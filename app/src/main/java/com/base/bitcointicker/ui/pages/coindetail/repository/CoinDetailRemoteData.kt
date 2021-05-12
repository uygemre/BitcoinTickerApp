package com.base.bitcointicker.ui.pages.coindetail.repository

import com.base.data.request.BitcoinTickerApiInterface
import com.base.data.response.CoinDetailResponse
import io.reactivex.Single

class CoinDetailRemoteData(
    private val apiInterface: BitcoinTickerApiInterface
) : CoinDetailFragmentContract.Remote {

    override fun getCoinDetail(id: String?): Single<CoinDetailResponse> =
        apiInterface.getCoinDetail(id)
}