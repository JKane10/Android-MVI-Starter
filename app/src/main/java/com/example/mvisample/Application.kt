package com.example.mvisample

import android.app.Application
import com.example.mvisample.network.api.SampleApi
import com.example.mvisample.network.services.SampleRepo
import com.example.mvisample.network.services.SampleRepoImpl
import com.readystatesoftware.chuck.ChuckInterceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class Application : Application() {

    lateinit var sampleRepo: SampleRepo

    override fun onCreate() {
        super.onCreate()

        val api = Retrofit.Builder()
            .baseUrl("https://jsonplaceholder.typicode.com")
            .addConverterFactory(GsonConverterFactory.create())
            .client(
                OkHttpClient.Builder().addInterceptor(ChuckInterceptor(applicationContext)).build()
            )
            .build()
            .create(SampleApi::class.java)
        sampleRepo = SampleRepoImpl(api)
    }

}