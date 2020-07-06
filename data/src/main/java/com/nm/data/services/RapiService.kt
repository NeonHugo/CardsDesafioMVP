package com.nm.data.services

import com.nm.data.model.CardSchema
import com.nm.data.model.CardTypesSchema
import com.nm.domain.entity.Card
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path

interface RapiService {

    @GET("info")
    @Headers("x-rapidapi-key: 4uxMFPpwqDmshMUuW3hkLhvJwsr4p1qv85NjsnhLyNHxQRGZ5I")
    fun getCardTypes(): Single<CardTypesSchema>

    @GET("cards/classes/{id}")
    @Headers("x-rapidapi-key: 4uxMFPpwqDmshMUuW3hkLhvJwsr4p1qv85NjsnhLyNHxQRGZ5I")
    fun getCardByClass(@Path("id") id: String): Single<List<CardSchema>>

    @GET("cards/races/{id}")
    @Headers("x-rapidapi-key: 4uxMFPpwqDmshMUuW3hkLhvJwsr4p1qv85NjsnhLyNHxQRGZ5I")
    fun getCardByRace(@Path("id") id: String): Single<List<CardSchema>>

    @GET("cards/qualities/{id}")
    @Headers("x-rapidapi-key: 4uxMFPpwqDmshMUuW3hkLhvJwsr4p1qv85NjsnhLyNHxQRGZ5I")
    fun getCardByQuality(@Path("id") id: String): Single<List<CardSchema>>

    @GET("cards/types/{id}")
    @Headers("x-rapidapi-key: 4uxMFPpwqDmshMUuW3hkLhvJwsr4p1qv85NjsnhLyNHxQRGZ5I")
    fun getCardByType(@Path("id") id: String): Single<List<CardSchema>>

    @GET("cards/factions/{id}")
    @Headers("x-rapidapi-key: 4uxMFPpwqDmshMUuW3hkLhvJwsr4p1qv85NjsnhLyNHxQRGZ5I")
    fun getCardByFaction(@Path("id") id: String): Single<List<CardSchema>>
}
