package com.nenaner.aoc2021.Day02

class DiveLocation(
    var horizontalPosition: Int,
    var depth: Int
) {
    var aim = 0

    fun changeHorizontalPosition(distance: Int) {
        horizontalPosition += distance
    }
    fun changeVerticalPosition(distance: Int) {
        depth += distance
    }

    fun aimDown(distance: Int) {
        aim += distance
    }

    fun aimUp(distance: Int) {
        aim += distance
    }

    fun aimForward(distance: Int) {
        horizontalPosition += distance
        depth += aim * distance
    }

    override fun toString(): String {
        return "DiveLocation(horizontalPosition=$horizontalPosition, depth=$depth, aim=$aim)"
    }
}