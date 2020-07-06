package com.nm.domain.usecase

import com.nm.domain.entity.Card
import com.nm.domain.entity.CardTypes
import io.reactivex.Single


interface CardListUseCase {
    fun getCardList(type: String, param: String) : Single<List<Card>>
}