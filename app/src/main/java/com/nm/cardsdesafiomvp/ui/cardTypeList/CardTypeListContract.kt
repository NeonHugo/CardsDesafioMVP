package com.nm.cardsdesafiomvp.ui.cardTypeList

import com.nm.domain.entity.CardTypes

interface CardTypeListContract {

    interface View {
        fun loadCardTypesList(cardTypes: CardTypes)
        fun loadError(message: String)
    }

    interface Presenter {
        fun getCardTypesList()
        fun onDestroy()
    }
}