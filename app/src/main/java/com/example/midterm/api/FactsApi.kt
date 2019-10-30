package com.example.midterm.api

import com.example.midterm.data.response.All
import retrofit2.Call
import retrofit2.http.GET


interface FactsApi {
    @GET("facts")
    fun fetchData(): Call<All>
}