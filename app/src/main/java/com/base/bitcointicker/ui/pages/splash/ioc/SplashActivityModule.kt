package com.base.bitcointicker.ui.pages.splash.ioc

import androidx.appcompat.app.AppCompatActivity
import com.base.bitcointicker.ioc.builder.FragmentBuilderModule
import com.base.bitcointicker.ioc.keys.ActivityViewModelKey
import com.base.bitcointicker.ioc.modules.database.bitcoin.BitcoinTickerService
import com.base.bitcointicker.ui.base.activity.BaseActivityModule
import com.base.bitcointicker.ui.base.viewmodel.BaseActivityViewModel
import com.base.bitcointicker.ui.pages.splash.SplashActivity
import com.base.bitcointicker.ui.pages.splash.viewmodel.SplashActivityViewModel
import com.base.core.ioc.scopes.ActivityScope
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module(includes = [BaseActivityModule::class, FragmentBuilderModule::class, BitcoinTickerService::class])
abstract class SplashActivityModule {

    @Binds
    @ActivityScope
    abstract fun bindActivity(activity: SplashActivity): AppCompatActivity

    @Binds
    @IntoMap
    @ActivityViewModelKey(SplashActivityViewModel::class)
    @ActivityScope
    abstract fun bindViewModel(activityViewModel: SplashActivityViewModel): BaseActivityViewModel

}