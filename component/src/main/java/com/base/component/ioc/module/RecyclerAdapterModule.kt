package com.base.component.ioc.module

import com.base.component.BitcoinTickerRecyclerviewAdapter
import dagger.Module
import dagger.Provides

@Module
class RecyclerAdapterModule {

	@Provides
	fun provideAdapter(): BitcoinTickerRecyclerviewAdapter {
		return BitcoinTickerRecyclerviewAdapter()
	}
}
