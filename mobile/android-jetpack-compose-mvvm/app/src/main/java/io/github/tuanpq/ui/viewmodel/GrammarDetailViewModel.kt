package io.github.tuanpq.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import io.github.tuanpq.domain.model.GrammarEntity
import io.github.tuanpq.domain.usecase.GrammarUseCase
import io.github.tuanpq.utility.UIState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GrammarDetailViewModel@Inject constructor(
    private val grammarUseCase: GrammarUseCase
) : ViewModel() {
    private val _grammarDetailState = MutableStateFlow<UIState<GrammarEntity>>(UIState.Loading)
    val grammarDetailState: StateFlow<UIState<GrammarEntity>> = _grammarDetailState.asStateFlow()

    fun findGrammarById(grammarId: Int) {
        viewModelScope.launch {
            grammarUseCase.findGrammarById(grammarId)
                .onStart { _grammarDetailState.value = UIState.Loading }
                .catch { error -> _grammarDetailState.value = UIState.Error("${error.localizedMessage}") }
                .collect { result ->
                    _grammarDetailState.value = result
                }
        }
    }
}