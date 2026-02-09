package com.example.soundquest2.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.soundquest2.core.language.AppLanguage
import com.example.soundquest2.domain.model.GameMode
import com.example.soundquest2.domain.model.GameState
import com.example.soundquest2.domain.model.content.MediaContent
import com.example.soundquest2.domain.usecase.CheckAnswerUseCase
import com.example.soundquest2.domain.usecase.NextRoundUseCase
import com.example.soundquest2.domain.usecase.ResetGameUseCase
import com.example.soundquest2.domain.usecase.SetGameModeUseCase
import com.example.soundquest2.domain.usecase.StartGameUseCase
import com.example.soundquest2.ui.intent.GameIntent
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
    private val languageFlow: StateFlow<AppLanguage>
) : ViewModel() {


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

            is GameIntent.ChooseAnswer -> checkAnswer(intent.content)

            GameIntent.NextRound -> nextRound()

            GameIntent.RestartVerse -> {}

            GameIntent.Reset -> reset()
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
        }
    }

    private fun checkAnswer(selected: MediaContent) {
        _state.update {
            checkAnswer(it, selected)
        }
    }

    private fun nextRound() {
        _state.update {
            nextRound(it, viewModelScope)
        }
    }

    private fun reset() {
        _state.update {
            resetGame(it)
        }
    }
}