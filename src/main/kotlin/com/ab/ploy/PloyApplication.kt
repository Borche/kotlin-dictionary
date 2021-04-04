package com.ab.ploy

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.RestController

@SpringBootApplication
@Controller
class PloyApplication {

    @GetMapping("/")
    @ResponseBody
    fun helloWorld(): String {
        return "hello world";
    }
}

fun main(args: Array<String>) {
    runApplication<PloyApplication>(*args)
}
