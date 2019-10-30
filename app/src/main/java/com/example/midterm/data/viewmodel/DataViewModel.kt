package com.example.midterm.data.viewmodel


import android.os.AsyncTask
import android.util.Log.d
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.midterm.data.repository.LocalDatabase
import com.example.midterm.data.repository.RetrofitDatabase
import com.example.midterm.data.response.All
import com.example.midterm.data.response.DefaultResponse

import retrofit2.Response
import kotlin.math.log

class DataViewModel(
   private val retrofitRepository : RetrofitDatabase,
   private val daoRepository: LocalDatabase
) : ViewModel() {

    val itemsLiveDataRetrofit = MutableLiveData<All>()
    val itemsLiveDataDao = MutableLiveData<All>()

    fun getCatsFacts(){
        retrofitRepository.loadData(
            onDataLoaded = { response ->
                itemsLiveDataRetrofit.postValue(response.body())
            },
            onError = { throwable ->
                println(throwable.message)
                getDaoCatsFacts()
            }
        )
    }

    fun insertAll(listOfItems: List<DefaultResponse>?){
        AsyncTask.execute{
            daoRepository.insertAll(listOfItems)
        }
    }
    fun getDaoCatsFacts(){
        AsyncTask.execute{
            val facts = daoRepository.getAllItems()
            itemsLiveDataDao.postValue(All(facts))
        }
    }


    class Factory(
        private val retrofitRepository: RetrofitDatabase,
        private val daoRepository: LocalDatabase
    ): ViewModelProvider.Factory{
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return DataViewModel(retrofitRepository, daoRepository) as T
        }
    }
}


