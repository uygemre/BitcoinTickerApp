package com.base.bitcointicker.ui.pages.coindetail.ioc

import androidx.fragment.app.Fragment
import com.base.core.ioc.scopes.FragmentScope
import com.base.core.networking.Scheduler
import com.base.data.database.dao.FavoriteCoinDao
import com.base.data.request.BitcoinTickerApiInterface
import com.base.bitcointicker.ioc.keys.FragmentViewModelKey
import com.base.bitcointicker.ui.base.fragment.BaseViewModelFragmentModule
import com.base.bitcointicker.ui.base.viewmodel.BaseFragmentViewModel
import com.base.bitcointicker.ui.pages.coindetail.CoinDetailFragment
import com.base.bitcointicker.ui.pages.coindetail.repository.CoinDetailLocaleData
import com.base.bitcointicker.ui.pages.coindetail.repository.CoinDetailRemoteData
import com.base.bitcointicker.ui.pages.coindetail.repository.CoinDetailFragmentRepository
import com.base.bitcointicker.ui.pages.coindetail.viewmodel.CoinDetailFragmentViewModel
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap
import io.reactivex.disposables.CompositeDisposable

@Module(includes = [BaseViewModelFragmentModule::class])
abstract class CoinDetailFragmentModule {

    @Binds
    @FragmentScope
    abstract fun bindFragment(fragment: CoinDetailFragment): Fragment

    @Binds
    @IntoMap
    @FragmentViewModelKey(CoinDetailFragmentViewModel::class)
    @FragmentScope
    abstract fun bindViewModel(viewModel: CoinDetailFragmentViewModel): BaseFragmentViewModel

    @Module
    companion object {

        @Provides
        @FragmentScope
        @JvmStatic
        fun coinDetailFragmentRemoteData(apiInterface: BitcoinTickerApiInterface) =
            CoinDetailRemoteData(apiInterface)

        @Provides
        @FragmentScope
        @JvmStatic
        fun coinDetailFragmentLocaleData(favoriteCoinDao: FavoriteCoinDao) =
            CoinDetailLocaleData(favoriteCoinDao)

        @Provides
        @FragmentScope
        @JvmStatic
        fun coinDetailFragmentRepository(
            remote: CoinDetailRemoteData,
            locale: CoinDetailLocaleData,
            scheduler: Scheduler,
            compositeDisposable: CompositeDisposable
        ) = CoinDetailFragmentRepository(remote, locale, scheduler, compositeDisposable)
    }
}