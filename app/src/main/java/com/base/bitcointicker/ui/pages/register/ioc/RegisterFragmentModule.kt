package com.base.bitcointicker.ui.pages.register.ioc

import androidx.fragment.app.Fragment
import com.base.bitcointicker.ioc.keys.FragmentViewModelKey
import com.base.bitcointicker.ui.base.fragment.BaseViewModelFragmentModule
import com.base.bitcointicker.ui.base.viewmodel.BaseFragmentViewModel
import com.base.bitcointicker.ui.pages.register.RegisterFragment
import com.base.bitcointicker.ui.pages.register.repository.RegisterFragmentRemoteData
import com.base.bitcointicker.ui.pages.register.repository.RegisterFragmentRepository
import com.base.bitcointicker.ui.pages.register.viewmodel.RegisterFragmentViewModel
import com.base.core.ioc.scopes.FragmentScope
import com.base.core.networking.Scheduler
import com.base.data.request.BitcoinTickerApiInterface
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap
import io.reactivex.disposables.CompositeDisposable

@Module(includes = [BaseViewModelFragmentModule::class])
abstract class RegisterFragmentModule {

    @Binds
    @FragmentScope
    abstract fun bindFragment(fragment: RegisterFragment): Fragment

    @Binds
    @IntoMap
    @FragmentViewModelKey(RegisterFragmentViewModel::class)
    @FragmentScope
    abstract fun bindViewModel(viewModel: RegisterFragmentViewModel): BaseFragmentViewModel

    @Module
    companion object {

        @Provides
        @FragmentScope
        @JvmStatic
        fun provideRegisterRemoteData(apiInterface: BitcoinTickerApiInterface) =
            RegisterFragmentRemoteData(apiInterface)


        @Provides
        @FragmentScope
        @JvmStatic
        fun provideRegisterRepository(
            remote: RegisterFragmentRemoteData,
            scheduler: Scheduler,
            compositeDisposable: CompositeDisposable
        ) = RegisterFragmentRepository(remote, scheduler, compositeDisposable)
    }
}