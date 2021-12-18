package com.nenaner.aoc2021.day05

import com.nenaner.aoc2021.utils.FileManager
import org.slf4j.LoggerFactory

class HydroThermalVentDetector (
    private val fileManager: FileManager,
    private val simpleVentLocationPlotter: SimpleVentLocationPlotter,
    private val ventLocationPlotter: VentLocationPlotter
) {
    private val logger = LoggerFactory.getLogger(this.javaClass)

    fun assessSeverityOfSimpleVents(fileName: String): Int {
        return assessSeverity(fileName, false)
    }

    fun assessSeverityOfComplexVents(fileName: String): Int {
        return assessSeverity(fileName, true)
    }

    private fun assessSeverity(fileName: String, includeDiagonals: Boolean): Int {
        val rawData = fileManager.readFile(fileName)
        val locationAndVentMap = mutableMapOf<VentLocation, MutableList<String>>()

        for (ventDescription in rawData) {
            logger.debug("Processing $ventDescription")
            val ventLocations: List<VentLocation> = getLocationsOfVent(ventDescription, includeDiagonals)
            for (location in ventLocations) {
                if(locationAndVentMap.containsKey(location)) {
                    val currentVentsAtLocation = locationAndVentMap[location]
                    currentVentsAtLocation?.add(ventDescription)
                } else {
                    locationAndVentMap[location] = mutableListOf(ventDescription)
                }
            }
        }

        var countOfLocationsWhereMoreThanOneVentIsPresent = 0
        for ((_, currentVentsAtLocation) in locationAndVentMap) {
            if (currentVentsAtLocation.size > 1) {
                countOfLocationsWhereMoreThanOneVentIsPresent++
            }
        }
        return countOfLocationsWhereMoreThanOneVentIsPresent
    }

    private fun getLocationsOfVent(ventDescription: String, includeDiagonals: Boolean): List<VentLocation> {
        return if (includeDiagonals) {
            ventLocationPlotter.plot(ventDescription)
        } else {
            simpleVentLocationPlotter.plot(ventDescription)
        }
    }
}