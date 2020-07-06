package com.nm.domain.usecase

import com.nm.domain.entity.CardTypes
import com.nm.domain.repository.CardTypeRepository
import io.reactivex.Observable
import io.reactivex.Single

class CardTypeListUseCaseImpl(
    private val cardTypeRepository: CardTypeRepository
) : CardTypeListUseCase {

    override fun getCardTypes(): Single<CardTypes> {
        return cardTypeRepository.getCardTypes()
    }
}