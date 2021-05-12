package com.base.bitcointicker.ui.pages.coindetail.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.base.component.ui.coindetail.CoinDetailDTO
import com.base.core.extensions.toLiveData
import com.base.core.networking.DataFetchResult
import com.base.core.ui.recyclerview.DisplayItem
import com.base.data.database.model.FavoriteCoinDTO
import com.base.bitcointicker.ui.base.viewmodel.BaseFragmentViewModel
import com.base.bitcointicker.ui.pages.coindetail.repository.CoinDetailFragmentRepository
import com.base.data.response.CoinDetailResponse
import io.reactivex.subjects.PublishSubject
import javax.inject.Inject

class CoinDetailFragmentViewModel @Inject constructor(
    private val repository: CoinDetailFragmentRepository
) : BaseFragmentViewModel() {

    private var detailList = mutableListOf<DisplayItem>()
    var gamesDetailPublishSubject = PublishSubject.create<List<DisplayItem>>()

    fun insertFav(dataFav: FavoriteCoinDTO) {
        repository.insertFav(dataFav)
    }

    fun deleteFavoritesById(favoriteCoinId: String) {
        repository.deleteFavoritesById(favoriteCoinId)
    }

    val getCoinDetailPublishSubject: LiveData<DataFetchResult<CoinDetailResponse>> =
        Transformations.map(repository.getCoinDetailPublishSubject.toLiveData(disposables)) {
            when (it) {
                is DataFetchResult.Progress -> {
                }
                is DataFetchResult.Failure -> {
                }
                is DataFetchResult.Success -> {
                    consumeDetail(it.data)
                }
            }

            it
        }

    private fun consumeDetail(response: CoinDetailResponse) {
        val list = mutableListOf<DisplayItem>()
        response.let {
            list.add(
                CoinDetailDTO(
                    image = it.image,
                    market_data = it.market_data,
                    description = it.description,
                    id = it.id,
                    symbol = it.symbol,
                    name = it.name
                )
            )
        }

        gamesDetailPublishSubject.onNext(list)
    }

    fun getCoinDetail(id: String?) {
        repository.getCoinDetail(id)
    }
}


