package com.base.bitcointicker.ui.pages.detail.repository

import com.base.core.networking.Scheduler
import io.reactivex.disposables.CompositeDisposable

class DetailActivityRepository(
    private val remote: DetailActivityRemoteData,
    private val scheduler: Scheduler,
    private val compositeDisposable: CompositeDisposable
) : DetailActivityContract.Repository {
}