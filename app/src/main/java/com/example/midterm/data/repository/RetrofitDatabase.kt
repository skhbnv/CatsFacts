package com.example.midterm.data.repository

import android.util.Log
import com.example.midterm.api.RetrofitClient
import com.example.midterm.data.response.All
import kotlinx.coroutines.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RetrofitDatabase {

    fun loadData(onDataLoaded: (Response<All>) -> Unit, onError : (Throwable) -> Unit){
        RetrofitClient.instance.fetchData().enqueue(object: Callback<All> {
            override fun onFailure(call: Call<All>, t: Throwable) {
                Log.d("___", t.message)
                onError(t)
            }

            override fun onResponse(call: Call<All>, response: Response<All>) {
                onDataLoaded(response)
            }
        })
    }
}