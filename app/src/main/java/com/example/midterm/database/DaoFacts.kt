package com.example.midterm.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.midterm.data.response.DefaultResponse

@Dao
interface DaoFacts {
    @Insert
    fun insertAll(facts : List<DefaultResponse>?)

    @Query("SELECT * FROM cats_facts")
    fun getAllFactsFromDatabase(): List<DefaultResponse>
}