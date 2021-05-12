package com.base.bitcointicker.ioc.builder

import com.base.core.ioc.scopes.ActivityScope
import com.base.bitcointicker.ui.pages.detail.DetailActivity
import com.base.bitcointicker.ui.pages.detail.ioc.DetailActivityModule
import com.base.bitcointicker.ui.pages.main.MainActivity
import com.base.bitcointicker.ui.pages.main.ioc.MainActivityModule
import com.base.bitcointicker.ui.pages.splash.SplashActivity
import com.base.bitcointicker.ui.pages.splash.ioc.SplashActivityModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuilderModule {

    @ActivityScope
    @ContributesAndroidInjector(modules = [MainActivityModule::class])
    internal abstract fun bindMainActivity(): MainActivity

    @ActivityScope
    @ContributesAndroidInjector(modules = [DetailActivityModule::class])
    internal abstract fun bindDetailActivity(): DetailActivity

    @ActivityScope
    @ContributesAndroidInjector(modules = [SplashActivityModule::class])
    internal abstract fun bindSplashActivity(): SplashActivity

}