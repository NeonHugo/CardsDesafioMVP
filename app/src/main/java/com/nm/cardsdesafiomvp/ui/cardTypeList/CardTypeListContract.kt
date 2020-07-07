package com.nm.cardsdesafiomvp.ui.cardTypeList

import com.nm.domain.entity.CardTypes

interface CardTypeListContract {

    interface View {
        fun loadCardTypesList(cardTypes: CardTypes)
        fun loadStatus()
        fun loadError()
    }

    interface Presenter {
        fun getCardTypesList()
        fun onDestroy()
    }
}