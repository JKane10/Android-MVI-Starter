package com.example.mvisample.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

abstract class BaseViewModel<S : ViewState, A : Action>(initialState: S) : ViewModel() {
    val actionFlow = MutableSharedFlow<A>()
    private val _state = MutableStateFlow(initialState)
    val state: StateFlow<S> = _state

    init {
        viewModelScope.launch {
            actionFlow.collect {
                sideEffect(it).let { result ->
                    _state.value = reduce(state.value, result)
                }
            }
        }
    }

    fun dispatch(action: A) {
        viewModelScope.launch {
            actionFlow.emit(action)
        }
    }

    abstract fun sideEffect(action: A): A
    abstract fun reduce(currentViewState: S, action: A): S
}