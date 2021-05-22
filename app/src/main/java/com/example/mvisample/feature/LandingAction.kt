package com.example.mvisample.feature

import com.example.mvisample.base.Action

sealed class LandingAction : Action {

    object LoadInitialData : LandingAction()
    object Increment : LandingAction()
    data class LoadInitialDataSuccess(val result: String) : LandingAction()
}

sealed class LandingViewAction : LandingAction() {

    object ButtonClicked : LandingViewAction()
}
