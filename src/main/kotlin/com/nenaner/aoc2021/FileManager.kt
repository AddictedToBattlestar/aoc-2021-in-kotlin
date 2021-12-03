package com.nenaner.aoc2019

import org.springframework.stereotype.Component

@Component
class FileManager {
    fun readFile(fileName: String): List<String> {
        val inputStream = javaClass.classLoader.getResourceAsStream(fileName)
        return inputStream.bufferedReader().readLines()
    }
}