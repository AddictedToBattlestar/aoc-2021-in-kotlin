package com.nenaner.aoc2021.day03

import com.nenaner.aoc2021.utils.FileManager
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component
import kotlin.math.pow

@Component
class LifeSupportAssessor (
    private val fileManager: FileManager
) {
    private val logger = LoggerFactory.getLogger(this.javaClass)
    fun getLifeSupportRating(fileName: String): Int {
        val rawFileData = fileManager.readFile(fileName)

        val rawOxygenGeneratorRating = findLifeSupportRating(rawFileData, true)
        logger.debug("Oxygen generator rating (raw): $rawOxygenGeneratorRating")
        val rawCarbonScrubberRating = findLifeSupportRating(rawFileData, false)
        logger.debug("CO2 scrubber rating: $rawCarbonScrubberRating")


        val rawOxygenGeneratorValue = calculateValueFromBinaryString(rawOxygenGeneratorRating)
        logger.debug("Oxygen generator rating: $rawOxygenGeneratorValue")
        val rawCarbonScrubberValue = calculateValueFromBinaryString(rawCarbonScrubberRating)
        logger.debug("CO2 scrubber rating: $rawCarbonScrubberValue")

        return (rawOxygenGeneratorValue * rawCarbonScrubberValue).toInt()
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
            rawDiagnosticData = findDiagnosticDataForNextCycle(findMostCommonValue, diagnosticData)
            columnIndex++
        }
        return rawDiagnosticData[0]
    }

    private fun findDiagnosticDataForNextCycle(
        findMostCommonValue: Boolean,
        diagnosticData: LifeSupportTracker
    ): List<String> {
        val areThereMoreZeroesThanOnes = diagnosticData.linesWithLeadingOnes.size < diagnosticData.linesWithLeadingZeroes.size;
        return if (findMostCommonValue && areThereMoreZeroesThanOnes || !findMostCommonValue && !areThereMoreZeroesThanOnes) {
            diagnosticData.linesWithLeadingZeroes
        } else {
            diagnosticData.linesWithLeadingOnes
        }
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