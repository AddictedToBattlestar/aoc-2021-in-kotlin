package com.nenaner.aoc2021.Day02

import com.nenaner.aoc2021.FileManager
import com.nenaner.aoc2021.OutputLogger

class DivePositionTracker (
    private val fileManager: FileManager,
    private val outputLogger: OutputLogger
) {
    fun getResultingDiveLocation(fileName: String): Int {
        val diveReadings = fileManager.readFile(fileName)
        val currentDiveLocation = DiveLocation(0,0)
        for(rawDiveReading in diveReadings) {
            var (direction, distance) = rawDiveReading.split(" ")
            when(direction) {
                "forward" -> currentDiveLocation.changeHorizontalPosition(distance.toInt())
                "down" -> currentDiveLocation.changeVerticalPosition(distance.toInt())
                "up" -> currentDiveLocation.changeVerticalPosition(distance.toInt() * -1)
            }
            outputLogger.debug("current location - horizontalPosition: ${currentDiveLocation.horizontalPosition}, depth: ${currentDiveLocation.depth}")
        }
        val result = currentDiveLocation.horizontalPosition * currentDiveLocation.depth
        outputLogger.info("day2-part1-getResultingDiveLocation: ${result}")
        return result
    }
}