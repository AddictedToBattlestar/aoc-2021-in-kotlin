package com.nenaner.aoc2021.day04

import com.nenaner.aoc2021.utils.FileManager
import org.slf4j.LoggerFactory

class BingoProcessor (
    private val fileManager: FileManager
) {
    private val logger = LoggerFactory.getLogger(this.javaClass)
    fun findFinalScore(fileName: String, goUntilTheEnd: Boolean): Int {
        val fileData = fileManager.readFile(fileName)
        val bingoBoards = buildBingoBoards(fileData)
        val bingoNumbers = fileData[0].split(",").map{it.toInt()}
        var winningBingoBoard: BingoBoard? = null
        var winningBingoNumber: Int? = null
        playingBingo@ for (bingoNumber in bingoNumbers) {
            logger.debug("Number $bingoNumber has been called")
            for (bingoBoard in bingoBoards.filter { !it.isBingoAchieved }) {
                logger.debug("Bingo board index ID ${bingoBoard.boardIndexId} is being checked")
                val isBingoAchieved = bingoBoard.markBingoNumber(bingoNumber)
                if (isBingoAchieved) {
                    winningBingoBoard = bingoBoard
                    winningBingoNumber = bingoNumber
                    if (goUntilTheEnd) {
                        logger.debug("Bingo board ${bingoBoard.boardIndexId} is being removed as it has won on bingo number $bingoNumber")
                    } else {
                        break@playingBingo
                    }
                }
            }
        }
        if (winningBingoBoard == null) {
            logger.info("No bingo board has won yet with the numbers called")
            return 0
        }

        if (goUntilTheEnd) {
            logger.info("The last remaining bingo board was ${winningBingoBoard.boardIndexId}")
        } else {
            logger.info("BINGO!!! The winning bingo board's indexId is ${winningBingoBoard.boardIndexId}")
        }
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