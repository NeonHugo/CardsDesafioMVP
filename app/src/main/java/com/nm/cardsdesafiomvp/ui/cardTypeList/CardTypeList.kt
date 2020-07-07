package com.nm.cardsdesafiomvp.ui.cardTypeList

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.nm.cardsdesafiomvp.R
import com.nm.cardsdesafiomvp.ui.cardList.CardList
import com.nm.cardsdesafiomvp.ui.cardTypeList.adapter.SectionAdapter
import com.nm.commons.base.BaseActivity
import com.nm.data.mapper.CardTypeToSectionListMapper
import com.nm.domain.entity.CardTypes
import com.nm.domain.entity.Option
import kotlinx.android.synthetic.main.content_card_type.*
import kotlinx.android.synthetic.main.content_card_type.ivError
import kotlinx.android.synthetic.main.content_card_type.pbLoading
import org.koin.android.ext.android.inject
import org.koin.core.parameter.parametersOf

class CardTypeList : BaseActivity(), CardTypeListContract.View {

    val presenter: CardTypeListContract.Presenter by inject { parametersOf(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_card_type)

        iniActions()
        startProcess()
    }

    private fun iniActions() {
        ivError.setOnClickListener {
            startProcess()
        }
    }

    private fun  startProcess() {
        presenter.getCardTypesList()
    }

    override fun loadCardTypesList(cardTypes: CardTypes) {
        pbLoading.visibility = View.GONE
        rvSections.visibility = View.VISIBLE

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

    override fun loadStatus() {
        pbLoading.visibility = View.VISIBLE
        rvSections.visibility = View.GONE
        ivError.visibility = View.GONE
    }

    override fun loadError() {
        pbLoading.visibility = View.GONE
        ivError.visibility = View.VISIBLE
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.onDestroy()
    }
}