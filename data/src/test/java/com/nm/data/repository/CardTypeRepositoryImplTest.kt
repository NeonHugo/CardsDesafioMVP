package com.nm.data.repository

import com.nm.data.mapper.CardSchemaToCardMapper
import com.nm.data.mapper.CardTypeSchemaToCardTypeMapper
import com.nm.data.model.CardSchema
import com.nm.data.model.CardTypesSchema
import com.nm.domain.entity.Card
import com.nm.domain.entity.CardTypes
import com.nm.domain.repository.CardTypeRepository
import io.reactivex.Single
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class CardTypeRepositoryImplTest {

    @Mock
    private lateinit var remoteDataSource: CardTypeRemoteDataSource

    private lateinit var cardTypesRepository: CardTypeRepository

    @Before
    fun setupTest() {
        MockitoAnnotations.initMocks(this)

        cardTypesRepository =
            CardTypeRepositoryImpl(remoteDataSource, CardTypeSchemaToCardTypeMapper(), CardSchemaToCardMapper())
    }

    @Test
    fun cardTypesOk() {
        Mockito.`when`(remoteDataSource.getCardTypesRemote())
            .thenReturn(Single.just(createCardTypeSchema()))

        val testObserver = cardTypesRepository.getCardTypes().test()

        testObserver.assertValue(createCardType())
    }

    private fun createCardTypeSchema(): CardTypesSchema? {
        return CardTypesSchema(
            classes = listOf("Death Knight", "Druid"),
            factions = listOf("Horde", "Alliance"),
            locales = listOf("koKR", "ptBR"),
            qualities = listOf("Epic", "Legendary"),
            races = listOf("Murloc", "Beast"),
            sets = listOf("Basic", "Classic"),
            standard = listOf("Rise of Shadows", "Saviors of Uldum"),
            types = listOf("Hero", "Minion"),
            wild = listOf("Hall of Fame", "Naxxramas")
        )
    }

    private fun createCardType(): CardTypes {
        return CardTypes(
            classes = listOf("Death Knight", "Druid"),
            factions = listOf("Horde", "Alliance"),
            qualities = listOf("Epic", "Legendary"),
            races = listOf("Murloc", "Beast"),
            types = listOf("Hero", "Minion")
        )
    }

    @Test
    fun cardListOk() {
        Mockito.`when`(remoteDataSource.getCardSearch("races", "Dragon"))
            .thenReturn(Single.just(createCardListSchema()))

        val testObserver = cardTypesRepository.getCardSearch("races", "Dragon").test()

        testObserver.assertValue(createCardList())
    }

    private fun createCardListSchema(): List<CardSchema>? {
        return arrayListOf(
            CardSchema(
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