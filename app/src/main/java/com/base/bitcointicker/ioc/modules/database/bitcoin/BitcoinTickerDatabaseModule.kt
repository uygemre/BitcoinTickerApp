package com.base.bitcointicker.ioc.modules.database.bitcoin

import android.content.Context
import androidx.room.Room
import com.base.data.database.dao.FavoriteCoinDao
import com.base.bitcointicker.application.Application
import com.base.bitcointicker.ioc.modules.database.AppDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
internal class BitcoinTickerDatabaseModule {

    @Singleton
    @Provides
    fun provideDb(app: Context): AppDatabase {
        return Room.databaseBuilder(
            app.applicationContext,
            AppDatabase::class.java, "bitcoin.db"
        ).build()
    }

    @Singleton
    @Provides
    fun provideFavoriteCoinsDao(db: AppDatabase): FavoriteCoinDao {
        return db.favoriteCoinsDao()
    }

    @Provides
    fun provideContext(application: Application): Context {
        return application.applicationContext
    }
}