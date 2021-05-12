@file:Suppress("unused")

package com.base.bitcointicker.application

import android.app.Application
import android.content.Context
import com.base.component.ioc.module.RecyclerAdapterModule
import com.base.core.ioc.modules.*
import com.base.core.ioc.qualifiers.ApplicationContext
import com.base.bitcointicker.ioc.builder.ActivityBuilderModule
import com.base.bitcointicker.ioc.modules.database.bitcoin.BitcoinTickerDatabaseModule
import dagger.Binds
import dagger.Module
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Module(
    includes = [
        NetworkModule::class,
        ImageModule::class,
        SystemServiceModule::class,
        TimberModule::class,
        UtilsModule::class,
        AndroidSupportInjectionModule::class,
        RecyclerAdapterModule::class,
        ActivityBuilderModule::class,
        BitcoinTickerDatabaseModule::class
    ]
)
abstract class ApplicationModule {

    @Binds
    @Singleton
    abstract fun bindApplication(application: com.base.bitcointicker.application.Application): Application

    @Binds
    @Singleton
    @ApplicationContext
    abstract fun bindApplicationContext(application: Application): Context

}