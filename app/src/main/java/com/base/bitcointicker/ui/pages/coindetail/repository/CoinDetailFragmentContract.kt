package com.base.bitcointicker.ui.pages.coindetail.repository

import com.base.core.networking.DataFetchResult
import com.base.data.database.model.FavoriteCoinDTO
import com.base.data.response.CoinDetailResponse
import io.reactivex.Single
import io.reactivex.subjects.PublishSubject

interface CoinDetailFragmentContract {

    interface Repository {
        fun <T> handleError(dataFetchResult: PublishSubject<DataFetchResult<T>>, error: Throwable)
        fun insertFav(favoriteCoinDTO: FavoriteCoinDTO)
        fun deleteFavoritesById(favoriteCoinId: String)
        val getCoinDetailPublishSubject: PublishSubject<DataFetchResult<CoinDetailResponse>>
        fun getCoinDetail(id: String?)
    }

    interface Remote {
        fun getCoinDetail(id: String?): Single<CoinDetailResponse>
    }

    interface Locale {
        fun insertFav(favData: FavoriteCoinDTO)
        fun deleteFavoritesById(favoriteCoinId: String)
    }
}