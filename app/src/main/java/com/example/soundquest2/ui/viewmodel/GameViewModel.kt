package com.example.soundquest2.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.soundquest2.core.language.AppLanguage
import com.example.soundquest2.core.media.AudioPlayer
import com.example.soundquest2.core.media.VideoPlayer
import com.example.soundquest2.domain.model.GameMode
import com.example.soundquest2.domain.model.GameState
import com.example.soundquest2.domain.model.enums.GamePhase
import com.example.soundquest2.domain.usecase.CheckAnswerUseCase
import com.example.soundquest2.domain.usecase.FinishGameUseCase
import com.example.soundquest2.domain.usecase.InsertResultUseCase
import com.example.soundquest2.domain.usecase.NextRoundUseCase
import com.example.soundquest2.domain.usecase.ResetGameUseCase
import com.example.soundquest2.domain.usecase.SetGameModeUseCase
import com.example.soundquest2.domain.usecase.StartGameUseCase
import com.example.soundquest2.ui.intent.GameIntent
import com.example.soundquest2.ui.intent.RoundResultIntent
import com.example.soundquest2.ui.playback.GamePlaybackController
import com.example.soundquest2.ui.playback.VideoPreparer
import com.example.soundquest2.ui.state.GameUiState
import com.example.soundquest2.ui.toUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class GameViewModel(
    private val setGameMode: SetGameModeUseCase,
    private val startGame: StartGameUseCase,
    private val checkAnswer: CheckAnswerUseCase,
    private val nextRound: NextRoundUseCase,
    private val resetGame: ResetGameUseCase,
    private val finishGame: FinishGameUseCase,
    private val insertResult: InsertResultUseCase,
    private val languageFlow: StateFlow<AppLanguage>,
    private val audioPlayer: AudioPlayer,
    private val videoPlayer: VideoPlayer,
    private val videoPreparer: VideoPreparer,
) : ViewModel() {

    private lateinit var playbackController: GamePlaybackController

    private val _state = MutableStateFlow(GameState())
    val uiState: StateFlow<GameUiState> =
        combine(_state, languageFlow) { gameState, language ->
            gameState.toUiState(language.code)
        }.stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5_000),
            GameUiState.Idle
        )

    fun onIntent(intent: GameIntent) {
        when (intent) {

            GameIntent.Start -> startGame()

            is GameIntent.SetMode -> setMode(intent.gameMode)

            is GameIntent.ChooseAnswer -> checkAnswer(intent.content.id)

            GameIntent.NextRound -> nextRound()

            GameIntent.RestartVerse -> playbackController.restartAudio()

            GameIntent.Reset -> reset()
        }
    }

    fun onResultIntent(intent: RoundResultIntent) {
        when (intent) {
            RoundResultIntent.ScreenShown -> {
                playbackController.playVideoForRound(_state.value.currentRound!!)
            }

            RoundResultIntent.ContinueClicked -> {
                onIntent(GameIntent.NextRound)
            }

            RoundResultIntent.FavouriteClicked -> {}
        }
    }

    private fun setMode(mode: GameMode) {
        _state.update {
            setGameMode(it, mode)
        }
    }

    private fun startGame() {
        val currentLanguage = languageFlow.value
        viewModelScope.launch {
            _state.update {
                startGame(it, currentLanguage.code, count = 10)
            }
            val resolver = videoPreparer.prepare(_state.value.rounds)
            playbackController = GamePlaybackController(
                audioPlayer,
                videoPlayer,
                resolver
            )
            playbackController.playRoundAudio(_state.value.currentRound!!)
        }
    }

    private fun checkAnswer(selectedId: Long) {
        _state.update {
            checkAnswer(it, selectedId)
        }
        playbackController.playResultAudio(_state.value.currentRound!!)
    }

    private fun nextRound() {
        val prevState = _state.value
        val newState = nextRound(prevState)

        _state.value = newState

        if (newState.gamePhase == GamePhase.FINISHED) {
            val result = finishGame(prevState)

            viewModelScope.launch {
                insertResult(result)
            }
        }
        else {
            playbackController.playRoundAudio(newState.currentRound!!)
        }
    }

    private fun reset() {
        playbackController.release()
        _state.update {
            resetGame(it)
        }
    }
}