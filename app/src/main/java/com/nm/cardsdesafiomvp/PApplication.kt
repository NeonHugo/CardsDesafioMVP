package com.nm.cardsdesafiomvp

import androidx.multidex.MultiDexApplication
import com.nm.cardsdesafiomvp.di.AppComponent.getAllModules
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.KoinComponent
import org.koin.core.context.startKoin

open class PApplication : MultiDexApplication(), KoinComponent {

    override fun onCreate() {
        super.onCreate()
        initDI()
    }

    private fun initDI() {
        startKoin {
            androidLogger()
            androidContext(this@PApplication)
            modules(getAllModules())
        }
    }

}