package com.nenaner.aoc2021.day05

import com.nenaner.aoc2021.utils.FileManager
import org.slf4j.LoggerFactory

class HydroThermalVentDetector (
    private val fileManager: FileManager
) {
    private val logger = LoggerFactory.getLogger(this.javaClass)
    fun assessSeverity(fileName: String): Int {
        val rawData = fileManager.readFile(fileName)
        val locationAndVentMap = mutableMapOf<String, MutableList<String>>()

        for (ventDescription in rawData) {
            val ventLocations: List<MappingLocation> = getLocationsOfVent(ventDescription)
            for (location in ventLocations) {
                if(locationAndVentMap.containsKey(location.toString())) {
                    val currentVentsAtLocation = locationAndVentMap[location.toString()]
                    currentVentsAtLocation?.add(ventDescription)
                } else {
                    locationAndVentMap[location.toString()] = mutableListOf(ventDescription)
                }
            }
        }

        var countOfLocationsWhereMoreThanOneVentIsPresent = 0
        for ((_, currentVentsAtLocation) in locationAndVentMap) {
            if (currentVentsAtLocation.size > 1) {
                countOfLocationsWhereMoreThanOneVentIsPresent++
            }
        }
        logger.info("The number of locations where at least 3 vents overlap is: $countOfLocationsWhereMoreThanOneVentIsPresent")
        return countOfLocationsWhereMoreThanOneVentIsPresent
    }

    private fun getLocationsOfVent(ventDescription: String): List<MappingLocation> {
        val descriptionParts = ventDescription.split(" ")
        val startingLocation = buildMappingLocation(descriptionParts[0])
        val endingLocation = buildMappingLocation(descriptionParts[2])

        val xRange = listOf(startingLocation.x, endingLocation.x).sorted()
        val yRange = listOf(startingLocation.y, endingLocation.y).sorted()
        val ventLocations = mutableListOf<MappingLocation>()
        if (startingLocation.x == endingLocation.x) {
            for (y in yRange[0]..yRange[1]) {
                val currentLocation = MappingLocation(startingLocation.x, y)
                ventLocations.add(currentLocation)
            }
        } else {
            if (startingLocation.y == endingLocation.y) {
                for (x in xRange[0]..xRange[1]) {
                    val currentLocation = MappingLocation(x, startingLocation.y)
                    ventLocations.add(currentLocation)
                }
            }
        }

        return ventLocations
    }



    private fun buildMappingLocation(description: String): MappingLocation {
        val rawLocationInfo = description.split(",").map { it.toInt() }
        return MappingLocation(rawLocationInfo[0], rawLocationInfo[1])
    }
}