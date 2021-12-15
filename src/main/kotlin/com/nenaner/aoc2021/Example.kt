package com.nenaner.aoc2021

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class Example {
    @GetMapping("/example")
    fun get(): String {
        return buildString {
            append("It works.")
        }
    }
}