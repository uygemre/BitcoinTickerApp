package com.base.bitcointicker.ui.pages.register.repository

import com.base.data.request.BitcoinTickerApiInterface

class RegisterFragmentRemoteData(
    private val apiInterface: BitcoinTickerApiInterface
) : RegisterFragmentContract.Remote {
}