package com.base.bitcointicker.ioc.modules.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.base.data.database.dao.FavoriteCoinDao
import com.base.data.database.model.FavoriteCoinDTO

@Database(entities = [FavoriteCoinDTO::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun favoriteCoinsDao(): FavoriteCoinDao
}
