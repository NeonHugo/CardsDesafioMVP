package com.nm.data.repository

import com.nm.data.mapper.CardSchemaToCardMapper
import com.nm.data.mapper.CardTypeSchemaToCardTypeMapper
import com.nm.domain.entity.Card
import com.nm.domain.entity.CardTypes
import com.nm.domain.repository.CardTypeRepository
import io.reactivex.Single

class CardTypeRepositoryImpl(
    private val cardTypeRemoteDataSource: CardTypeRemoteDataSource,
    private val cardTypeMapper: CardTypeSchemaToCardTypeMapper,
    private val cardMapper: CardSchemaToCardMapper

) : CardTypeRepository {

    override fun getCardTypes(): Single<CardTypes> {
        return cardTypeRemoteDataSource.getCardTypesRemote().flatMap {
            return@flatMap Single.just(cardTypeMapper.transform(it))
        }
    }

    override fun getCardSearch(type: String, param: String): Single<List<Card>> {
        return cardTypeRemoteDataSource.getCardSearch(type, param).flatMap {
            return@flatMap Single.just(
                it.map { cardSchema -> cardMapper.transform(cardSchema) }
            )
        }
    }
}