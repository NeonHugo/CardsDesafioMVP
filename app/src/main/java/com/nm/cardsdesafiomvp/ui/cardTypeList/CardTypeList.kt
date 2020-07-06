package com.nm.cardsdesafiomvp.ui.cardTypeList

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.nm.cardsdesafiomvp.R
import com.nm.cardsdesafiomvp.ui.cardList.CardList
import com.nm.cardsdesafiomvp.ui.cardTypeList.adapter.SectionAdapter
import com.nm.data.mapper.CardTypeToSectionListMapper
import com.nm.domain.entity.CardTypes
import com.nm.domain.entity.Option
import kotlinx.android.synthetic.main.content_main.*
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
        val manager = LinearLayoutManager(this)
        rvSections.layoutManager = manager
        rvSections.adapter = SectionAdapter(
            CardTypeToSectionListMapper().transform(cardTypes),
            this,
            ::itemSelected
        )
    }

    private fun itemSelected(option: Option) {
        startActivity(CardList.newInstance(this, option))
    }

    override fun loadError(message: String) {
        val i = 10
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.onDestroy()
    }
}