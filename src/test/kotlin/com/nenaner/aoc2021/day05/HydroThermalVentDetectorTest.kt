package com.nenaner.aoc2021.day05

import com.nenaner.aoc2021.utils.FileManager
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe
import io.mockk.MockKAnnotations
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.SpyK
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

internal class HydroThermalVentDetectorTest {
    @SpyK private var fileManager = FileManager()
    @SpyK private var simpleVentLocationPlotter = SimpleVentLocationPlotter()
    @SpyK private var ventLocationPlotter = VentLocationPlotter()

    @InjectMockKs lateinit var subject: HydroThermalVentDetector

    @BeforeEach
    internal fun setUp() = MockKAnnotations.init(this)

    @Test
    internal fun `it can process the example provided in part 1`() {
        subject.assessSeverityOfSimpleVents("day5-example.txt").shouldBe(5)
    }

    @Test
    internal fun `it can process the problem provided in part 1`() {
        subject.assessSeverityOfSimpleVents("day5.txt").shouldNotBe(0)
    }

    @Test
    internal fun `it can process the example provided in part 2`() {
        subject.assessSeverityOfComplexVents("day5-example.txt").shouldBe(12)
    }

    @Test
    internal fun `it can process the problem provided in part 2`() {
        subject.assessSeverityOfComplexVents("day5.txt").shouldNotBe(0)
    }
}