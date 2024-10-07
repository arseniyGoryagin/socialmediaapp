package com.arseniy.blogapp.network

import com.skydoves.retrofit.adapters.arrow.EitherCallAdapterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit
import retrofit2.converter.kotlinx.serialization.asConverterFactory

object RetrofitClient {

    private val BASE_URL = "http://77.221.154.76:8080/"

    fun getRetrofit() : Retrofit{

        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addCallAdapterFactory(EitherCallAdapterFactory.create())
            .addConverterFactory(Json { ignoreUnknownKeys = true }.asConverterFactory(contentType = "application/json".toMediaType()))
            .build()
    }


}