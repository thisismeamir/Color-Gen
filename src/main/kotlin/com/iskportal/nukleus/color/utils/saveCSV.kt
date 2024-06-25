package com.iskportal.com.iskportal.nukleus.color.utils

import com.iskportal.com.iskportal.nukleus.color.Color
import java.io.File


fun List<Color>.saveCSV(outputDir: File, name: String) {
    if (!outputDir.exists()) {
        outputDir.mkdirs()
    }

    val outputFile = File(outputDir, name)
    outputFile.bufferedWriter().use { writer ->
        // Write header
        writer.write("Red,Green,Blue\n")

        // Write color data
        this.forEach { color ->
            writer.write("${(color.red * 255).toInt()},${(color.green * 255).toInt()},${(color.blue * 255).toInt()}\n")
        }
    }
}