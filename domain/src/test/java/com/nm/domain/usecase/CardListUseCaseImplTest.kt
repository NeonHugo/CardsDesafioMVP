package com.nm.domain.usecase

import com.nm.domain.entity.Card
import com.nm.domain.entity.CardTypes
import com.nm.domain.repository.CardTypeRepository
import io.reactivex.Single
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class CardListUseCaseImplTest {

    @Mock
    lateinit var repository: CardTypeRepository

    private lateinit var cardListUseCase: CardListUseCase

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)

        cardListUseCase = CardListUseCaseImpl(repository)
    }

    @Test
    fun getCardTypeList() {
        Mockito.`when`(repository.getCardSearch("races", "Dragon")).thenReturn(Single.just(createCardList()))


        val testObserver = cardListUseCase.getCardList("races", "Dragon").test()

        testObserver.assertValue(createCardList())
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

    @After
    fun tearDown() {
        //
    }
}