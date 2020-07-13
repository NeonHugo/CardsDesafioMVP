package com.nm.cardsdesafiomvp.ui.cardList

import com.nm.commons.base.BasePresenter
import com.nm.domain.usecase.CardListUseCase
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class CardListPresenter(
    private val mView: CardListContract.View,
    private val cardListUseCase: CardListUseCase
) : BasePresenter(),  CardListContract.Presenter {

    override fun getCardList(type: String, param: String) {
        rxManager.subscribeAndCallAction(
            cardListUseCase.getCardList(type, param)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe {
                    mView.loadStatus()
                }
            ,
            {
                mView.loadCardList(it)
            },
            {
                mView.loadError()
            }
        )
    }
}