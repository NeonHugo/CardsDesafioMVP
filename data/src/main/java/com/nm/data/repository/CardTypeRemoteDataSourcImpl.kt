package com.nm.data.repository

import com.nm.data.model.CardSchema
import com.nm.data.model.CardTypesSchema
import com.nm.data.services.RapiService
import com.nm.domain.entity.Card
import io.reactivex.Single

class CardTypeRemoteDataSourcImpl(
    private val serviceAPI: RapiService
) : CardTypeRemoteDataSource {

    override fun getCardTypesRemote(): Single<CardTypesSchema> {
        return serviceAPI.getCardTypes()
    }

    override fun getCardSearch(type: String, param: String): Single<List<CardSchema>> {
        when (type) {
            "classes" -> {
                return serviceAPI.getCardByClass(param)
            }
            "factions" -> {
                return serviceAPI.getCardByFaction(param)
            }
            "qualities" -> {
                return serviceAPI.getCardByQuality(param)
            }
            "races" -> {
                return serviceAPI.getCardByRace(param)
            }
            else -> {
                return serviceAPI.getCardByType(param)
            }
        }
    }
}