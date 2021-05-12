package com.base.bitcointicker.ui.pages.favorites

import android.annotation.SuppressLint
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import androidx.lifecycle.Observer
import com.base.component.BitcoinTickerRecyclerviewAdapter
import com.base.component.ui.coinsearch.CoinSearchDTO
import com.base.core.extensions.setup
import com.base.bitcointicker.R
import com.base.bitcointicker.ui.base.fragment.BaseViewModelFragment
import com.base.bitcointicker.ui.common.navigation.NavigationHelper
import com.base.bitcointicker.ui.pages.favorites.viewmodel.FavoritesFragmentViewModel
import kotlinx.android.synthetic.main.fragment_favorites.*
import javax.inject.Inject

class FavoritesFragment : BaseViewModelFragment<FavoritesFragmentViewModel>() {

    override val viewModelClass = FavoritesFragmentViewModel::class.java
    override val layoutViewRes = R.layout.fragment_favorites

    @Inject
    lateinit var adapter: BitcoinTickerRecyclerviewAdapter
    private var mainHandler = Handler(Looper.getMainLooper())
    private val updatePage = Runnable { bindObserver() }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        btn_clear.setOnClickListener {
            viewModel.deleteAllFavorites()
            mainHandler.post(updatePage)
        }

        rv_favorites.setup(adapter = adapter.getAdapter())
        val navigationHelper = NavigationHelper(requireActivity())
        adapter.getAdapter().itemClickListener = { _item, _position ->
            when (_item) {
                is CoinSearchDTO -> navigationHelper.navigate(_item)
            }
        }
    }

    override fun onPause() {
        super.onPause()
        mainHandler.removeCallbacks(updatePage)
    }

    override fun onResume() {
        super.onResume()
        bindObserver()
    }

    @SuppressLint("CheckResult")
    private fun bindObserver() {
        viewModel.getAllFavoritesData()
        viewModel.getAllFavoritesPublishSubject.observe(this, Observer {
        })
        viewModel.allFavoritesPublishSubject.subscribe {
            adapter.getAdapter().updateAllItems(it.toMutableList().reversed())
        }
    }
}