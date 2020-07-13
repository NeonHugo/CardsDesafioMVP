package com.nm.commons.rx

import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.disposables.CompositeDisposable

class RxSubscriberManager  {

    private var mCompositeDisposable = CompositeDisposable()

    fun subscribeAndCallAction(
        completable: Completable,
        onSuccess: (() -> Unit) = {},
        onError: ((t: Throwable) -> Unit) = {}
    ): Completable {
        mCompositeDisposable.add(
            completable.subscribe(
                onSuccess,
                onError
            )
        )
        return completable
    }

    fun <T> subscribeAndCallAction(
        observable: Observable<T>,
        onSuccess: ((t: T) -> Unit),
        onError: ((t: Throwable) -> Unit) = {}
    ): Observable<T> {
        mCompositeDisposable.add(
            observable.subscribe(
                onSuccess,
                onError
            )
        )
        return observable
    }

    fun <T> subscribeAndCallAction(
        single: Single<T>,
        onSuccess: ((t: T) -> Unit),
        onError: ((t: Throwable) -> Unit) = {}
    ): Single<T> {
        mCompositeDisposable.add(
            single.subscribe(
                onSuccess,
                onError
            )
        )
        return single
    }

    fun onCleared() {
        mCompositeDisposable.dispose()
        mCompositeDisposable = CompositeDisposable()
    }
}