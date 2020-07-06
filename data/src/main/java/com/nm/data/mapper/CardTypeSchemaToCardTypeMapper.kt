package com.nm.data.mapper

import com.nm.commons.net.data.Mapper
import com.nm.data.model.CardTypesSchema
import com.nm.domain.entity.CardTypes

class CardTypeSchemaToCardTypeMapper : Mapper<CardTypesSchema, CardTypes>() {
    override fun transform(item: CardTypesSchema): CardTypes {
        return CardTypes(
            classes = item.classes,
            factions = item.factions,
            qualities = item.qualities,
            races = item.races,
            types = item.types
        )
    }
}