package com.nm.domain.usecase

import com.nm.domain.entity.CardTypes
import com.nm.domain.repository.CardTypeRepository
import io.reactivex.Single
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class CardTypeListUseCaseImplTest {

    @Mock
    lateinit var repository: CardTypeRepository

    private lateinit var cardTypeListUseCase: CardTypeListUseCase

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)

        cardTypeListUseCase = CardTypeListUseCaseImpl(repository)
    }

    @Test
    fun getCardTypeList() {
        Mockito.`when`(repository.getCardTypes()).thenReturn(Single.just(createCardType()))


        val testObserver = cardTypeListUseCase.getCardTypes().test()

        testObserver.assertValue(createCardType())
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
        //
    }
}