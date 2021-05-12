package com.base.bitcointicker.ui.pages.coindetail.repository

import com.base.core.extensions.*
import com.base.core.networking.DataFetchResult
import com.base.core.networking.Scheduler
import com.base.data.database.model.FavoriteCoinDTO
import com.base.data.response.CoinDetailResponse
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.subjects.PublishSubject
import timber.log.Timber

class CoinDetailFragmentRepository(
    private val remote: CoinDetailRemoteData,
    private val locale: CoinDetailLocaleData,
    private val scheduler: Scheduler,
    private val compositeDisposable: CompositeDisposable
) : CoinDetailFragmentContract.Repository {

    override fun <T> handleError(result: PublishSubject<DataFetchResult<T>>, error: Throwable) {
        result.failed(error)
        Timber.e(error.localizedMessage)
    }

    override fun insertFav(favoriteCoinDTO: FavoriteCoinDTO) {
        return locale.insertFav(favoriteCoinDTO)
    }

    override fun deleteFavoritesById(favoriteCoinId: String) {
        return locale.deleteFavoritesById(favoriteCoinId)
    }

    override val getCoinDetailPublishSubject = PublishSubject.create<DataFetchResult<CoinDetailResponse>>()

    override fun getCoinDetail(id: String?) {
        getCoinDetailPublishSubject.loading(true)
        remote.getCoinDetail(id)
            .performOnBackOutOnMain(scheduler)
            .subscribe(
                {
                    getCoinDetailPublishSubject.success(it)
                },
                {
                    handleError(getCoinDetailPublishSubject, it)
                }
            ).addTo(compositeDisposable)
    }
}