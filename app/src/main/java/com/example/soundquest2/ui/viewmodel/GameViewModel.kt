package com.example.soundquest2.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.soundquest2.domain.model.GameMode
import com.example.soundquest2.ui.state.GameIntent
import com.example.soundquest2.ui.state.GameUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import com.example.soundquest2.core.errors.Result
import com.example.soundquest2.domain.model.GameResult
import com.example.soundquest2.domain.model.content.Film
import com.example.soundquest2.domain.model.content.Game
import com.example.soundquest2.domain.model.content.MediaContent
import com.example.soundquest2.domain.model.content.Song
import com.example.soundquest2.domain.usecase.CheckAnswerUseCase
import com.example.soundquest2.domain.usecase.GenerateRoundsUseCase
import com.example.soundquest2.domain.usecase.InsertResultUseCase
import com.example.soundquest2.domain.usecase.LoadMediaUseCase
import com.example.soundquest2.domain.usecase.PlayAnswerAudioUseCase
import com.example.soundquest2.domain.usecase.PlayQuestionAudioUseCase
import com.example.soundquest2.domain.usecase.PlayVideoUseCase
import com.example.soundquest2.domain.usecase.PrepareVideosUseCase
import com.example.soundquest2.domain.usecase.RepeatAudioUseCase
import com.example.soundquest2.ui.state.GameState
import com.example.soundquest2.ui.state.VideoCommand
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow

class GameViewModel(
    val language: String,
    private val loadMedia: LoadMediaUseCase,
    private val generateRounds: GenerateRoundsUseCase,
    private val checkAnswer: CheckAnswerUseCase,
    private val insertResult: InsertResultUseCase,
    private var playQuestionAudio: PlayQuestionAudioUseCase,
    private var playAnswerAudio: PlayAnswerAudioUseCase,
    private var repeatAudio: RepeatAudioUseCase
) : ViewModel() {

    private var state = GameState()

    private val _uiState = MutableStateFlow<GameUiState>(GameUiState.Loading)
    val uiState: StateFlow<GameUiState> = _uiState.asStateFlow()

    private val _videoCommands = MutableSharedFlow<VideoCommand>()
    val videoCommands = _videoCommands.asSharedFlow()

    fun onIntent(intent: GameIntent) {
        when (intent) {

            GameIntent.Start -> startGame()

            is GameIntent.SetMode -> setGameMode(intent.gameMode)

            is GameIntent.ChooseAnswer -> check(intent.audio)

            GameIntent.NextRound -> nextRound()

            GameIntent.RestartAudio -> repeatAudio()
        }
    }

    private fun setGameMode(mode: GameMode) {
        state = state.copy(gameMode = mode)
    }

    private fun startGame() {
        viewModelScope.launch {
            _uiState.value = GameUiState.Loading

            when (val result = loadMedia(forceRefresh = true, state.gameMode, language, count = 10)) {
                is Result.Success -> {
                    val rounds = generateRounds(
                        items = result.data,
                        roundsCount = 5,
                        optionsPerRound = 4
                    )

                    state = state.copy(
                        rounds = rounds,
                        currentRoundIndex = 0,
                        score = 0
                    )

                    val videos = rounds.map{it.correct}.mapNotNull {
                        when (it) {
                            is Song -> it.visualMedia.localVideoPath
                            is Game -> it.gameMedia.localVideoPath
                            is Film -> it.filmMedia.localVideoPath
                        }
                    }

                    _videoCommands.tryEmit(
                        VideoCommand.Prepare(videos)
                    )

                    showRound()
                }

                is Result.Error -> {
                    _uiState.value = GameUiState.Error(
                        error = result.error,
                        gameMode = state.gameMode
                    )
                }
            }
        }
    }

    private fun showRound() {
        val round = state.currentRound ?: return

        _uiState.value = GameUiState.Round(
            roundNumber = state.currentRoundIndex + 1,
            totalRounds = state.rounds.size,
            options = round.options,
            correctAnswer = state.currentRound!!.correct
        )

        playQuestionAudio(round.correct)

        _videoCommands.tryEmit(
            VideoCommand.Play(state.currentRoundIndex)
        )
    }

    private fun check(selected: MediaContent) {
        val round = state.currentRound ?: return

        val isCorrect = checkAnswer(selected, round)

        //emitStopAudio()

        state = state.copy(
            score = if (isCorrect) state.score + 1 else state.score
        )

        _uiState.value = GameUiState.Result(
            isCorrect = isCorrect,
            selected = selected,
            correct = round.correct,
        )

        playAnswerAudio(round.correct)
    }

    private fun nextRound() {
        //emitStopAudio()

        if (state.isLastRound) {
            finishGame()
        } else {
            state = state.copy(
                currentRoundIndex = state.currentRoundIndex + 1
            )
            showRound()
        }
    }

    private fun finishGame() {
        //emitStopAudio()

        val result = GameResult(
            createdAt = System.currentTimeMillis(),
            roundsCount = state.totalRounds,
            guessedSongsCount = state.score,
            gameMode = state.gameMode
        )

        _uiState.value = GameUiState.Finished(result)

        viewModelScope.launch {
            insertResult(result)
        }
    }
}