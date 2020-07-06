package com.nm.cardsdesafiomvp.ui.cardList

import com.nm.domain.entity.Card
import com.nm.domain.entity.CardTypes

interface CardListContract {

    interface View {
        fun loadCardList(cards: List<Card>)
        fun loadError(message: String)
    }

    interface Presenter {
        fun getCardList(type: String, param: String)
        fun onDestroy()
    }
}