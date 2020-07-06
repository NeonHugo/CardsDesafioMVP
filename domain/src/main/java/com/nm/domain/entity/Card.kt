package com.nm.domain.entity

data class Card(
    val cardId: String,
    val cardSet: String,
    val dbfId: String,
    val health: Int,
    val img: String,
    val imgGold: String,
    val locale: String,
    val name: String,
    val type: String
)