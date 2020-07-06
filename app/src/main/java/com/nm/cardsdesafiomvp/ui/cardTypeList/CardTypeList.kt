package com.nm.cardsdesafiomvp.ui.cardTypeList

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.nm.domain.entity.CardTypes
import com.nm.cardsdesafiomvp.R
import org.koin.android.ext.android.inject
import org.koin.core.parameter.parametersOf

class CardTypeList : AppCompatActivity(), CardTypeListContract.View {

    val presenter: CardTypeListContract.Presenter by inject { parametersOf(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        iniActions()
    }

    private fun iniActions() {
        presenter.getCardTypesList()
    }


    override fun loadCardTypesList(cardTypes: CardTypes) {
        val i = 10
    }

    override fun loadError(message: String) {
        val i = 10
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.onDestroy()
    }
}