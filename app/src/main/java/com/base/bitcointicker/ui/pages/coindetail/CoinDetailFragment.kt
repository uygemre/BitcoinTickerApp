package com.base.bitcointicker.ui.pages.coindetail

import android.annotation.SuppressLint
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.widget.ImageButton
import androidx.lifecycle.Observer
import com.base.component.BitcoinTickerRecyclerviewAdapter
import com.base.component.ui.coindetail.CoinDetailDTO
import com.base.core.helpers.LocalPrefManager
import com.base.core.networking.DataFetchResult
import com.base.bitcointicker.R
import com.base.bitcointicker.ui.base.fragment.BaseViewModelFragment
import com.base.bitcointicker.ui.pages.coindetail.viewmodel.CoinDetailFragmentViewModel
import com.base.core.extensions.setup
import com.base.data.database.model.FavoriteCoinDTO
import kotlinx.android.synthetic.main.fragment_coin_detail.*
import kotlinx.android.synthetic.main.layout_toolbar.*
import javax.inject.Inject

class CoinDetailFragment : BaseViewModelFragment<CoinDetailFragmentViewModel>() {

    override val viewModelClass = CoinDetailFragmentViewModel::class.java
    override val layoutViewRes = R.layout.fragment_coin_detail

    @Inject
    lateinit var adapter: BitcoinTickerRecyclerviewAdapter

    @Inject
    lateinit var localPrefManager: LocalPrefManager

    private var id: String? = null
    private var mainHandler = Handler(Looper.getMainLooper())
    private val updatePage = object : Runnable {
        override fun run() {
            bindObserver()
            mainHandler.postDelayed(this, 60000)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arguments?.let {
            it.getString("id")?.let { _id ->
                id = _id
            }
        }

        btn_back.setOnClickListener {
            activity?.onBackPressed()
        }

        adapter.getAdapter().itemViewClickListener = { _view, _item, _position ->
            when (_item) {
                is CoinDetailDTO -> {
                    if (_view.id == R.id.ib_favorite) {
                        val imgFavorites = _view.findViewById<ImageButton>(R.id.ib_favorite)
                        if (localPrefManager.pull(_item.id ?: "", "") == "") {
                            viewModel.insertFav(
                                FavoriteCoinDTO(
                                    id = 0,
                                    coinId = _item.id,
                                    name = _item.name,
                                    symbol = _item.symbol
                                )
                            )
                            localPrefManager.push(_item.id ?: "", _item.id)
                            imgFavorites.setBackgroundResource(R.drawable.ic_favorite_selected)
                        } else {
                            viewModel.deleteFavoritesById(_item.id ?: "")
                            localPrefManager.push(_item.id ?: "", "")
                            imgFavorites.setBackgroundResource(R.drawable.ic_favorite_unselected)
                        }
                    }
                }
            }
        }

        rv_coin_detail.setup(adapter = adapter.getAdapter())
    }

    override fun onResume() {
        super.onResume()
        mainHandler.post(updatePage)
    }

    override fun onPause() {
        super.onPause()
        mainHandler.removeCallbacks(updatePage)
    }

    @SuppressLint("CheckResult")
    fun bindObserver() {
        viewModel.getCoinDetail(id)
        viewModel.getCoinDetailPublishSubject.observe(this, Observer {
            when (it) {
                is DataFetchResult.Progress -> {
                }
                is DataFetchResult.Failure -> {
                }
                is DataFetchResult.Success -> {
                    tv_title.text = it.data.name
                }
            }
        })
        viewModel.gamesDetailPublishSubject.subscribe {
            adapter.getAdapter().updateAllItems(it)
        }
    }

    companion object {
        @JvmStatic
        fun newInstance(bundle: Bundle?) =
            CoinDetailFragment().apply {
                arguments = bundle
            }
    }
}