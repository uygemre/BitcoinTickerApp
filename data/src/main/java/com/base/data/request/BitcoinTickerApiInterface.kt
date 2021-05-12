package com.base.data.request

import com.base.data.response.CoinDetailResponse
import com.base.data.response.CoinListResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path

interface BitcoinTickerApiInterface {

    @GET("https://api.coingecko.com/api/v3/coins/list")
    fun getCoinList(): Single<List<CoinListResponse>>

    @GET("https://api.coingecko.com/api/v3/coins/{id}")
    fun getCoinDetail(
        @Path("id") id: String?
    ): Single<CoinDetailResponse>

}