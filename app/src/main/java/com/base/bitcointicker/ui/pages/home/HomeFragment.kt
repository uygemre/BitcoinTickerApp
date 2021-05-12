package com.base.bitcointicker.ui.pages.home

import android.os.Bundle
import android.view.View
import com.base.component.BitcoinTickerRecyclerviewAdapter
import com.base.component.ui.coinsearch.CoinSearchDTO
import com.base.core.extensions.afterTextChanged
import com.base.core.extensions.gone
import com.base.core.extensions.setup
import com.base.core.extensions.visibile
import com.base.core.networking.DataFetchResult
import com.base.data.response.CoinListResponse
import com.base.bitcointicker.R
import com.base.bitcointicker.ui.base.fragment.BaseViewModelFragment
import com.base.bitcointicker.ui.common.navigation.NavigationHelper
import com.base.bitcointicker.ui.pages.home.viewmodel.HomeFragmentViewModel
import kotlinx.android.synthetic.main.fragment_home.*
import java.util.*
import javax.inject.Inject

class HomeFragment : BaseViewModelFragment<HomeFragmentViewModel>() {

    override val viewModelClass = HomeFragmentViewModel::class.java
    override val layoutViewRes = R.layout.fragment_home

    @Inject
    lateinit var adapter: BitcoinTickerRecyclerviewAdapter
    private var filter: List<CoinListResponse>? = emptyList()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        home_page_recyclerview.setup(adapter = adapter.getAdapter())

        val navigationHelper = NavigationHelper(requireActivity())
        adapter.getAdapter().itemClickListener = { item, position ->
            when (item) {
                is CoinSearchDTO -> {
                    navigationHelper.navigate(item)
                }
            }
        }

        et_search.afterTextChanged {
            if (et_search.text.length > 3) {
                filter = emptyList()
                viewModel.coinListMutableLiveData.value.let { _list ->
                    _list?.map {
                        if (it.id?.contains(et_search.text.toString())!!) {
                            filter = _list.filter { _response ->
                                _response.id?.toLowerCase(Locale.getDefault())!!.contains(
                                    et_search.text.toString().toLowerCase(
                                        Locale.getDefault()
                                    )
                                )
                            }

                            tv_no_data_found.gone()
                        } else if (it.name?.contains(et_search.text.toString())!!) {
                            filter = _list.filter { _response ->
                                _response.name?.toLowerCase(Locale.getDefault())!!.contains(
                                    et_search.text.toString().toLowerCase(
                                        Locale.getDefault()
                                    )
                                )
                            }
                            tv_no_data_found.gone()
                        }
                    }
                }
                if (!filter.isNullOrEmpty()) {
                    adapter.getAdapter().updateAllItems(viewModel.searchResult(filter))
                    tv_no_data_found.gone()
                } else {
                    tv_no_data_found.visibile()
                    adapter.getAdapter().updateAllItems(emptyList())
                }
            } else {
                adapter.getAdapter().updateAllItems(emptyList())
                tv_no_data_found.gone()
            }
        }
    }

    override fun onResume() {
        super.onResume()
        bindObserver()
    }

    private fun bindObserver() {
        viewModel.getCoinList()
        viewModel.getCoinListPublishSubject.subscribe {
            when (it) {
                is DataFetchResult.Progress -> {
                }
                is DataFetchResult.Failure -> {
                }
                is DataFetchResult.Success -> {
                    viewModel.coinListMutableLiveData.value = it.data
                }
            }
        }.isDisposed
    }
}