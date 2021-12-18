package com.nenaner.aoc2021.day01

import com.nenaner.aoc2021.utils.FileManager
import org.springframework.stereotype.Component

@Component
class SonarSweep (
    private val fileManager: FileManager,
) {
    fun getDepthTrend(fileName: String): Int {
        val depthReadings = fileManager.readFile(fileName).map{it.toInt()}
        var numberOfIncreases = 0
        var previousReading = depthReadings[0]
        for(index in 1..depthReadings.lastIndex) {
            if (depthReadings[index] > previousReading) {
                numberOfIncreases++
            }
            previousReading = depthReadings[index]
        }
        return numberOfIncreases
    }

    fun getDepthTrendSlidingWindow(fileName: String): Int {
        val depthReadings = fileManager.readFile(fileName).map{it.toInt()}

        val measurementWindowsAccumulator = mutableListOf<MeasurementWindowAccumulator>()
        for(index in 0..depthReadings.lastIndex) {
            for(measurementWindow in measurementWindowsAccumulator.filter { measurementWindowAccumulator -> measurementWindowAccumulator.accumulationCount < 3 }) {
                measurementWindow.accumulationCount++
                measurementWindow.totalDepth = measurementWindow.totalDepth + depthReadings[index]
            }
            val newMeasurementWindow = MeasurementWindowAccumulator(index, 1, depthReadings[index])
            measurementWindowsAccumulator.add(newMeasurementWindow)
        }

        var numberOfIncreases = 0
        var previousReading = measurementWindowsAccumulator[0].totalDepth
        for(index in 1 .. measurementWindowsAccumulator.lastIndex) {
            if (measurementWindowsAccumulator[index].totalDepth > previousReading) {
                numberOfIncreases++
            }
            previousReading = measurementWindowsAccumulator[index].totalDepth
        }
        return numberOfIncreases
    }
}