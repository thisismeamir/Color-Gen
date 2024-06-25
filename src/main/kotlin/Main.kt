package com.iskportal

import com.iskportal.com.iskportal.nukleus.color.distribute
import com.iskportal.com.iskportal.nukleus.color.distributionfunctions.*
import com.iskportal.com.iskportal.nukleus.color.utils.*
import java.awt.Color.black
import java.io.File
import java.nio.file.FileSystem
import kotlin.streams.asStream

fun main() {
    val runID = 1


    val distributions = listOf(
        linearDistributionFunction to "linear",
        quardaticDistributionFunction to "quadratic",
        rootDistributionFunction to "root",
        cubicDistributionFunction to "cubic",
        exponentialDistributionFunction to "exponential",
        logarithmicDistributionFunction to "logarithmic",
        sineDistributionFunction to "sine",
        cosineDistributionFunction to "cosine",
        tangentDistributionFunction to "tangent",
        inverseDistributionFunction to "inverse",
        linearDirstributionAddStep to "linear-to-step"

    )
    listOf(
        "#000000",
        "#FFFFFF",
        "#500550"
    ).map { it.toRGB() }
        .allUniquePairs()
        .asSequence()
        .asStream()
        .parallel()
        .forEach { twoColor ->

            distributions.forEach { distribution ->
                twoColor
                    .distribute(7, distribution.first).onEach { println(it) }
                    .apply {
                        File("output/data-runID-${runID}/${distribution.second}").mkdirs()
                        File("output/visual-runID-${runID}/${distribution.second}").mkdirs()
                        File("output/all-visual-runID-${runID}").mkdirs()
                        saveCSV(File("./output/data-runID-${runID}/${distribution.second}/"), "${twoColor.first}-${twoColor.second}.csv")
                        seeGridRounded(savePath = File("output/all-visual-runID-${runID}", "onWhite${distribution.second}-${twoColor.first}-${twoColor.second}"))
                        seeGridRounded(savePath = File("output/all-visual-runID-${runID}", "onBlack${distribution.second}-${twoColor.first}-${twoColor.second}"), backgroundColor = black, )
                        seeGridRounded(savePath = File("./output/visual-runID-${runID}/${distribution.second}/onWhite${twoColor.first}-${twoColor.second}.png"))
                        seeGridRounded(savePath = File("./output/visual-runID-${runID}/${distribution.second}/onBlack${twoColor.first}-${twoColor.second}.png"), backgroundColor = black)
                    }
                    }


        }
}