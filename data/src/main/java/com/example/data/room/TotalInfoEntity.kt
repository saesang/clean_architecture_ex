package com.example.data.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class TotalInfoEntity (
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    @ColumnInfo(name = "date") val date: String,
    @ColumnInfo(name = "username") val username: String,
    @ColumnInfo(name = "age") val age: Int,
    @ColumnInfo(name = "personality") val personality: String,
    @ColumnInfo(name = "content") val content: String
)