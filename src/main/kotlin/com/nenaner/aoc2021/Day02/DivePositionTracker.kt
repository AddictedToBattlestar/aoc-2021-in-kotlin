package com.nenaner.aoc2021.Day02

import com.nenaner.aoc2021.FileManager
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component

@Component
class DivePositionTracker (
    private val fileManager: FileManager
) {
    private val logger = LoggerFactory.getLogger(this.javaClass)
    fun getResultingDiveLocation(fileName: String): Int {
        val diveReadings = fileManager.readFile(fileName)
        val currentDiveLocation = DiveLocation()
        for(diveReading in diveReadings) {
            val (direction, distance) = diveReading.split(" ")
            when(direction) {
                "forward" -> currentDiveLocation.changeHorizontalPosition(distance.toInt())
                "down" -> currentDiveLocation.changeVerticalPosition(distance.toInt())
                "up" -> currentDiveLocation.changeVerticalPosition(distance.toInt() * -1)
            }
            logger.debug("current location - horizontalPosition: ${currentDiveLocation.horizontalPosition}, depth: ${currentDiveLocation.depth}")
        }
        val result = currentDiveLocation.horizontalPosition * currentDiveLocation.depth
        logger.info("day2-part1-getResultingDiveLocation: $result")
        return result
    }

    fun getResultingDiveLocationWithAiming(fileName: String): Int {
        val diveReadings = fileManager.readFile(fileName)
        val currentDiveLocation = DiveLocation()
        for(diveReading in diveReadings) {
            val (direction, distance) = diveReading.split(" ")
            when(direction) {
                "forward" -> currentDiveLocation.aimForward(distance.toInt())
                "down" -> currentDiveLocation.aimDown(distance.toInt())
                "up" -> currentDiveLocation.aimUp(distance.toInt() * -1)
            }
            logger.debug("current location - currentDiveLocation: ${currentDiveLocation}")
        }
        val result = currentDiveLocation.horizontalPosition * currentDiveLocation.depth
        logger.info("day2-part2-getResultingDiveLocationWithAiming: $result")
        return result
    }
}