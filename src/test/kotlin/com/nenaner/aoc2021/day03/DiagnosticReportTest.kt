package com.nenaner.aoc2021.day03

import com.nenaner.aoc2021.utils.FileManager
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe
import io.mockk.MockKAnnotations
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.SpyK
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.slf4j.LoggerFactory

internal class DiagnosticReportTest {
    private val logger = LoggerFactory.getLogger(this.javaClass)

    @SpyK private var fileManager = FileManager()

    @InjectMockKs lateinit var subject: DiagnosticReport

    @BeforeEach
    fun setup() = MockKAnnotations.init(this)

    @Test
    internal fun `it can process the example provided in part 1`() {
        subject.calculatePowerConsumption("day3-example.txt").shouldBe(198)
    }

    @Test
    internal fun `it can process the problem provided in part 1`() {
        val result = subject.calculatePowerConsumption("day3.txt")
        logger.info("the power consumption of the submarine is: $result")
    }
}