package com.base.bitcointicker.ui.pages.home.repository

import com.base.core.extensions.*
import com.base.core.ioc.scopes.FragmentScope
import com.base.core.networking.DataFetchResult
import com.base.core.networking.Scheduler
import com.base.data.response.CoinListResponse
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.subjects.PublishSubject
import timber.log.Timber

@FragmentScope
class HomeFragmentRepository(
    private val remote: HomeFragmentRemoteData,
    private val scheduler: Scheduler,
    private val compositeDisposable: CompositeDisposable
) : HomeFragmentContract.Repository {

    override fun <T> handleError(result: PublishSubject<DataFetchResult<T>>, error: Throwable) {
        result.failed(error)
        Timber.e(error.localizedMessage)
    }

    override val coinListPublishSubject = PublishSubject.create<DataFetchResult<List<CoinListResponse>>>()

    override fun getCoinList() {
        coinListPublishSubject.loading(true)
        remote.getCoinList()
            .performOnBackOutOnMain(scheduler)
            .subscribe(
                {
                    coinListPublishSubject.success(it)
                },
                {
                    handleError(coinListPublishSubject, it)
                }
            ).addTo(compositeDisposable)
    }
}