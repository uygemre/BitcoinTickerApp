package com.base.bitcointicker.ioc.modules.database.bitcoin

import com.base.core.ioc.scopes.ActivityScope
import com.base.data.request.BitcoinTickerApiInterface
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

@Module
abstract class BitcoinTickerService {

    @Module
    companion object {

        @Provides
        @ActivityScope
        @JvmStatic
        fun provideCoinsInterface(retrofit: Retrofit): BitcoinTickerApiInterface =
            retrofit.create(BitcoinTickerApiInterface::class.java)

    }
}