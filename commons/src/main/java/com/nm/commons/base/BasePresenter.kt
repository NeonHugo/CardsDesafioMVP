package com.nm.commons.base

import com.nm.commons.rx.RxSubscriberManager

open class BasePresenter : IPresenter {

    protected var rxManager = RxSubscriberManager()

    override fun onDestroy() {
        rxManager.onCleared()
    }
}