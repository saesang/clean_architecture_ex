package com.example.data.room

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [TotalInfoEntity::class],
    version = 2
)
abstract class TodayFortuneDb : RoomDatabase() {
    abstract fun dao(): TodayFortuneDao
}