package com.nm.data.mapper

import com.nm.commons.net.data.Mapper
import com.nm.data.model.CardSchema
import com.nm.data.model.CardTypesSchema
import com.nm.domain.entity.Card
import com.nm.domain.entity.CardTypes

class CardSchemaToCardMapper : Mapper<CardSchema, Card>() {
    override fun transform(item: CardSchema): Card {
        return Card(
            cardId = item.cardId,
            cardSet = item.cardSet,
            dbfId = item.dbfId,
            health = item.health,
            img = item.img,
            imgGold = item.imgGold,
            locale = item.locale,
            name = item.name,
            type = item.type
        )
    }
}