package com.nm.cardsdesafiomvp.di

import com.nm.cardsdesafiomvp.di.DataModules.dataSourceModules
import com.nm.cardsdesafiomvp.di.DataModules.mapperModules
import com.nm.cardsdesafiomvp.di.DataModules.repositoryModules
import com.nm.cardsdesafiomvp.di.DataModules.serviceModules
import com.nm.cardsdesafiomvp.di.DataModules.useCaseModules
import org.koin.core.module.Module

object AppComponent {

    fun getAllModules(): List<Module> =
        listOf(*getDataModules())

    private fun getDataModules(): Array<Module> {
        return arrayOf(serviceModules, dataSourceModules, repositoryModules, useCaseModules, mapperModules)
    }
}
