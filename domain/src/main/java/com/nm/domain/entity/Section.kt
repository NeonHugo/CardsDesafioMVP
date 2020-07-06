package com.nm.domain.entity

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Section(
    val description: String,
    val options: ArrayList<Option>
) : Parcelable