package com.nm.cardsdesafiomvp.ui.cardList

import com.nm.commons.base.IPresenter
import com.nm.domain.entity.Card

interface CardListContract {

    interface View {
        fun loadCardList(cards: List<Card>)
        fun loadStatus()
        fun loadError()
    }

    interface Presenter : IPresenter{
        fun getCardList(type: String, param: String)
    }
}