package com.nenaner.aoc2021.day05

class VentLocationPlotter {
    fun plot(ventDescription: String): List<VentLocation> {
        val trackingData = PlotterTrackingData(ventDescription)
        val ventSlope = getSlope(trackingData.startingLocation, trackingData.endingLocation)
        if (ventSlope != null && ventSlope == 1) {
            var y = trackingData.yRangeMin
            for (x in trackingData.xRangeMin  .. trackingData.xRangeMax) {
                val currentLocation = VentLocation(x, y)
                trackingData.ventLocations.add(currentLocation)
                y++
            }
        } else if (ventSlope != null && ventSlope == -1) {
            var y = trackingData.yRangeMax
            for (x in trackingData.xRangeMin  .. trackingData.xRangeMax) {
                val currentLocation = VentLocation(x, y)
                trackingData.ventLocations.add(currentLocation)
                y--
            }
        } else {
            val simpleVentLocationPlotter = SimpleVentLocationPlotter()
            return simpleVentLocationPlotter.plot(ventDescription)
        }
        return trackingData.ventLocations
    }

    private fun getSlope(point1: VentLocation, point2: VentLocation): Int? {
        if (point2.x - point1.x == 0) return null
        return (point2.y - point1.y) / (point2.x - point1.x)
    }
}