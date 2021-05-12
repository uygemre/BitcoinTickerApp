package com.base.bitcointicker.ui.pages.register.repository

import com.base.core.networking.Scheduler
import io.reactivex.disposables.CompositeDisposable

class RegisterFragmentRepository(
    private val remote: RegisterFragmentRemoteData,
    private val scheduler: Scheduler,
    private val compositeDisposable: CompositeDisposable
) : RegisterFragmentContract.Repository {
}