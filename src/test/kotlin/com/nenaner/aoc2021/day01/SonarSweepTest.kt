package com.nenaner.aoc2021.day01

import com.nenaner.aoc2021.utils.FileManager
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe
import io.mockk.MockKAnnotations
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.SpyK
import org.junit.jupiter.api.Assertions.assertNotEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.slf4j.LoggerFactory

internal class SonarSweepTest {
    private val logger = LoggerFactory.getLogger(this.javaClass)

    @SpyK private var fileManager = FileManager()

    @InjectMockKs lateinit var subject: SonarSweep

    @BeforeEach fun setup() = MockKAnnotations.init(this)

    @Test
    internal fun `it can process the example provided in part 1`() {
        subject.getDepthTrend("day1-example.txt").shouldBe(7)
    }

    @Test
    internal fun `it can process the problem provided in part 1`() {
        val result = subject.getDepthTrend("day1.txt")
        logger.info("day1-part1-getDepthTrend: $result")
        assertNotEquals(0, result)
    }

    @Test
    internal fun `it can process the example provided in part 2`() {
        subject.getDepthTrendSlidingWindow("day1-example.txt").shouldBe(5)
    }

    @Test
    internal fun `it can process the problem provided in part 2`() {
        val result = subject.getDepthTrendSlidingWindow("day1.txt")
        logger.info("day1-part2-getDepthTrendSlidingWindow: $result")
        assertNotEquals(0, result)
    }
}