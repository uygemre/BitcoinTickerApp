package com.base.bitcointicker.ui.pages.main.repository

import com.base.data.request.BitcoinTickerApiInterface

class MainActivityRemoteData(private val apiInterface: BitcoinTickerApiInterface) :
    MainActivityContract.Remote {
}
