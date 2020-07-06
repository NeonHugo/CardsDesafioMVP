package com.nm.data.mapper

import com.nm.data.model.CardSchema
import com.nm.data.model.CardTypesSchema
import com.nm.domain.entity.Card
import com.nm.domain.entity.CardTypes
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class CardSchemaToCardMapperTest {
    private lateinit var mapper: CardSchemaToCardMapper

    @Before
    fun setupTest() {
        mapper = CardSchemaToCardMapper()
    }

    @Test
    fun cardTypeMapperTest() {
        val cardType = mapper.transform(createCardSchema())

        assertEquals(createCard(), cardType)
    }

    private fun createCardSchema(): CardSchema {
        return CardSchema(
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
    }

    private fun createCard(): Card {
        return Card(
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
    }

    @After
    fun tearDown() {
        // Not Implemented
    }
}