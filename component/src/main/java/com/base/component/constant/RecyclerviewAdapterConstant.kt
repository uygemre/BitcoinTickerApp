package com.base.component.constant

import com.base.component.ui.coindetail.CoinDetailViewHolder
import com.base.component.ui.coinsearch.CoinSearchViewHolder

class RecyclerviewAdapterConstant {

    object TYPES {
        const val TYPE_NONE = 0
        const val TYPE_COIN_SEARCH = 1
        const val TYPE_COIN_DETAIL = 2
    }

    var binderFactoryMap = mutableMapOf(
        TYPES.TYPE_COIN_SEARCH to CoinSearchViewHolder.BinderFactory(),
        TYPES.TYPE_COIN_DETAIL to CoinDetailViewHolder.BinderFactory()
    )

    var holderFactoryMap = mutableMapOf(
        TYPES.TYPE_COIN_SEARCH to CoinSearchViewHolder.HolderFactory(),
        TYPES.TYPE_COIN_DETAIL to CoinDetailViewHolder.HolderFactory()
    )
}
