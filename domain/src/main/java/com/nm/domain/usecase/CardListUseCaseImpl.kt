package com.nm.domain.usecase

import com.nm.domain.entity.Card
import com.nm.domain.entity.CardTypes
import com.nm.domain.repository.CardTypeRepository
import io.reactivex.Observable
import io.reactivex.Single

class CardListUseCaseImpl(
    private val cardTypeRepository: CardTypeRepository
) : CardListUseCase {

    override fun getCardList(type: String, param: String): Single<List<Card>> {
        return cardTypeRepository.getCardSearch(type, param)
    }
}