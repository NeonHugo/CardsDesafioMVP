package com.nm.cardsdesafiomvp.ui.cardTypeList

import com.nm.cardsdesafiomvp.ui.RxImmediateSchedulerRule
import com.nm.domain.entity.CardTypes
import com.nm.domain.usecase.CardTypeListUseCase
import io.reactivex.Single
import io.reactivex.android.plugins.RxAndroidPlugins
import io.reactivex.plugins.RxJavaPlugins
import io.reactivex.schedulers.Schedulers
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.runner.Description
import org.junit.runners.model.Statement
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations

class CardTypeListPresenterTest {

    @Rule
    @JvmField
    var testSchedulerRule = RxImmediateSchedulerRule()

    @Mock
    private lateinit var mView: CardTypeListContract.View

    @Mock
    private lateinit var useCase: CardTypeListUseCase

    private lateinit var cardTypeListPresenter: CardTypeListPresenter

    @Before
    fun setupTest() {
        MockitoAnnotations.initMocks(this)

        cardTypeListPresenter = CardTypeListPresenter(mView, useCase)
    }


    @Test
    fun cardTypeListSuccessful() {
        Mockito.`when`(useCase.getCardTypes()).thenReturn(Single.just(createCardType()))

        cardTypeListPresenter.getCardTypesList()

        verify(mView).loadCardTypesList(createCardType())
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

    @Test
    fun cardTypeListUnSuccessful() {
        Mockito.`when`(useCase.getCardTypes()).thenReturn(
            Single.error(Throwable("Deu Ruim"))
        )

        cardTypeListPresenter.getCardTypesList()

        verify(mView).loadError()
    }

    @After
    fun tearDown() {

    }
}