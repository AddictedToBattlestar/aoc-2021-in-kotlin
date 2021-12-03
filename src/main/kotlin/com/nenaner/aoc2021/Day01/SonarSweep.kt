package com.nenaner.aoc2021.Day01

import com.nenaner.aoc2021.FileManager
import com.nenaner.aoc2021.OutputLogger
import org.springframework.stereotype.Component

@Component
class SonarSweep (
    private val fileManager: FileManager,
    private val outputLogger: OutputLogger
) {
    fun getDepthTrend(fileName: String): Int {
        val depthReadings = fileManager.readFile(fileName).map{it.toInt()}
        var numberOfIncreases = 0;
        var previousReading = depthReadings[0]
        for(index in 1..depthReadings.lastIndex) {
            if (depthReadings[index] > previousReading) {
                numberOfIncreases++
            }
            previousReading = depthReadings[index]
        }
        outputLogger.info("day1-part1-getDepthTrend: ${numberOfIncreases}")
        return numberOfIncreases
    }
}