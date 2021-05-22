package com.example.mvisample.feature

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.mvisample.base.BaseViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.lang.Thread.sleep

class LandingViewModel : BaseViewModel<LandingState, LandingAction>(LandingState()) {

    val dataLoaded = MutableLiveData(false)

    override fun sideEffect(action: LandingAction): LiveData<LandingAction> {
        return when (action) {
            is LandingViewAction.ButtonClicked -> MutableLiveData(LandingAction.Increment)
            is LandingAction.LoadInitialData -> makeDataRequest(action)
            else -> MutableLiveData(action)
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

    private fun makeDataRequest(action: LandingAction): MutableLiveData<LandingAction> {
        val data = MutableLiveData<LandingAction>(action)
        viewModelScope.launch(Dispatchers.IO) {
            sleep(5000)
            data.postValue(LandingAction.LoadInitialDataSuccess("some result!"))
            dataLoaded.postValue(true)
        }
        return data
    }
}