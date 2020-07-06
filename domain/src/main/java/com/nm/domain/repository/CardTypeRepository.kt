package com.nm.domain.repository

import com.nm.domain.entity.Card
import com.nm.domain.entity.CardTypes
import io.reactivex.Single

interface CardTypeRepository {
    fun getCardTypes(): Single<CardTypes>
    fun getCardSearch(type: String, param: String) : Single<List<Card>>
}