package com.base.bitcointicker.ui.pages.favorites.repository

import com.base.core.networking.DataFetchResult
import com.base.data.database.model.FavoriteCoinDTO
import io.reactivex.Single
import io.reactivex.subjects.PublishSubject

interface FavoritesFragmentContract {

    interface Repository {
        val getAllFavoritesPublishSubject: PublishSubject<DataFetchResult<List<FavoriteCoinDTO>>>
        fun getAllFavorites()
        fun deleteAllFavorites()
        fun <T> handleError(dataFetchResult: PublishSubject<DataFetchResult<T>>, error: Throwable)
    }

    interface Remote {
    }

    interface Locale {
        fun getAllFavorites(): Single<List<FavoriteCoinDTO>>
        fun deleteAllFavorites()
    }
}