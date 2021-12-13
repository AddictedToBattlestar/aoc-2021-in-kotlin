package com.nenaner.aoc2021.Day03

import com.nenaner.aoc2021.FileManager
import com.nenaner.aoc2021.OutputLogger
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe
import io.mockk.MockKAnnotations
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.SpyK
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

internal class DiagnosticReportTest {
    @SpyK
    private var fileManager = FileManager()
    @SpyK
    private var OutputLogger = OutputLogger()

    @InjectMockKs
    lateinit var subject: DiagnosticReport

    @BeforeEach
    fun setup() = MockKAnnotations.init(this)

    @Test
    internal fun `it can process the example provided in part 1`() {
        subject.calculatePowerConsumption("day3-example.txt").shouldBe(198)
    }

    @Test
    internal fun `it can process the problem provided in part 1`() {
        subject.calculatePowerConsumption("day3.txt").shouldNotBe(0)
    }
}