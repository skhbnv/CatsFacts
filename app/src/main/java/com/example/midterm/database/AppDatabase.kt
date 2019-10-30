package com.example.midterm.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.midterm.data.response.DefaultResponse

@Database(entities = [DefaultResponse::class], version = 2, exportSchema = false )
abstract class AppDatabase: RoomDatabase() {
    abstract fun getItemDao() : DaoFacts

    companion object {
        private const val DB_NAME = "app.db"
        private var instance: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase? {
            if (instance == null) {
                instance = Room.databaseBuilder(
                    context, AppDatabase::class.java, DB_NAME
                ).build()
            }
            return instance
        }
    }
}