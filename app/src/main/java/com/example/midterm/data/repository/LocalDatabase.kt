package com.example.midterm.data.repository

import com.example.midterm.data.response.DefaultResponse
import com.example.midterm.database.AppDatabase
import com.example.midterm.database.DaoFacts

class LocalDatabase(private val itemsDao: DaoFacts?){
    fun getAllItems() = itemsDao?.getAllFactsFromDatabase()
    fun insertAll(factsList: List<DefaultResponse>?) = itemsDao?.insertAll(factsList)
}