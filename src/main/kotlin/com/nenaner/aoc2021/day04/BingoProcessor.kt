package com.nenaner.aoc2021.day04

import com.nenaner.aoc2021.utils.FileManager
import org.slf4j.LoggerFactory

class BingoProcessor (
    private val fileManager: FileManager
) {
    private val logger = LoggerFactory.getLogger(this.javaClass)
    fun findFinalScore(fileName: String): Int {
        val fileData = fileManager.readFile(fileName)
        val bingoBoards = buildBingoBoards(fileData)
        val bingoNumbers = fileData[0].split(",").map{it.toInt()}
        var winningBingoBoard: BingoBoard? = null
        var winningBingoNumber: Int? = null
        playingBingo@ for (bingoNumber in bingoNumbers) {
            logger.debug("Number $bingoNumber has been called")
            for (bingoBoard in bingoBoards) {
                logger.debug("Bingo board index ID ${bingoBoard.boardIndexId} is being checked")
                val isBingoAchieved = bingoBoard.markBingoNumber(bingoNumber)
                if (isBingoAchieved) {
                    winningBingoBoard = bingoBoard
                    winningBingoNumber = bingoNumber
                    break@playingBingo
                }
            }
        }
        if (winningBingoBoard == null) {
            logger.info("No bingo board has won yet with the numbers called")
            return 0
        }

        logger.info("BINGO!!! The winning bingo board's indexId is: ${winningBingoBoard.boardIndexId}")
        var sumOfAllUnMarkedNumbersOnWinningBoard = 0
        for(bingoRow in winningBingoBoard.bingoData) {
            sumOfAllUnMarkedNumbersOnWinningBoard += bingoRow.filter{!it.isMarked}.map{it.bingoNumber}.sum()
        }
        val finalScore = winningBingoNumber?.times(sumOfAllUnMarkedNumbersOnWinningBoard)!!
        logger.info("The final score is: $finalScore")
        return finalScore
    }

    private fun buildBingoBoards(fileData: List<String>): List<BingoBoard> {
        val bingoBoards = mutableListOf<BingoBoard>()
        for (index in 2..fileData.lastIndex step 6) {
            val rawBingoBoardData = mutableListOf<String>()
            for (bingoCardIndex in index until (index + 5)) {
                rawBingoBoardData.add(fileData[bingoCardIndex])
            }
            val bingoBoard = BingoBoard(rawBingoBoardData, index)
            bingoBoards.add(bingoBoard)
        }
        return bingoBoards
    }
}