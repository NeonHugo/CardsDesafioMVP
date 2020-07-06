package com.nm.cardsdesafiomvp.ui.cardList

import com.nm.domain.usecase.CardListUseCase
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class CardListPresenter(
    private val mView: CardListContract.View,
    private val cardListUseCase: CardListUseCase
) : CardListContract.Presenter {

    private var compositeDisposable = CompositeDisposable()

    override fun getCardList(type: String, param: String) {
        compositeDisposable.add(cardListUseCase.getCardList(type, param)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    mView.loadCardList(it)
                },
                {
                    mView.loadError(it.message ?: "Deu Ruim")
                }
            )
        )
    }

    override fun onDestroy() {
        compositeDisposable.dispose()
    }
}