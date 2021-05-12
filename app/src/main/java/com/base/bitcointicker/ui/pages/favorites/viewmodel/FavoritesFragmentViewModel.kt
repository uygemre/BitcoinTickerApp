package com.base.bitcointicker.ui.pages.favorites.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.base.component.ui.coinsearch.CoinSearchDTO
import com.base.core.extensions.toLiveData
import com.base.core.networking.DataFetchResult
import com.base.core.ui.recyclerview.DisplayItem
import com.base.data.database.model.FavoriteCoinDTO
import com.base.bitcointicker.ui.base.viewmodel.BaseFragmentViewModel
import com.base.bitcointicker.ui.pages.favorites.repository.FavoritesFragmentRepository
import io.reactivex.subjects.PublishSubject
import javax.inject.Inject

class FavoritesFragmentViewModel @Inject constructor(
    private val repository: FavoritesFragmentRepository
) : BaseFragmentViewModel() {

    val allFavoritesPublishSubject = PublishSubject.create<MutableList<DisplayItem>>()

    val getAllFavoritesPublishSubject: LiveData<DataFetchResult<List<FavoriteCoinDTO>>> =
        Transformations.map(repository.getAllFavoritesPublishSubject.toLiveData(disposables)) { _data ->
            when (_data) {
                is DataFetchResult.Progress -> {
                }
                is DataFetchResult.Failure -> {
                }
                is DataFetchResult.Success -> {
                    getMyListData(_data.data)
                }
            }
            _data
        }

    private fun getMyListData(data: List<FavoriteCoinDTO>) {
        val myList = mutableListOf<DisplayItem>()
        data.map { _localData ->
            myList.add(
                CoinSearchDTO(
                    name = _localData.name,
                    symbol = _localData.symbol,
                    id = _localData.coinId
                )
            )
        }

        allFavoritesPublishSubject.onNext(myList)
    }

    fun getAllFavoritesData() {
        repository.getAllFavorites()
    }

    fun deleteAllFavorites() {
        repository.deleteAllFavorites()
    }
}