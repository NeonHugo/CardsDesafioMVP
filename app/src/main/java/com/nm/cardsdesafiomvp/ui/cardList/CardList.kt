package com.nm.cardsdesafiomvp.ui.cardList

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.TypedValue
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.nm.cardsdesafiomvp.R
import com.nm.cardsdesafiomvp.ui.cardList.adapter.CardAdapter
import com.nm.cardsdesafiomvp.ui.cardList.adapter.GridSpacingItemDecoration
import com.nm.commons.base.BaseActivity
import com.nm.domain.entity.Card
import com.nm.domain.entity.Option
import kotlinx.android.synthetic.main.content_card_list.*
import org.koin.android.ext.android.inject
import org.koin.core.parameter.parametersOf


class CardList : BaseActivity(), CardListContract.View {

    val presenter: CardListContract.Presenter by inject { parametersOf(this) }

    var p: Option? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_card_list)

        recoverParams()
        initActions()

        startProcess()
    }

    private fun recoverParams() {
        p = intent.extras?.getParcelable<Option>(SELECTION)
        tvHeader.text = p?.type
    }

    private fun initActions() {
        ivError.setOnClickListener {
            startProcess()
        }

        ivBack.setOnClickListener {
            onBackPressed()
        }
    }

    private fun startProcess() {
        presenter.getCardList(typeForCall(p!!.type), p!!.description)
    }

    override fun loadCardList(cards: List<Card>) {
        pbLoading.visibility = View.GONE
        rvCards.visibility = View.VISIBLE

        val manager = GridLayoutManager(this, 2)
        rvCards.layoutManager = manager
        rvCards.addItemDecoration(
            GridSpacingItemDecoration(
                2,
                dpToPx(20),
                true
            )
        )

        rvCards.adapter = CardAdapter(
            this,
            cards,
            ::itemSelected
        )
    }

    private fun itemSelected(card: Card) {
        Toast.makeText(
            this,
            card.name,
            Toast.LENGTH_SHORT
        ).show()

    }

    override fun loadStatus() {
        pbLoading.visibility = View.VISIBLE
        rvCards.visibility = View.GONE
        ivError.visibility = View.GONE
    }

    override fun loadError() {
        pbLoading.visibility = View.GONE
        ivError.visibility = View.VISIBLE
    }

    companion object {

        const val SELECTION = "selection"

        fun newInstance(context: Context, option: Option): Intent {
            val bundle = Bundle().apply {
                putParcelable(SELECTION, option)
            }

            return Intent(context, CardList::class.java).apply {
                putExtras(bundle)
            }
        }
    }

    private fun typeForCall(type: String): String {
        return when (type.toLowerCase()) {
            "classe" -> "classes"
            "faction" -> "factions"
            "quality" -> "qualities"
            "race" -> "races"
            "type" -> "types"
            else -> ""
        }
    }

    /**
     * Converting dp to pixel
     */
    private fun dpToPx(dp: Int): Int {
        val r = resources
        return Math.round(
            TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP,
                dp.toFloat(),
                r.displayMetrics
            )
        )
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.onDestroy()
    }
}