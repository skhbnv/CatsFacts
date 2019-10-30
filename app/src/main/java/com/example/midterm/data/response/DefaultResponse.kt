package com.example.midterm.data.response

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "cats_facts")
data class DefaultResponse(
    @PrimaryKey @ColumnInfo(name = "id") val id: Int? = null,
    @ColumnInfo val text: String,
    @ColumnInfo val type: String,
    @ColumnInfo val upvotes: Int
)