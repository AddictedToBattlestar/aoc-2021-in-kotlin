package com.nenaner.aoc2021.day05

class SimpleVentLocationPlotter {
    fun plot(ventDescription: String): List<VentLocation> {
        val trackingData = PlotterTrackingData(ventDescription)
        if (trackingData.startingLocation.x == trackingData.endingLocation.x) {
            for (y in trackingData.yRangeMin..trackingData.yRangeMax) {
                val currentLocation = VentLocation(trackingData.startingLocation.x, y)
                trackingData.ventLocations.add(currentLocation)
            }
        } else {
            if (trackingData.startingLocation.y == trackingData.endingLocation.y) {
                for (x in trackingData.xRangeMin..trackingData.xRangeMax) {
                    val currentLocation = VentLocation(x, trackingData.startingLocation.y)
                    trackingData.ventLocations.add(currentLocation)
                }
            }
        }
        return trackingData.ventLocations
    }
}