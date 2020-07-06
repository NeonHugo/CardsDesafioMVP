package com.nm.cardsdesafiomvp.di

import com.nm.cardsdesafiomvp.ui.cardList.CardListContract
import com.nm.cardsdesafiomvp.ui.cardList.CardListPresenter
import com.nm.commons.net.data.RetrofitBuild.makeService
import com.nm.data.mapper.CardTypeSchemaToCardTypeMapper
import com.nm.data.repository.*
import com.nm.data.services.RapiService
import com.nm.domain.repository.CardTypeRepository
import com.nm.domain.usecase.*

import com.nm.cardsdesafiomvp.ui.cardTypeList.CardTypeListContract
import com.nm.cardsdesafiomvp.ui.cardTypeList.CardTypeListPresenter
import com.nm.data.mapper.CardSchemaToCardMapper

import org.koin.core.qualifier.named
import org.koin.dsl.module

object DataModules {

    private const val rapiService = "RapiService"
    private const val cardTypeSchemaToCardTypeMapper = "CardTypeSchemaToCardTypeMapper"
    private const val cardSchemaToCardMapper = "CardSchemaToCardMapper"

    val serviceModules = module {
        single(named(rapiService)) { makeService<RapiService>("https://omgvamp-hearthstone-v1.p.rapidapi.com/") }

        factory<CardTypeListContract.Presenter> { (view: CardTypeListContract.View) ->
            CardTypeListPresenter(view, get())
        }

        factory<CardListContract.Presenter> { (view: CardListContract.View) ->
            CardListPresenter(view, get())
        }
    }

    val dataSourceModules = module {
        single<CardTypeRemoteDataSource> {
            CardTypeRemoteDataSourcImpl(
                get(named(rapiService))
            )
        }
    }

    val repositoryModules = module {
        single<CardTypeRepository> {
            CardTypeRepositoryImpl(
                get(), get(
                    named(
                        cardTypeSchemaToCardTypeMapper
                    )
                ),
                get(
                    named(
                        cardSchemaToCardMapper
                    )
                )
            )
        }
    }

    val useCaseModules = module {
        single<CardTypeListUseCase> { CardTypeListUseCaseImpl(get()) }
        single<CardListUseCase> { CardListUseCaseImpl(get()) }
    }

    val mapperModules = module {
        single(named(cardTypeSchemaToCardTypeMapper)) { CardTypeSchemaToCardTypeMapper() }
        single(named(cardSchemaToCardMapper)) { CardSchemaToCardMapper() }
    }

}
