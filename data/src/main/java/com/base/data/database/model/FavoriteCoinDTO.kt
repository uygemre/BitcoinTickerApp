package com.base.data.database.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.base.data.response.CoinDetailResponse
import com.base.data.response.Description
import com.base.data.response.Image
import com.base.data.response.MarketData

@Entity(tableName = "FavoriteCoins")
data class FavoriteCoinDTO(
    @PrimaryKey(autoGenerate = true) var id: Int = 0,
    var coinId: String?,
    val symbol: String? = "",
    val name: String? = ""
)