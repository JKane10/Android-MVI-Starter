package com.example.mvisample.feature

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.mvisample.base.BaseViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.lang.Thread.sleep

class LandingViewModel : BaseViewModel<LandingState, LandingAction>(LandingState()) {

    val dataLoaded = MutableLiveData(false)

    override fun sideEffect(action: LandingAction): LandingAction {
        return when (action) {
            is LandingViewAction.ButtonClicked -> LandingAction.Increment
            is LandingAction.LoadInitialData -> makeDataRequest(action)
            else -> action
        }
    }

    override fun reduce(currentViewState: LandingState, action: LandingAction): LandingState {
        return when (action) {
            is LandingAction.Increment -> currentViewState.copy(count = currentViewState.count + 1)
            is LandingAction.LoadInitialData -> currentViewState.copy(isLoading = true)
            is LandingAction.LoadInitialDataSuccess -> currentViewState.copy(isLoading = false)
            else -> currentViewState
        }
    }

    private fun makeDataRequest(action: LandingAction): LandingAction {
        viewModelScope.launch(Dispatchers.IO) {
            sleep(3000)
            actionFlow.emit(LandingAction.LoadInitialDataSuccess("some result!"))
            dataLoaded.postValue(true)
        }
        return action
    }
}