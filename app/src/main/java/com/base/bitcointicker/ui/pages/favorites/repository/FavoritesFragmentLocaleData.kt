package com.base.bitcointicker.ui.pages.favorites.repository

import com.base.data.database.dao.FavoriteCoinDao
import com.base.data.database.model.FavoriteCoinDTO
import io.reactivex.Single
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class FavoritesFragmentLocaleData(
    private var favoritesDAO: FavoriteCoinDao
) : FavoritesFragmentContract.Locale {

    override fun getAllFavorites(): Single<List<FavoriteCoinDTO>> {
        return favoritesDAO.getAllCoin()
    }

    override fun deleteAllFavorites() {
        GlobalScope.launch {
            favoritesDAO.deleteAll()
        }
    }
}