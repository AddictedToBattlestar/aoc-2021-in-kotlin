package com.nenaner.aoc2021.day05

import io.mockk.MockKAnnotations
import io.mockk.impl.annotations.InjectMockKs
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

internal class VentLocationPlotterTest {
    @InjectMockKs lateinit var subject: VentLocationPlotter

    @BeforeEach
    internal fun setUp() = MockKAnnotations.init(this)

    @Test
    internal fun diagonalVentTest1() {
        val ventDescription = "1,1 -> 3,3" // start / end

        val result = subject.plot(ventDescription)

        assertEquals(listOf(VentLocation(1,1), VentLocation(2,2), VentLocation(3,3)), result)
    }

    @Test
    internal fun diagonalVentTest2() {
        val ventDescription = "3,3 -> 1,1" // end / start

        val result = subject.plot(ventDescription)

        assertEquals(listOf(VentLocation(1,1), VentLocation(2,2), VentLocation(3,3)), result)
    }

    @Test
    internal fun diagonalVentTest3() {
        val ventDescription = "1,3 -> 3,1" //  start \ end

        val result = subject.plot(ventDescription)

        assertEquals(listOf(VentLocation(1,3), VentLocation(2,2), VentLocation(3,1)), result)
    }

    @Test
    internal fun diagonalVentTest4() {
        val ventDescription = "3,1 -> 1,3" // end \ start

        val result = subject.plot(ventDescription)

        assertEquals(listOf(VentLocation(1,3), VentLocation(2,2), VentLocation(3,1)), result)
    }

    @Test
    internal fun horizontalVentTest() {
        val ventDescription = "0,9 -> 2,9"

        val result = subject.plot(ventDescription)

        assertEquals(listOf(VentLocation(0,9), VentLocation(1,9), VentLocation(2,9)), result)
    }

    @Test
    internal fun verticalVentTest() {
        val ventDescription = "3,3 -> 3,5"

        val result = subject.plot(ventDescription)

        assertEquals(listOf(VentLocation(3,3), VentLocation(3,4), VentLocation(3,5)), result)
    }

    @Test
    internal fun longDiagonalVentTest() {
        val ventDescription = "0,0 -> 8,8"

        val result = subject.plot(ventDescription)

        val expected = listOf(
            VentLocation(0,0),
            VentLocation(1,1),
            VentLocation(2,2),
            VentLocation(3,3),
            VentLocation(4,4),
            VentLocation(5,5),
            VentLocation(6,6),
            VentLocation(7,7),
            VentLocation(8,8)
        )
        assertEquals(expected, result)
    }
}