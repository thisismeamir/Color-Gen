package com.iskportal.com.iskportal.nukleus.color

import kotlin.math.absoluteValue
import kotlin.math.ln
import kotlin.math.pow

data class Color(
    var red: Double,
    var green: Double,
    var blue: Double,
) {

    operator fun plus(that: Color): Color {
        return Color(red + that.red, green + that.green, blue + that.blue)
    }

    operator fun minus(that: Color): Color {
        return Color(red - that.red, green - that.green, blue - that.blue)
    }

    operator fun times(that: Color): Color {
        return Color(red * that.red, green * that.green, blue * that.blue)
    }

    operator fun div(that: Color): Color {
        return Color(red / that.red, green / that.green, blue / that.blue)
    }

    operator fun plus(that: Double): Color {
        return Color(red + that, green + that, blue + that)
    }

    operator fun times(that: Double): Color {
        return Color(red * that, green * that, blue * that)
    }

    operator fun div(that: Double): Color {
        return Color(red / that, green / that, blue / that)
    }

    operator fun rangeTo(that: Color): Triple<ClosedFloatingPointRange<Double>, ClosedFloatingPointRange<Double>, ClosedFloatingPointRange<Double>> {
        return Triple(red..that.red, green..that.green, blue..that.blue)
    }


    fun zip(that: Color): Triple<Pair<Double, Double>, Pair<Double, Double>, Pair<Double, Double>> {
        return Triple(this.red to that.red, this.green to that.green, this.blue to that.blue)
    }


    // Should not be used
    operator fun compareTo(second: Color): Int {
        val isEqual = !this.zip(second).toList().map {
            it.first == it.second
        }.contains(element = false)
        return if (isEqual) 0 else 1
    }

    operator fun times(i: Int): Color {
        return Color(red * i, green * i, blue * i)
    }

    operator fun div(i: Int): Color {
        return Color(red / i, green / i, blue / i)

    }

    fun toAwtColor(): java.awt.Color {
        val colorsFloat: List<Float> = listOf(
            (red.toFloat() % 1).absoluteValue,
            (green.toFloat() % 1).absoluteValue,
            (blue.toFloat() % 1).absoluteValue
        )
        return java.awt.Color(
            colorsFloat[0],
            colorsFloat[1],
            colorsFloat[2],
        )
    }


    fun toList(): List<Double> {
        return listOf(red, green, blue)
    }

    fun pow(exponent: Double): Color {
        return this.toList().map { it.pow(exponent) }.toColor()
    }

    fun root(exponent: Double): Color {
        return this.pow(1 / exponent)
    }


    // Exponential function
    fun exp(lambda: Double): Color {
        return Color(
            red = kotlin.math.exp(lambda * red),
            green = kotlin.math.exp(lambda * green),
            blue = kotlin.math.exp(lambda * blue)
        )
    }

    // Logarithm function
    fun log(): Color {
        return Color(
            red = ln(red),
            green = ln(green),
            blue = ln(blue)
        )
    }

    // Sine function
    fun sin(): Color {
        return Color(
            red = kotlin.math.sin(red),
            green = kotlin.math.sin(green),
            blue = kotlin.math.sin(blue)
        )
    }

    // Cosine function
    fun cos(): Color {
        return Color(
            red = kotlin.math.cos(red),
            green = kotlin.math.cos(green),
            blue = kotlin.math.cos(blue)
        )
    }

    // Tangent function
    fun tan(): Color {
        return Color(
            red = kotlin.math.tan(red),
            green = kotlin.math.tan(green),
            blue = kotlin.math.tan(blue)
        )
    }

    // Inverse function
    fun inverse(): Color {
        return Color(
            red = 1.0 / red,
            green = 1.0 / green,
            blue = 1.0 / blue
        )
    }

    operator fun plus(i: Int): Color {
        return this.plus(i.toDouble())
    }


}

fun List<Double>.toColor(): Color {
    assert(this.count() == 3) {
        "lists to color accepts only 3 elements"
    }
    return Color(this[0], this[1], this[2])
}

fun Pair<Color, Color>.lineBetween(): (Color) -> Color {
    val lineCoef = Pair(this.first, this.second - this.first)
    val lineModel: (Color) -> Color = { it ->
        lineCoef.first + lineCoef.second * it
    }
    return lineModel
}

fun Pair<Color, Color>.getLinearSpacedColors(number: Int): List<Color> {
    val line = this.lineBetween()
    val step: Color = (this.second - this.first) / (number + 1).toDouble()
    var count: Int = 0
    var color = this.first
    val colors = mutableListOf<Color>()
    while (count <= number + 1) {
        colors.add(count, color)
        color += step
        count += 1
    }

    return colors
}

fun Pair<Color, Color>.distribute(number: Int, distributeFunction: DistributionFunction): List<Color> {
    return this.getLinearSpacedColors(number).mapIndexed{
        index, color ->
        distributeFunction(index, color)
    }
}

val red = Color(1.0, 0.0, 0.0)
val green = Color(0.0, 1.0, 0.0)
val blue = Color(0.0, 0.0, 1.0)
