package com.nenaner.aoc2021.day02

class DiveLocation {
    var horizontalPosition = 0
    var depth = 0
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