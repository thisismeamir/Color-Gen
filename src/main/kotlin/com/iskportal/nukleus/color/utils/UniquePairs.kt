package com.iskportal.com.iskportal.nukleus.color.utils

fun <T> List<T>.allUniquePairs(): List<Pair<T, T>> {
    val pairs = mutableListOf<Pair<T, T>>()
    for (i in indices) {
        for (j in i + 1 until size) {
            pairs.add(this[i] to this[j])
        }
    }
    return pairs
}