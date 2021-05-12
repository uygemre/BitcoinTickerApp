package com.base.bitcointicker.ui.pages.main.repository

import com.base.core.networking.Scheduler
import io.reactivex.disposables.CompositeDisposable

class MainActivityRepository(
    private val remote: MainActivityRemoteData,
    private val scheduler: Scheduler,
    private val compositeDisposable: CompositeDisposable
) : MainActivityContract.Repository {
}
