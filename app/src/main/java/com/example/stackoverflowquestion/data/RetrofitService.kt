package com.example.stackoverflowquestion.data

import com.example.stackoverflowquestion.data.remote.ApiService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitService {
    private const val BASE_URL = "https://api.stackexchange.com/2.2/"

    private fun retrofitService(): Retrofit {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .build()
    }

    val api: ApiService by lazy {
        retrofitService().create(ApiService::class.java)
    }

}

