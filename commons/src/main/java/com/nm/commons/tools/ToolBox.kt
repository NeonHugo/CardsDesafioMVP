package com.nm.commons.tools

import android.graphics.Color
import java.util.*

fun colorP(): Int {
    val rnd = Random()
    return Color.argb(
        255, rnd.nextInt(255), rnd.nextInt(255),
        rnd.nextInt(255)
    )
}