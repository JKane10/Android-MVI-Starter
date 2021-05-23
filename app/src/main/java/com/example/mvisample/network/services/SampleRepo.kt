package com.example.mvisample.network.services

import com.example.mvisample.network.api.SampleApi
import com.example.mvisample.network.models.Post

interface SampleRepo {

    suspend fun getPosts(): List<Post>
}

class SampleRepoImpl(private val sampleApi: SampleApi) : SampleRepo {

    override suspend fun getPosts() = sampleApi.getPosts()
}