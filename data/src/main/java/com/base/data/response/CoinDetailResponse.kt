package com.base.data.response

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class CoinDetailResponse(
    val id: String? = "",
    val symbol: String? = "",
    val name: String? = "",
    val hashing_algorithm: String? = "",
    val description: Description?,
    val image: Image?,
    val market_data: MarketData?
) : Parcelable

@Parcelize
data class Description(
    val en: String? = ""
) : Parcelable

@Parcelize
data class Image(
    val thumb: String? = "",
    val small: String? = "",
    val large: String? = ""
) : Parcelable

@Parcelize
data class MarketData(
    val current_price: CurrentPrice?,
    val price_change_percentage_24h: Double? = 0.0
) : Parcelable

@Parcelize
data class CurrentPrice(
    val usd: String? = "",
    @SerializedName("try")val tl: String? = ""
) : Parcelable