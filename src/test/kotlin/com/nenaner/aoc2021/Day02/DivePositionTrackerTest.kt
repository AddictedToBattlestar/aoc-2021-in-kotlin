package com.nenaner.aoc2021.Day02

import com.nenaner.aoc2021.FileManager
import com.nenaner.aoc2021.OutputLogger
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe
import io.mockk.MockKAnnotations
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.SpyK
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

internal class DivePositionTrackerTest {
    @SpyK
    private var fileManager = FileManager()
    @SpyK
    private var OutputLogger = OutputLogger()

    @InjectMockKs
    lateinit var subject: DivePositionTracker

    @BeforeEach
    fun setup() = MockKAnnotations.init(this)

    @Test
    internal fun `it can process the example provided in part 1`() {
        subject.getResultingDiveLocation("day2-example.txt").shouldBe(150)
    }

    @Test
    internal fun `it can process the problem provided in part 1`() {
        subject.getResultingDiveLocation("day2.txt").shouldNotBe(0)
    }

    @Test
    internal fun `it can process the example provided in part 2`() {
        subject.getResultingDiveLocationWithAiming("day2-example.txt").shouldBe(900)
    }

    @Test
    internal fun `it can process the problem provided in part 2`() {
        subject.getResultingDiveLocationWithAiming("day2.txt").shouldNotBe(0)
    }
}