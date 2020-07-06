package com.nm.data.repository

import com.nm.data.model.CardSchema
import com.nm.data.model.CardTypesSchema
import com.nm.domain.entity.Card
import io.reactivex.Single

interface CardTypeRemoteDataSource {
    fun getCardTypesRemote(): Single<CardTypesSchema>
    fun getCardSearch(type: String, param: String) : Single<List<CardSchema>>
}