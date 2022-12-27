package com.example.uihomework.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitInstance {
    companion object {
        private const val BASE_URL = "https://data.epa.gov.tw/api/v2/"
        private var retrofitInstance: Retrofit? = null

        fun getInstance(): Retrofit {
            if (retrofitInstance == null) {
                retrofitInstance = Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
            }

            return retrofitInstance!!
        }
    }
}