package com.nenaner.aoc2021.day06

class PopulationCountdownTracker (
    initialPopulation: List<Long>
) {
    private var countOfZeroes: Long = initialPopulation.count { it == 0L }.toLong()
    private var countOfOnes: Long = initialPopulation.count { it == 1L }.toLong()
    private var countOfTwos: Long = initialPopulation.count { it == 2L }.toLong()
    private var countOfThrees: Long = initialPopulation.count { it == 3L }.toLong()
    private var countOfFours: Long = initialPopulation.count { it == 4L }.toLong()
    private var countOfFives: Long = initialPopulation.count { it == 5L }.toLong()
    private var countOfSixes: Long = initialPopulation.count { it == 6L }.toLong()
    private var countOfSevens: Long = 0L
    private var countOfEights: Long = 0L

    fun moveForwardOneDay() {
        val fishBreeding = countOfZeroes
        countOfZeroes = countOfOnes
        countOfOnes = countOfTwos
        countOfTwos = countOfThrees
        countOfThrees = countOfFours
        countOfFours = countOfFives
        countOfFives = countOfSixes
        countOfSixes = countOfSevens + fishBreeding
        countOfSevens = countOfEights
        countOfEights = fishBreeding
    }

    fun countOfPopulation(): Long {
        return countOfZeroes + countOfOnes + countOfTwos + countOfThrees + countOfFours +
                countOfFives + countOfSixes + countOfSevens + countOfEights
    }
}