package com.nm.cardsdesafiomvp.ui.cardList

import com.nm.cardsdesafiomvp.ui.RxImmediateSchedulerRule
import com.nm.domain.entity.Card
import com.nm.domain.usecase.CardListUseCase
import io.reactivex.Single
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class CardListPresenterTest {
    @Rule
    @JvmField
    var testSchedulerRule = RxImmediateSchedulerRule()

    @Mock
    private lateinit var mView: CardListContract.View

    @Mock
    private lateinit var useCase: CardListUseCase

    private lateinit var cardListPresenter: CardListPresenter

    @Before
    fun setupTest() {
        MockitoAnnotations.initMocks(this)

        cardListPresenter = CardListPresenter(mView, useCase)
    }

    @Test
    fun cardListSuccessful() {
        Mockito.`when`(useCase.getCardList("races", "Dragon")).thenReturn(Single.just(createCardList()))

        cardListPresenter.getCardList("races", "Dragon")

        Mockito.verify(mView).loadCardList(createCardList())
    }

    private fun createCardList(): List<Card> {
        return arrayListOf(
            Card(
                cardId = "BRM_022t",
                cardSet = "Blackrock Mountain",
                dbfId = "2436",
                health = 1,
                img = "http://wow.zamimg.com/images/hearthstone/cards/enus/original/BRM_022t.png",
                imgGold = "http://wow.zamimg.com/images/hearthstone/cards/enus/animated/BRM_022t_premium.gif",
                locale = "enUS",
                name = "Black Whelp",
                type = "Minion"
            )
        )
    }

    @Test
    fun cardTypeListUnSuccessful() {
        Mockito.`when`(useCase.getCardList("races", "Dragon")).thenReturn(
            Single.error(Throwable("Deu Ruim"))
        )

        cardListPresenter.getCardList("races", "Dragon")

        Mockito.verify(mView).loadError("Deu Ruim")
    }

    @After
    fun tearDown() {

    }
}