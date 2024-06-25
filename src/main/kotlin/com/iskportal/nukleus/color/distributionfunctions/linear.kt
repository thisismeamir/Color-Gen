package com.iskportal.com.iskportal.nukleus.color.distributionfunctions

import com.iskportal.com.iskportal.nukleus.color.DistributionFunction

val linearDistributionFunction: DistributionFunction = {
    step, currentColor ->
    currentColor
}

val quardaticDistributionFunction: DistributionFunction = {
    step, currentColor ->
    val alpha= 3.0
    val beta = 5.0
     currentColor.pow(2.0)
}

val rootDistributionFunction: DistributionFunction = {
    step, currentColor ->
    currentColor.root(2.0)
}

val cubicDistributionFunction: DistributionFunction = {
        step, currentColor ->
    currentColor.pow(3.0)
}

val exponentialDistributionFunction: DistributionFunction = {
        step, currentColor ->
    val lambda = 1.0
    currentColor.exp(lambda)
}

val logarithmicDistributionFunction: DistributionFunction = {
        step, currentColor ->
    currentColor.log()
}

val sineDistributionFunction: DistributionFunction = {
        step, currentColor ->
    currentColor.sin()
}

val cosineDistributionFunction: DistributionFunction = {
        step, currentColor ->
    currentColor.cos()
}

val tangentDistributionFunction: DistributionFunction = {
        step, currentColor ->
    currentColor.tan()
}

val inverseDistributionFunction: DistributionFunction = {
        step, currentColor ->
    currentColor.pow(-1.0)
}

val linearDirstributionAddStep: DistributionFunction = {
    step, currentColor ->
    currentColor + step / (step + 1)
}
