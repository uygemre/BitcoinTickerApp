package com.base.bitcointicker.ui.pages.home.repository

import com.base.core.networking.DataFetchResult
import com.base.data.response.CoinListResponse
import io.reactivex.Single
import io.reactivex.subjects.PublishSubject

interface HomeFragmentContract {

    interface Repository {
        fun <T> handleError(dataFetchResult: PublishSubject<DataFetchResult<T>>, error: Throwable)
        val coinListPublishSubject: PublishSubject<DataFetchResult<List<CoinListResponse>>>
        fun getCoinList()
    }
    interface Remote{
        fun getCoinList(): Single<List<CoinListResponse>>
    }
}