package com.base.bitcointicker.ui.pages.favorites.repository

import com.base.data.request.BitcoinTickerApiInterface

class FavoritesFragmentRemoteData(
    private val apiInterface: BitcoinTickerApiInterface
) : FavoritesFragmentContract.Remote {
}