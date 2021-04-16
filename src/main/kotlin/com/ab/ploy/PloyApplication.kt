/* Copyright © 2021 */
package com.ab.ploy

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.web.bind.annotation.RestController

@SpringBootApplication @RestController class PloyApplication

fun main(args: Array<String>) {
    runApplication<PloyApplication>(*args)
}
