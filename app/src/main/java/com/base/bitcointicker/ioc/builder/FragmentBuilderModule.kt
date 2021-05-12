package com.base.bitcointicker.ioc.builder

import com.base.core.ioc.scopes.FragmentScope
import com.base.bitcointicker.ui.pages.home.HomeFragment
import com.base.bitcointicker.ui.pages.home.ioc.HomeFragmentModule
import com.base.bitcointicker.ui.pages.favorites.FavoritesFragment
import com.base.bitcointicker.ui.pages.favorites.ioc.FavoritesFragmentModule
import com.base.bitcointicker.ui.pages.coindetail.CoinDetailFragment
import com.base.bitcointicker.ui.pages.coindetail.ioc.CoinDetailFragmentModule
import com.base.bitcointicker.ui.pages.login.LoginFragment
import com.base.bitcointicker.ui.pages.login.ioc.LoginFragmentModule
import com.base.bitcointicker.ui.pages.register.RegisterFragment
import com.base.bitcointicker.ui.pages.register.ioc.RegisterFragmentModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentBuilderModule {

    @FragmentScope
    @ContributesAndroidInjector(modules = [HomeFragmentModule::class])
    abstract fun contributeHomeFragment(): HomeFragment

    @FragmentScope
    @ContributesAndroidInjector(modules = [FavoritesFragmentModule::class])
    abstract fun contributeFavoritesFragment(): FavoritesFragment

    @FragmentScope
    @ContributesAndroidInjector(modules = [CoinDetailFragmentModule::class])
    abstract fun contributeCoinDetailFragment(): CoinDetailFragment

    @FragmentScope
    @ContributesAndroidInjector(modules = [LoginFragmentModule::class])
    abstract fun contributeLoginFragment(): LoginFragment

    @FragmentScope
    @ContributesAndroidInjector(modules = [RegisterFragmentModule::class])
    abstract fun contributeRegisterFragment(): RegisterFragment

}