package com.nenaner.aoc2021.Day02

class DiveLocation(
    var horizontalPosition: Int,
    var depth: Int
) {
    fun changeHorizontalPosition(distance: Int) {
        horizontalPosition += distance
    }
    fun changeVerticalPosition(distance: Int) {
        depth += distance
    }
}