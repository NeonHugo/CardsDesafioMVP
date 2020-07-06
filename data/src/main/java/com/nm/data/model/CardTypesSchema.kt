package com.nm.data.model

data class CardTypesSchema(
    val classes: List<String>,
    val factions: List<String>,
    val locales: List<String>,
    val qualities: List<String>,
    val races: List<String>,
    val sets: List<String>,
    val standard: List<String>,
    val types: List<String>,
    val wild: List<String>
)