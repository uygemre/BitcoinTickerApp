package com.base.bitcointicker.ui.pages.login.repository

import com.base.data.request.BitcoinTickerApiInterface

class LoginFragmentRemoteData(
    private val apiInterface: BitcoinTickerApiInterface
) : LoginFragmentContract.Remote {
}