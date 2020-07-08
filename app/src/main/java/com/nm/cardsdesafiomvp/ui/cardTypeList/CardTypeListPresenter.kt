package com.nm.cardsdesafiomvp.ui.cardTypeList

import com.nm.domain.usecase.CardTypeListUseCase
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class CardTypeListPresenter(
    private val mView: CardTypeListContract.View,
    private val cardTypeListUseCase: CardTypeListUseCase
) : CardTypeListContract.Presenter {

    private var compositeDisposable = CompositeDisposable()

    override fun getCardTypesList() {
        compositeDisposable.add(cardTypeListUseCase.getCardTypes()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe {
                mView.loadStatus()
            }
            .subscribe(
                {
                    mView.loadCardTypesList(it)
                },
                {
                    mView.loadError()
                }
            )
        )
    }

    override fun onDestroy() {
        compositeDisposable.dispose()
    }
}