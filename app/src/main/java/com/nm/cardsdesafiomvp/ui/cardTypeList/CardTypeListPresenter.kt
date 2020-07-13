package com.nm.cardsdesafiomvp.ui.cardTypeList

import com.nm.commons.base.BasePresenter
import com.nm.domain.usecase.CardTypeListUseCase
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class CardTypeListPresenter(
    private val mView: CardTypeListContract.View,
    private val cardTypeListUseCase: CardTypeListUseCase
) : BasePresenter(), CardTypeListContract.Presenter {

    override fun getCardTypesList() {
        rxManager.subscribeAndCallAction(
            cardTypeListUseCase.getCardTypes()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe {
                    mView.loadStatus()
                },
            {
                mView.loadCardTypesList(it)
            },
            {
                mView.loadError()
            }
        )
    }
}