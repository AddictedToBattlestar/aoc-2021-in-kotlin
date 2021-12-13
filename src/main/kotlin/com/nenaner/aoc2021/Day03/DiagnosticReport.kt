package com.nenaner.aoc2021.Day03

import com.nenaner.aoc2021.FileManager
import com.nenaner.aoc2021.OutputLogger
import kotlin.math.pow

class DiagnosticReport (
    private val fileManager: FileManager,
    private val outputLogger: OutputLogger
) {
    fun calculatePowerConsumption(fileName: String): Int {
        val rawData = fileManager.readFile(fileName)
        val reportWidth = rawData[0].length
        val diagnosticData = Array(reportWidth) { DiagnosticTally() }
        for (rawDataRow in rawData) {
            for (index in 0 .. rawDataRow.lastIndex) {
                if (rawDataRow[index] == '0') {
                    diagnosticData[index].countOfZeroes++
                } else {
                    diagnosticData[index].countOfOnes++
                }
            }
        }
        val gammaRate = IntArray(reportWidth)
        val epsilonRate = IntArray(reportWidth)
        for (index in 0 until reportWidth) {
            if (diagnosticData[index].countOfOnes > diagnosticData[index].countOfZeroes) {
                gammaRate[index] = 1
                epsilonRate[index] = 0
            } else {
                gammaRate[index] = 0
                epsilonRate[index] = 1
            }
        }
        outputLogger.debug("gamma rate: $gammaRate")
        outputLogger.debug("epsilon rate: $epsilonRate")
        var gammaValue = 0.0;
        var epsilonValue = 0.0;
        for (index in 0 until reportWidth) {
            val binaryValue = 2.toDouble().pow(reportWidth - 1 - index)
            if (gammaRate[index] == 1) {
                gammaValue += binaryValue
            }
            if (epsilonRate[index] == 1) {
                epsilonValue += binaryValue
            }
        }
        outputLogger.debug("gamma value: $gammaValue")
        outputLogger.debug("epsilon value: $epsilonValue")
        val totalPowerConsumption = (gammaValue * epsilonValue).toInt()
        outputLogger.info("the power consumption of the submarine is: $totalPowerConsumption")
        return totalPowerConsumption
    }
}