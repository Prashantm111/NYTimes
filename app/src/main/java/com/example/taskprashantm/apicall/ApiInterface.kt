package com.example.prashantmtask.apicall

import com.example.prashantmtask.models.MainResponseNYTimes
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiInterface {
    @GET("all-sections/{period}.json?")

    fun getAllfeeds(
        @Path("period") period: String,
        @Query("api-key") sample_key: String
    ): Call<MainResponseNYTimes>
}