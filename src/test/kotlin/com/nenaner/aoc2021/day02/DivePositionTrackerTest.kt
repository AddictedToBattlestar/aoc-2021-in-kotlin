package com.nenaner.aoc2021.day02

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

internal class DivePositionTrackerTest {
    private val logger = LoggerFactory.getLogger(this.javaClass)

    @SpyK private var fileManager = FileManager()

    @InjectMockKs lateinit var subject: DivePositionTracker

    @BeforeEach fun setup() = MockKAnnotations.init(this)

    @Test
    internal fun `it can process the example provided in part 1`() {
        subject.getResultingDiveLocation("day2-example.txt").shouldBe(150)
    }

    @Test
    internal fun `it can process the problem provided in part 1`() {
        val result = subject.getResultingDiveLocation("day2.txt")
        logger.info("day2-part1-getResultingDiveLocation: $result")
        assertNotEquals(0, result)
    }

    @Test
    internal fun `it can process the example provided in part 2`() {
        subject.getResultingDiveLocationWithAiming("day2-example.txt").shouldBe(900)
    }

    @Test
    internal fun `it can process the problem provided in part 2`() {
        val result = subject.getResultingDiveLocationWithAiming("day2.txt")
        logger.info("day2-part2-getResultingDiveLocationWithAiming: $result")
        assertNotEquals(0, result)
    }
}