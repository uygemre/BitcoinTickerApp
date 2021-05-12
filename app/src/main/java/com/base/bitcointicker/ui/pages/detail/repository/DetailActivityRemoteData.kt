package com.base.bitcointicker.ui.pages.detail.repository

import com.base.data.request.BitcoinTickerApiInterface

class DetailActivityRemoteData(
    private val apiInterface: BitcoinTickerApiInterface
) : DetailActivityContract.Remote {
}