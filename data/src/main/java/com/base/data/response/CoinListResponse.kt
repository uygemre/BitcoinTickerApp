package com.base.data.response

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class CoinListResponse(
    val id: String? = "",
    val symbol: String? = "",
    val name: String? = ""
): Parcelable