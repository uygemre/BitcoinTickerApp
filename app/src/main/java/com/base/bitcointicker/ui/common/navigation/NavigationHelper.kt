package com.base.bitcointicker.ui.common.navigation

import android.app.Activity
import android.os.Bundle
import com.base.component.ui.coinsearch.CoinSearchDTO
import com.base.core.extensions.openActivity
import com.base.core.ui.recyclerview.DisplayItem
import com.base.bitcointicker.ui.common.enums.IntentBundleKeyEnum
import com.base.bitcointicker.ui.pages.detail.DetailActivity


class NavigationHelper(var activity: Activity) {

    fun navigate(item: DisplayItem) {
        val bundle = Bundle()
        when (item) {
            is CoinSearchDTO -> {
                bundle.putString(
                    IntentBundleKeyEnum.DETAIL_KEY.toString(),
                    IntentBundleKeyEnum.DETAIL_COIN.toString()
                )
                bundle.putString("id", item.id)
            }
        }

        activity.openActivity(DetailActivity::class.java, bundle)
    }
}