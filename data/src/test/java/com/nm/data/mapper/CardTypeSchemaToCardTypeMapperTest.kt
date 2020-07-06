package com.nm.data.mapper

import com.nm.data.model.CardTypesSchema
import com.nm.domain.entity.CardTypes
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class CardTypeSchemaToCardTypeMapperTest {
    private lateinit var mapper: CardTypeSchemaToCardTypeMapper

    @Before
    fun setupTest() {
        mapper = CardTypeSchemaToCardTypeMapper()
    }

    @Test
    fun cardTypeMapperTest() {
        val cardType = mapper.transform(createCardTypeSchema())

        assertEquals(createCardType(), cardType)
    }

    private fun createCardTypeSchema(): CardTypesSchema {
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

    @After
    fun tearDown() {
        // Not Implemented
    }
}