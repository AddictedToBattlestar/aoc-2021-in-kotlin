package com.nenaner.aoc2021.day06

import com.nenaner.aoc2021.utils.FileManager

class LanternFishTracker (
    private val fileManager: FileManager
) {
    fun countPopulation(rawInitialPopulation: String, numberOfDays: Int): Long {
        val initialPopulation = rawInitialPopulation.split(",").map{it.toLong()}
        val populationCountdownTracker = PopulationCountdownTracker(initialPopulation)
        for (dayCount in 0  until  numberOfDays) {
            populationCountdownTracker.moveForwardOneDay()
        }
        return populationCountdownTracker.countOfPopulation()
    }
}