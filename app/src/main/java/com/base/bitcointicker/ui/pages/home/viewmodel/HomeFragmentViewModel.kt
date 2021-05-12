package com.base.bitcointicker.ui.pages.home.viewmodel

import androidx.lifecycle.MutableLiveData
import com.base.component.ui.coinsearch.CoinSearchDTO
import com.base.core.ui.recyclerview.DisplayItem
import com.base.data.response.CoinListResponse
import com.base.bitcointicker.ui.base.viewmodel.BaseFragmentViewModel
import com.base.bitcointicker.ui.pages.home.repository.HomeFragmentRepository
import javax.inject.Inject

class HomeFragmentViewModel @Inject constructor(
    var repository: HomeFragmentRepository
) : BaseFragmentViewModel() {

    val coinListMutableLiveData = MutableLiveData<List<CoinListResponse>>()
    val getCoinListPublishSubject = repository.coinListPublishSubject

    fun searchResult(filter: List<CoinListResponse>?): List<DisplayItem> {
        val list = arrayListOf<CoinSearchDTO>()
        filter?.map { _response ->
            list.add(
                CoinSearchDTO(
                    id = _response.id,
                    name = _response.name,
                    symbol = _response.symbol
                )
            )
        }

        return list
    }

    fun getCoinList() {
        repository.getCoinList()
    }
}