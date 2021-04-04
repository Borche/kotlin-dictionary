package com.ab.ploy

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@SpringBootApplication
@RestController
class PloyApplication {

    @GetMapping("/")
    fun helloWorld(): String {
        return "hello world";
    }
}

fun main(args: Array<String>) {
    runApplication<PloyApplication>(*args)
}
