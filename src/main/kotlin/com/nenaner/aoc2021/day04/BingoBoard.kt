package com.nenaner.aoc2021.day04

class BingoBoard (
    private val rawBingoBoardData: List<String>,
    val boardIndexId: Int
) {
    val bingoData: List<List<BingoMark>>
    private var isBingoAchieved = false
        get() = field

    init {
        if (rawBingoBoardData.size != 5) {
            throw Exception("A bingo board requires 5 lines of lines.  (# of lines provided: ${rawBingoBoardData.size})")
        }
        val dataBeingBuilt = mutableListOf<List<BingoMark>>()
        for (rawBingoLine in rawBingoBoardData) {
            val bingoLine = rawBingoLine.split(" ").filter { it.trim() != "" }.map{BingoMark(it.toInt(), false)}
            dataBeingBuilt.add(bingoLine)
        }
        bingoData = dataBeingBuilt
    }

    fun markBingoNumber(bingoNumberToMark: Int): Boolean {
        for ((yIndex, bingoRow) in bingoData.withIndex()) {
            for ((xIndex, bingoMark) in bingoRow.withIndex()) {
                if (bingoMark.bingoNumber == bingoNumberToMark){
                    bingoMark.isMarked = true
                    checkForBingo(yIndex, xIndex)
                    return isBingoAchieved
                }
            }
        }
        return isBingoAchieved
    }

    private fun checkForBingo(rowIndexToCheck: Int, columnIndexToCheck: Int) {
        if (isBingoAchieved) {
            return
        }
        if (bingoData[rowIndexToCheck].stream().allMatch {it.isMarked}) {
            isBingoAchieved = true
        }
        var areAllItemsInColumnMarked = true
        columnCheckLoop@ for (rowIndex in 0 .. bingoData.lastIndex) {
            if (!bingoData[rowIndex][columnIndexToCheck].isMarked) {
                areAllItemsInColumnMarked = false
                break@columnCheckLoop
            }
        }
        if (areAllItemsInColumnMarked) {
            isBingoAchieved = true
        }
    }
}