package com.base.data.database.dao

import androidx.room.Dao
import androidx.room.Query
import com.base.data.database.model.FavoriteCoinDTO
import io.reactivex.Single

@Dao
interface FavoriteCoinDao : BaseDao<FavoriteCoinDTO> {

    @Query("DELETE FROM FavoriteCoins WHERE coinId = :coinId ")
    fun deleteById(coinId: String)

    @Query("SELECT * FROM FavoriteCoins")
    fun getAllCoin(): Single<List<FavoriteCoinDTO>>

    @Query("DELETE FROM FavoriteCoins")
    fun deleteAll()

}