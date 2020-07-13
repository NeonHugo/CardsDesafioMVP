package com.nm.cardsdesafiomvp.ui.cardTypeList

import com.nm.commons.base.IPresenter
import com.nm.domain.entity.CardTypes

interface CardTypeListContract {

    interface View {
        fun loadCardTypesList(cardTypes: CardTypes)
        fun loadStatus()
        fun loadError()
    }

    interface Presenter : IPresenter {
        fun getCardTypesList()
    }
}