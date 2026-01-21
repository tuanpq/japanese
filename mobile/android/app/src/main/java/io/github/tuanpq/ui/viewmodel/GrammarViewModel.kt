package io.github.tuanpq.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import io.github.tuanpq.aspect.Trace
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
class GrammarViewModel @Inject constructor(
    private val grammarUseCase: GrammarUseCase
) : ViewModel() {

    private val _grammarsState = MutableStateFlow<UIState<List<GrammarEntity>>>(UIState.Loading)
    val grammarsState: StateFlow<UIState<List<GrammarEntity>>> = _grammarsState.asStateFlow()

    fun findAllGrammars() {
        viewModelScope.launch {
            grammarUseCase.findAllGrammars()
                .onStart { _grammarsState.value = UIState.Loading }
                .catch { error -> _grammarsState.value = UIState.Error("${error.localizedMessage}") }
                .collect { result ->
                    _grammarsState.value = result
                }
        }
    }

}