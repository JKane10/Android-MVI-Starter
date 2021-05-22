package com.example.mvisample.feature

import com.example.mvisample.base.ViewState

data class LandingState(
    val isLoading: Boolean = false,
    val count: Int = 0,
    val dataLoaded: Boolean = false
) : ViewState