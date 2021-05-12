package com.base.bitcointicker.ui.pages.login.repository

import com.base.core.networking.Scheduler
import io.reactivex.disposables.CompositeDisposable

class LoginFragmentRepository(
    private val remote: LoginFragmentRemoteData,
    private val scheduler: Scheduler,
    private val compositeDisposable: CompositeDisposable
) : LoginFragmentContract.Repository {
}