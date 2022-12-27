package com.example.uihomework.api

import com.example.uihomework.model.AirPollution
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query


interface ApiService {
    @GET("aqx_p_432")
    fun getUserList(
        @Query("limit") limit: Int = 1000,
        @Query("api_key") offset: String = "cebebe84-e17d-4022-a28f-81097fda5896",
        @Query("sort") sort: String = "ImportDate",
        @Query("format") format: String = "json",
    ): Call<AirPollution>
}