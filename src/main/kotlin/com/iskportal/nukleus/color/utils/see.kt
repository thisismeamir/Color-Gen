package com.iskportal.com.iskportal.nukleus.color.utils

import com.iskportal.com.iskportal.nukleus.color.Color
import java.awt.Color.white
import java.awt.image.BufferedImage
import java.io.File
import javax.imageio.ImageIO

fun List<Color>.see(
    savePath: File,
    colorWidth: Int = 700,
    imageHeight: Int = 1000,
): BufferedImage {
    val numColors = this.size
    val imageWidth = colorWidth * numColors
    val image = BufferedImage(imageWidth, imageHeight, BufferedImage.TYPE_INT_RGB)
    val graphics = image.createGraphics()

    this.forEachIndexed { i, color ->
        val left = i * colorWidth
        val right = (i + 1) * colorWidth
        graphics.color = color.toAwtColor()
        graphics.fillRect(left, 0, right - left, imageHeight)
    }

    graphics.dispose()

    savePath.let {
        ImageIO.write(image, "png", it)
    }

    return image
}


fun List<Color>.seeGrid(
    savePath: File,
    colorWidth: Int = 100,
    colorHeight: Int = 100,
    columns: Int = 3,
): BufferedImage {
    val numColors = this.size
    val rows = (numColors + columns - 1) / columns  // Round up division
    val imageWidth = colorWidth * columns
    val imageHeight = colorHeight * rows
    val image = BufferedImage(imageWidth, imageHeight, BufferedImage.TYPE_INT_RGB)
    val graphics = image.createGraphics()

    this.forEachIndexed { index, color ->
        val x = (index % columns) * colorWidth
        val y = (index / columns) * colorHeight
        graphics.color = color.toAwtColor()
        graphics.fillRect(x, y, colorWidth, colorHeight)
    }

    graphics.dispose()

    savePath.let {
        ImageIO.write(image, "png", it)
    }

    return image
}

fun List<Color>.seeGridRounded(
    savePath: File,
    colorWidth: Int = 512,
    colorHeight: Int = 512,
    columns: Int = 3,
    cornerRadius: Int = 16,
    margin: Int = 32,
    backgroundColor: java.awt.Color = white
): BufferedImage {
    val numColors = this.size
    val rows = (numColors + columns - 1) / columns  // Round up division
    val imageWidth = colorWidth * columns + margin * (columns + 1)
    val imageHeight = colorHeight * rows + margin * (rows + 1)
    val image = BufferedImage(imageWidth, imageHeight, BufferedImage.TYPE_INT_RGB)
    val graphics = image.createGraphics()

    // Set the background to white
    graphics.color = backgroundColor
    graphics.fillRect(0, 0, imageWidth, imageHeight)

    this.forEachIndexed { index, color ->
        val x = (index % columns) * colorWidth + margin * (index % columns + 1)
        val y = (index / columns) * colorHeight + margin * (index / columns + 1)
        graphics.color = color.toAwtColor()
        graphics.fillRoundRect(x, y, colorWidth, colorHeight, cornerRadius, cornerRadius)
    }

    graphics.dispose()

    savePath.let {
        ImageIO.write(image, "png", it)
    }

    return image
}