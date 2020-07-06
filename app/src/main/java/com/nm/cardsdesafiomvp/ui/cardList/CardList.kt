package com.nm.cardsdesafiomvp.ui.cardList

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.nm.cardsdesafiomvp.R
import com.nm.domain.entity.Card
import org.koin.android.ext.android.inject
import org.koin.core.parameter.parametersOf

class CardList: AppCompatActivity(), CardListContract.View {

    val presenter: CardListContract.Presenter by inject { parametersOf(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        iniActions()
    }

    private fun iniActions() {
        presenter.getCardList("races", "Dragon")
    }


    override fun loadCardList(cards: List<Card>) {
        var i = 10
    }

    override fun loadError(message: String) {
        var i = 10
    }
}