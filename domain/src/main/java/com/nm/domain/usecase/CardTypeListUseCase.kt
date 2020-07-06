package com.nm.domain.usecase

import com.nm.domain.entity.CardTypes
import io.reactivex.Single


interface CardTypeListUseCase {
    fun getCardTypes(): Single<CardTypes>
}