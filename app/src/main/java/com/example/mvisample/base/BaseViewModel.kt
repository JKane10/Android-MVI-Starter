package com.example.mvisample.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel

abstract class BaseViewModel<S : ViewState, A : Action>(initialState: S) : ViewModel() {
    private val nextAction = MutableLiveData<A>()
    var viewState: LiveData<S> = MutableLiveData<S>(initialState)

    init {
        viewState = Transformations.map(
            Transformations.switchMap(nextAction) { action ->
                sideEffect(action)
            }
        ) { result -> // this leaks results. If a side effect is emitted before a result comes back it is lost
            reduce(viewState.value ?: initialState, result).also {
                // opportunity to log state or any other info here
                val debugPoint = 0
            }
        }
    }

    fun dispatch(action: A) {
        nextAction.value = action
    }

    abstract fun sideEffect(action: A): LiveData<A>
    abstract fun reduce(currentViewState: S, action: A): S
}