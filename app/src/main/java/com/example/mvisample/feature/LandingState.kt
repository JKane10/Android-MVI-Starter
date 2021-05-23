package com.example.mvisample.feature

import com.example.mvisample.base.ViewState
import com.example.mvisample.network.models.Post

data class LandingState(
    val isLoading: Boolean = false,
    val count: Int = 0,
    val dataLoaded: Boolean = false,
    val posts: List<Post> = emptyList()
) : ViewState