package com.base.component.ui.coindetail

import android.os.Parcelable
import com.base.component.constant.RecyclerviewAdapterConstant
import com.base.core.ui.recyclerview.DisplayItem
import com.base.data.response.Description
import com.base.data.response.Image
import com.base.data.response.MarketData
import kotlinx.android.parcel.Parcelize


@Parcelize
data class CoinDetailDTO(
    val id: String? = "",
    val symbol: String? = "",
    val name: String? = "",
    val hashing_algorithm: String? = "",
    val description: Description?,
    val image: Image?,
    val market_data: MarketData?
) : Parcelable,  DisplayItem(RecyclerviewAdapterConstant.TYPES.TYPE_COIN_DETAIL)