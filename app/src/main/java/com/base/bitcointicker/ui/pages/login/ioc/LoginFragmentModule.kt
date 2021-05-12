package com.base.bitcointicker.ui.pages.login.ioc

import androidx.fragment.app.Fragment
import com.base.bitcointicker.ioc.keys.FragmentViewModelKey
import com.base.bitcointicker.ui.base.fragment.BaseViewModelFragmentModule
import com.base.bitcointicker.ui.base.viewmodel.BaseFragmentViewModel
import com.base.bitcointicker.ui.pages.login.LoginFragment
import com.base.bitcointicker.ui.pages.login.repository.LoginFragmentRemoteData
import com.base.bitcointicker.ui.pages.login.repository.LoginFragmentRepository
import com.base.bitcointicker.ui.pages.login.viewmodel.LoginFragmentViewModel
import com.base.core.ioc.scopes.FragmentScope
import com.base.core.networking.Scheduler
import com.base.data.request.BitcoinTickerApiInterface
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap
import io.reactivex.disposables.CompositeDisposable

@Module(includes = [BaseViewModelFragmentModule::class])
abstract class LoginFragmentModule {

    @Binds
    @FragmentScope
    abstract fun bindFragment(fragment: LoginFragment): Fragment

    @Binds
    @IntoMap
    @FragmentViewModelKey(LoginFragmentViewModel::class)
    @FragmentScope
    abstract fun bindViewModel(viewModel: LoginFragmentViewModel): BaseFragmentViewModel

    @Module
    companion object {

        @Provides
        @FragmentScope
        @JvmStatic
        fun provideLoginRemoteData(apiInterface: BitcoinTickerApiInterface) =
            LoginFragmentRemoteData(apiInterface)


        @Provides
        @FragmentScope
        @JvmStatic
        fun provideLoginRepository(
            remote: LoginFragmentRemoteData,
            scheduler: Scheduler,
            compositeDisposable: CompositeDisposable
        ) = LoginFragmentRepository(remote, scheduler, compositeDisposable)
    }
}