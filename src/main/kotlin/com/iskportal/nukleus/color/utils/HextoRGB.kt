package com.iskportal.com.iskportal.nukleus.color.utils

import com.iskportal.com.iskportal.nukleus.color.Color
import kotlin.math.roundToInt

fun String.toRGB(): Color {
    // Remove '#' if present
    val hex = if (this.startsWith("#")) this.substring(1) else this

        val r = hex.substring(0, 2).toInt(16)
        val g = hex.substring(2, 4).toInt(16)
        val b = hex.substring(4, 6).toInt(16)

        // Normalize RGB values by dividing by 255
        val rNormalized = ((r / 255.0) * 100).roundToInt() / 100.0
        val gNormalized = ((g / 255.0) * 100).roundToInt() / 100.0
        val bNormalized = ((b / 255.0) * 100).roundToInt() / 100.0

        return Color(rNormalized, gNormalized, bNormalized)
}