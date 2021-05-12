@file:Suppress("unused")

package com.base.bitcointicker.ui.pages.main.ioc

import androidx.appcompat.app.AppCompatActivity
import com.base.core.ioc.scopes.ActivityScope
import com.base.core.networking.Scheduler
import com.base.bitcointicker.ioc.builder.FragmentBuilderModule
import com.base.bitcointicker.ioc.keys.ActivityViewModelKey
import com.base.bitcointicker.ui.pages.main.MainActivity
import com.base.bitcointicker.ui.pages.main.viewmodel.MainActivityViewModel
import com.base.bitcointicker.ui.base.activity.BaseActivityModule
import com.base.bitcointicker.ui.base.viewmodel.BaseActivityViewModel
import com.base.data.request.BitcoinTickerApiInterface
import com.base.bitcointicker.ioc.modules.database.bitcoin.BitcoinTickerService
import com.base.bitcointicker.ui.pages.main.repository.MainActivityRemoteData
import com.base.bitcointicker.ui.pages.main.repository.MainActivityRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap
import io.reactivex.disposables.CompositeDisposable

@Module(includes = [BaseActivityModule::class, FragmentBuilderModule::class, BitcoinTickerService::class])
abstract class MainActivityModule {

    @Binds
    @ActivityScope
    abstract fun bindActivity(activity: MainActivity): AppCompatActivity

    @Binds
    @IntoMap
    @ActivityViewModelKey(MainActivityViewModel::class)
    @ActivityScope
    abstract fun bindViewModel(activityViewModel: MainActivityViewModel): BaseActivityViewModel

    @Module
    companion object {

        @Provides
        @ActivityScope
        @JvmStatic
        fun provideMainActivityRemoteData(apiInterface: BitcoinTickerApiInterface) =
            MainActivityRemoteData(apiInterface)

        @Provides
        @ActivityScope
        @JvmStatic
        fun provideMainActivityRepository(
            remote: MainActivityRemoteData,
            scheduler: Scheduler,
            compositeDisposable: CompositeDisposable
        ) = MainActivityRepository(remote, scheduler, compositeDisposable)
    }
}
