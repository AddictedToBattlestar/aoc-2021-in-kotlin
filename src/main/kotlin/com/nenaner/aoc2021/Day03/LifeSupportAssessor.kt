package com.nenaner.aoc2021.Day03

import com.nenaner.aoc2021.FileManager
import com.nenaner.aoc2021.OutputLogger
import kotlin.math.pow

class LifeSupportAssessor (
    private val fileManager: FileManager,
    private val outputLogger: OutputLogger
) {
    fun getLifeSupportRating(fileName: String): Int {
        val rawFileData = fileManager.readFile(fileName)

        val rawOxygenGeneratorRating = findLifeSupportRating(rawFileData, true)
        outputLogger.debug("Oxygen generator rating (raw): $rawOxygenGeneratorRating")
        val rawCarbonScrubberRating = findLifeSupportRating(rawFileData, false)
        outputLogger.debug("CO2 scrubber rating: $rawCarbonScrubberRating")


        val rawOxygenGeneratorValue = calculateValueFromBinaryString(rawOxygenGeneratorRating)
        outputLogger.debug("Oxygen generator rating: $rawOxygenGeneratorValue")
        val rawCarbonScrubberValue = calculateValueFromBinaryString(rawCarbonScrubberRating)
        outputLogger.debug("CO2 scrubber rating: $rawCarbonScrubberValue")

        val lifeSupportRating = (rawOxygenGeneratorValue * rawCarbonScrubberValue).toInt()
        outputLogger.info("Life support rating: $lifeSupportRating")
        return lifeSupportRating
    }

    private fun calculateValueFromBinaryString(binaryString: String): Double {
        var calculatedValue = 0.0;
        for (index in 0..binaryString.lastIndex) {
            val binaryValue = 2.toDouble().pow(binaryString.lastIndex - index)
            if (binaryString[index] == '1') {
                calculatedValue += binaryValue
            }
        }
        return calculatedValue
    }

    private fun findLifeSupportRating(rawFileData: List<String>, findMostCommonValue: Boolean): String {
        var columnIndex = 0
        var rawDiagnosticData = rawFileData
        while (rawDiagnosticData.size > 1) {
            val diagnosticData = buildLifeSupportData(rawDiagnosticData, columnIndex)
            // TODO: This is ugly and needs to be refactored
            rawDiagnosticData = if (findMostCommonValue) {
                if (diagnosticData.linesWithLeadingOnes.size < diagnosticData.linesWithLeadingZeroes.size) {
                    diagnosticData.linesWithLeadingZeroes
                } else {
                    diagnosticData.linesWithLeadingOnes
                }
            } else {
                if (diagnosticData.linesWithLeadingOnes.size < diagnosticData.linesWithLeadingZeroes.size) {
                    diagnosticData.linesWithLeadingOnes
                } else {
                    diagnosticData.linesWithLeadingZeroes
                }
            }
            columnIndex++
        }
        return rawDiagnosticData[0]
    }

    private fun buildLifeSupportData(rawData: List<String>, columnIndex: Int): LifeSupportTracker {
        val diagnosticData = LifeSupportTracker()
        for (rawDataRow in rawData) {
            if (rawDataRow[columnIndex] == '0') {
                diagnosticData.linesWithLeadingZeroes.add(rawDataRow)
            } else {
                diagnosticData.linesWithLeadingOnes.add(rawDataRow)
            }
        }
        return diagnosticData
    }
}