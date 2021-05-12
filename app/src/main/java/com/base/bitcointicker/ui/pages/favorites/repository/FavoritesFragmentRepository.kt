package com.base.bitcointicker.ui.pages.favorites.repository

import com.base.core.extensions.*
import com.base.core.networking.DataFetchResult
import com.base.core.networking.Scheduler
import com.base.data.database.model.FavoriteCoinDTO
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.subjects.PublishSubject
import timber.log.Timber

class FavoritesFragmentRepository(
    private val remote: FavoritesFragmentRemoteData,
    private val locale: FavoritesFragmentLocaleData,
    private val scheduler: Scheduler,
    private val compositeDisposable: CompositeDisposable
) : FavoritesFragmentContract.Repository {

    override val getAllFavoritesPublishSubject =
        PublishSubject.create<DataFetchResult<List<FavoriteCoinDTO>>>()

    override fun getAllFavorites() {
        getAllFavoritesPublishSubject.loading(true)
        locale.getAllFavorites()
            .performOnBackOutOnMain(scheduler)
            .subscribe(
                {
                    getAllFavoritesPublishSubject.success(it)
                },
                { _error ->
                    handleError(getAllFavoritesPublishSubject, _error)
                }
            ).addTo(compositeDisposable)
    }

    override fun deleteAllFavorites() {
        return locale.deleteAllFavorites()
    }

    override fun <T> handleError(result: PublishSubject<DataFetchResult<T>>, error: Throwable) {
        result.failed(error)
        Timber.e(error.localizedMessage)
    }
}