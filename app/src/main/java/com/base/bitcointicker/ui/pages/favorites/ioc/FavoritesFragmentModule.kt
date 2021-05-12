package com.base.bitcointicker.ui.pages.favorites.ioc

import androidx.fragment.app.Fragment
import com.base.core.ioc.scopes.FragmentScope
import com.base.core.networking.Scheduler
import com.base.data.database.dao.FavoriteCoinDao
import com.base.data.request.BitcoinTickerApiInterface
import com.base.bitcointicker.ioc.keys.FragmentViewModelKey
import com.base.bitcointicker.ui.base.fragment.BaseViewModelFragmentModule
import com.base.bitcointicker.ui.base.viewmodel.BaseFragmentViewModel
import com.base.bitcointicker.ui.pages.favorites.FavoritesFragment
import com.base.bitcointicker.ui.pages.favorites.repository.FavoritesFragmentLocaleData
import com.base.bitcointicker.ui.pages.favorites.repository.FavoritesFragmentRemoteData
import com.base.bitcointicker.ui.pages.favorites.repository.FavoritesFragmentRepository
import com.base.bitcointicker.ui.pages.favorites.viewmodel.FavoritesFragmentViewModel
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap
import io.reactivex.disposables.CompositeDisposable

@Module(includes = [BaseViewModelFragmentModule::class])
abstract class FavoritesFragmentModule {

    @Binds
    @FragmentScope
    abstract fun bindFragment(fragment: FavoritesFragment): Fragment

    @Binds
    @IntoMap
    @FragmentViewModelKey(FavoritesFragmentViewModel::class)
    @FragmentScope
    abstract fun bindViewModel(viewModel: FavoritesFragmentViewModel): BaseFragmentViewModel

    @Module
    companion object {

        @Provides
        @FragmentScope
        @JvmStatic
        fun provideFavoritesRemoteData(apiInterface: BitcoinTickerApiInterface) =
            FavoritesFragmentRemoteData(apiInterface)

        @Provides
        @FragmentScope
        @JvmStatic
        fun provideFavoritesLocaleData(favoriteCoinDao: FavoriteCoinDao) =
            FavoritesFragmentLocaleData(favoriteCoinDao)

        @Provides
        @FragmentScope
        @JvmStatic
        fun provideFavoritesRepository(
            remote: FavoritesFragmentRemoteData,
            locale: FavoritesFragmentLocaleData,
            scheduler: Scheduler,
            compositeDisposable: CompositeDisposable
        ) = FavoritesFragmentRepository(remote, locale, scheduler, compositeDisposable)
    }
}