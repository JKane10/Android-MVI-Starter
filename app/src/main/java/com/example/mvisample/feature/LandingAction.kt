package com.example.mvisample.feature

import com.example.mvisample.base.Action
import com.example.mvisample.network.models.Post

sealed class LandingAction : Action {

    object LoadInitialData : LandingAction()
    object Increment : LandingAction()
    data class LoadInitialDataSuccess(val posts: List<Post>) : LandingAction()
}

sealed class LandingViewAction : LandingAction() {

    object ButtonClicked : LandingViewAction()
}
