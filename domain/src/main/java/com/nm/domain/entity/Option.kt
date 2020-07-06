package com.nm.domain.entity

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Option(
    val type: String,
    val description: String
) : Parcelable