package com.nm.cardsdesafiomvp.ui.cardList

import com.nm.domain.entity.Card

interface CardListContract {

    interface View {
        fun loadCardList(cards: List<Card>)
        fun loadStatus()
        fun loadError()
    }

    interface Presenter {
        fun getCardList(type: String, param: String)
        fun onDestroy()
    }
}