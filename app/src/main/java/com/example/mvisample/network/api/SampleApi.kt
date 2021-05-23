package com.example.mvisample.network.api

import com.example.mvisample.network.models.Post
import retrofit2.http.GET

interface  SampleApi {

    @GET("/posts")
    suspend fun getPosts() : List<Post>
}