package com.base.bitcointicker.ui.pages.coindetail.repository

import com.base.data.database.dao.FavoriteCoinDao
import com.base.data.database.model.FavoriteCoinDTO
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class CoinDetailLocaleData(
    private var favoritesCoinDao: FavoriteCoinDao
) : CoinDetailFragmentContract.Locale {

    override fun insertFav(favData: FavoriteCoinDTO) {
        GlobalScope.launch {
            favoritesCoinDao.insert(favData)
        }
    }

    override fun deleteFavoritesById(favoriteCoinId: String) {
        GlobalScope.launch {
            favoritesCoinDao.deleteById(favoriteCoinId)
        }
    }
}