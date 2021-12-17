package com.nenaner.aoc2021.utils

import org.springframework.stereotype.Component

@Component
class FileManager {
    fun readFile(fileName: String): List<String> {
        val inputStream = javaClass.classLoader.getResourceAsStream(fileName)
        return inputStream.bufferedReader().readLines()
    }
}