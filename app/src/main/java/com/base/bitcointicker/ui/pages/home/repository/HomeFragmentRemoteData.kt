package com.base.bitcointicker.ui.pages.home.repository

import com.base.data.request.BitcoinTickerApiInterface
import com.base.data.response.CoinListResponse
import io.reactivex.Single

class HomeFragmentRemoteData(
    private val apiInterface: BitcoinTickerApiInterface
) : HomeFragmentContract.Remote {

    override fun getCoinList(): Single<List<CoinListResponse>> = apiInterface.getCoinList()
}