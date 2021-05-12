package com.base.component.ui.coindetail

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.base.component.R
import com.base.core.extensions.loadImage
import com.base.core.helpers.LocalPrefManager
import com.base.core.ui.recyclerview.DisplayItem
import com.base.core.ui.recyclerview.ViewHolder
import com.base.core.ui.recyclerview.ViewHolderBinder
import com.base.core.ui.recyclerview.ViewHolderFactory
import javax.inject.Inject

class CoinDetailViewHolder(var view: View) : ViewHolder<CoinDetailDTO>(view) {

    private lateinit var localPrefManager: LocalPrefManager

    private var imgGames = view.findViewById<ImageView>(R.id.img_coin)
    private var tvCoinName = view.findViewById<TextView>(R.id.tv_coin_name)
    private var tvDescription = view.findViewById<TextView>(R.id.tv_description)
    private var tvCurrentPrice = view.findViewById<TextView>(R.id.tv_current_price)
    private var tvCoinSymbol = view.findViewById<TextView>(R.id.tv_coin_symbol)
    private var tvPercentageLast24 = view.findViewById<TextView>(R.id.tv_percentage_last_24)
    private var imgButtonFavorite = view.findViewById<ImageButton>(R.id.ib_favorite)
    private var tvHashingAlgorithm = view.findViewById<TextView>(R.id.tv_hashing_algorithm)

    @SuppressLint("SetTextI18n")
    override fun bind(item: CoinDetailDTO) {
        item.let {  _response ->
            imgGames.loadImage(_response.image?.large ?: "")
            tvCoinName.text = _response.name
            tvDescription.text = _response.description?.en
            tvCurrentPrice.text = _response.market_data?.current_price?.usd
            tvCoinSymbol.text = _response.symbol
            tvHashingAlgorithm.text = _response.hashing_algorithm
            tvPercentageLast24.text = _response.market_data?.price_change_percentage_24h.toString()
            imgButtonFavorite.setOnClickListener {
                itemViewClickListener?.invoke(it, _response, adapterPosition)
            }
        }

        if (view.context is AppCompatActivity) {
            val holder = view.context as AppCompatActivity
            val sharedPreferences =
                holder.getSharedPreferences("bitcointicker", Context.MODE_PRIVATE)
            localPrefManager = LocalPrefManager(sharedPreferences)
        }

        if (localPrefManager.pull(item.id ?: "", "") == "") {
            imgButtonFavorite.setBackgroundResource(R.drawable.ic_favorite_unselected)
        } else {
            imgButtonFavorite.setBackgroundResource(R.drawable.ic_favorite_selected)
        }
    }

    class HolderFactory @Inject constructor() : ViewHolderFactory {
        override fun createViewHolder(parent: ViewGroup): RecyclerView.ViewHolder =
            CoinDetailViewHolder(
                LayoutInflater.from(parent.context).inflate(
                    R.layout.item_coin_detail,
                    parent,
                    false
                )
            )
    }

    class BinderFactory @Inject constructor() : ViewHolderBinder {
        override fun bind(holder: RecyclerView.ViewHolder, item: DisplayItem) {
            (holder as CoinDetailViewHolder).bind(item as CoinDetailDTO)
        }
    }
}