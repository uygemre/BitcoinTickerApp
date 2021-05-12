package com.base.bitcointicker.ui.pages.home.ioc

import androidx.fragment.app.Fragment
import com.base.core.ioc.scopes.FragmentScope
import com.base.core.networking.Scheduler
import com.base.data.request.BitcoinTickerApiInterface
import com.base.bitcointicker.ioc.keys.FragmentViewModelKey
import com.base.bitcointicker.ui.base.fragment.BaseViewModelFragmentModule
import com.base.bitcointicker.ui.base.viewmodel.BaseFragmentViewModel
import com.base.bitcointicker.ui.pages.home.HomeFragment
import com.base.bitcointicker.ui.pages.home.repository.HomeFragmentRemoteData
import com.base.bitcointicker.ui.pages.home.repository.HomeFragmentRepository
import com.base.bitcointicker.ui.pages.home.viewmodel.HomeFragmentViewModel
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap
import io.reactivex.disposables.CompositeDisposable

@Module(includes = [BaseViewModelFragmentModule::class])
abstract class HomeFragmentModule {

    @Binds
    @FragmentScope
    abstract fun bindFragment(fragment: HomeFragment): Fragment

    @Binds
    @IntoMap
    @FragmentViewModelKey(HomeFragmentViewModel::class)
    @FragmentScope
    abstract fun bindViewModel(viewModel: HomeFragmentViewModel): BaseFragmentViewModel

    @Module
    companion object {

        @Provides
        @FragmentScope
        @JvmStatic
        fun provideHomeRemoteData(apiInterface: BitcoinTickerApiInterface) =
            HomeFragmentRemoteData(apiInterface)

        @Provides
        @FragmentScope
        @JvmStatic
        fun provideHomeRepository(
            remote: HomeFragmentRemoteData,
            scheduler: Scheduler,
            compositeDisposable: CompositeDisposable
        ) = HomeFragmentRepository(remote, scheduler, compositeDisposable)
    }
}