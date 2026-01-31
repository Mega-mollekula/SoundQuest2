package com.example.soundquest2.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.soundquest2.domain.model.GameResult
import com.example.soundquest2.domain.usecase.GetResultsUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class StatisticViewModel(
    private val getResultsUseCase: GetResultsUseCase
) : ViewModel() {

    private val _results = MutableStateFlow<List<GameResult>>(emptyList())
    val results: StateFlow<List<GameResult>> = _results.asStateFlow()

    init {
        viewModelScope.launch {
            _results.value = getResultsUseCase()
        }
    }
}