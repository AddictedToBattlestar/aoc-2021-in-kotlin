package com.nenaner.aoc2021.day04

import com.nenaner.aoc2021.utils.FileManager
import io.kotest.matchers.shouldBe
import io.mockk.MockKAnnotations
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.SpyK
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

internal class BingoProcessorTest {
    @SpyK private var fileManager = FileManager()

    @InjectMockKs lateinit var subject: BingoProcessor

    @BeforeEach internal fun setUp() = MockKAnnotations.init(this)

    @Test
    internal fun `it can process the example provided in part 1`() {
        subject.findFinalScore("day4-example.txt").shouldBe(4512)
    }

    @Test
    internal fun `it can process the problem provided in part 1`() {
        val result = subject.findFinalScore("day4.txt")
        Assertions.assertNotEquals(0, result)
    }
}