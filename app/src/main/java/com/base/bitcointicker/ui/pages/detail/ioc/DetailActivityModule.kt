package com.base.bitcointicker.ui.pages.detail.ioc

import androidx.appcompat.app.AppCompatActivity
import com.base.core.ioc.scopes.ActivityScope
import com.base.core.networking.Scheduler
import com.base.data.request.BitcoinTickerApiInterface
import com.base.bitcointicker.ioc.builder.FragmentBuilderModule
import com.base.bitcointicker.ioc.keys.ActivityViewModelKey
import com.base.bitcointicker.ioc.modules.database.bitcoin.BitcoinTickerService
import com.base.bitcointicker.ui.base.activity.BaseActivityModule
import com.base.bitcointicker.ui.base.viewmodel.BaseActivityViewModel
import com.base.bitcointicker.ui.pages.detail.DetailActivity
import com.base.bitcointicker.ui.pages.detail.repository.DetailActivityRemoteData
import com.base.bitcointicker.ui.pages.detail.repository.DetailActivityRepository
import com.base.bitcointicker.ui.pages.detail.viewmodel.DetailActivityViewModel
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap
import io.reactivex.disposables.CompositeDisposable

@Module(includes = [BaseActivityModule::class, FragmentBuilderModule::class, BitcoinTickerService::class])
abstract class DetailActivityModule {

    @Binds
    @ActivityScope
    abstract fun bindActivity(activity: DetailActivity): AppCompatActivity

    @Binds
    @IntoMap
    @ActivityViewModelKey(DetailActivityViewModel::class)
    @ActivityScope
    abstract fun bindViewModel(activityViewModel: DetailActivityViewModel): BaseActivityViewModel

    @Module
    companion object {

        @Provides
        @ActivityScope
        @JvmStatic
        fun provideDetailActivityRemoteData(apiInterface: BitcoinTickerApiInterface) =
            DetailActivityRemoteData(apiInterface)

        @Provides
        @ActivityScope
        @JvmStatic
        fun provideDetailActivityRepository(
            remote: DetailActivityRemoteData,
            scheduler: Scheduler,
            compositeDisposable: CompositeDisposable
        ) = DetailActivityRepository(remote, scheduler, compositeDisposable)
    }
}