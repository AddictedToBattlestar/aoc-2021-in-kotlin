package com.nenaner.aoc2021.day05

class PlotterTrackingData (
    ventDescription: String
) {
    val startingLocation: VentLocation
    val endingLocation: VentLocation
    val xRangeMin: Int
    val xRangeMax: Int
    val yRangeMin: Int
    val yRangeMax: Int
    val ventLocations = mutableListOf<VentLocation>()

    init {
        val descriptionParts = ventDescription.split(" ")
        startingLocation = buildMappingLocation(descriptionParts[0])
        endingLocation = buildMappingLocation(descriptionParts[2])
        val xRange = listOf(startingLocation.x, endingLocation.x).sorted()
        xRangeMin = xRange[0]
        xRangeMax = xRange[1]
        val yRange = listOf(startingLocation.y, endingLocation.y).sorted()
        yRangeMin = yRange[0]
        yRangeMax = yRange[1]
    }

    private fun buildMappingLocation(description: String): VentLocation {
        val rawLocationInfo = description.split(",").map { it.toInt() }
        return VentLocation(rawLocationInfo[0], rawLocationInfo[1])
    }
}