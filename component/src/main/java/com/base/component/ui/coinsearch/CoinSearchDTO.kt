package com.base.component.ui.coinsearch

import android.os.Parcelable
import com.base.component.constant.RecyclerviewAdapterConstant
import com.base.core.ui.recyclerview.DisplayItem
import kotlinx.android.parcel.Parcelize

@Parcelize
data class CoinSearchDTO(
    val id: String? = "",
    val symbol: String? = "",
    val name: String? = ""
) : Parcelable, DisplayItem(RecyclerviewAdapterConstant.TYPES.TYPE_COIN_SEARCH)
