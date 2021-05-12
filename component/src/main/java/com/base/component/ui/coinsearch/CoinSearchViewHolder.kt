package com.base.component.ui.coinsearch

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.base.component.R
import com.base.core.ui.recyclerview.DisplayItem
import com.base.core.ui.recyclerview.ViewHolder
import com.base.core.ui.recyclerview.ViewHolderBinder
import com.base.core.ui.recyclerview.ViewHolderFactory
import javax.inject.Inject

class CoinSearchViewHolder(var view: View) : ViewHolder<CoinSearchDTO>(view) {

    private var tvCoinName = view.findViewById<TextView>(R.id.tv_coin_name)
    private var tvCoinSymbol = view.findViewById<TextView>(R.id.tv_coin_symbol)
    private var rootView = view.findViewById<CardView>(R.id.rootView)

    @SuppressLint("SetTextI18n")
    override fun bind(item: CoinSearchDTO) {
        item.let { _item ->
            tvCoinName.text = _item.name
            tvCoinSymbol.text = _item.symbol
            rootView.setOnClickListener {
                itemClickListener?.invoke(_item, adapterPosition)
            }
        }

    }

    class HolderFactory @Inject constructor() : ViewHolderFactory {
        override fun createViewHolder(parent: ViewGroup): RecyclerView.ViewHolder =
            CoinSearchViewHolder(
                LayoutInflater.from(parent.context).inflate(
                    R.layout.item_coin_search,
                    parent,
                    false
                )
            )
    }

    class BinderFactory @Inject constructor() : ViewHolderBinder {
        override fun bind(holder: RecyclerView.ViewHolder, item: DisplayItem) {
            (holder as CoinSearchViewHolder).bind(item as CoinSearchDTO)
        }
    }
}