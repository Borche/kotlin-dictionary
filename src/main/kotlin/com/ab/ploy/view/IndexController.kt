/* Copyright © 2021 */
package com.ab.ploy.view

import org.slf4j.LoggerFactory
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping

@Controller
@RequestMapping("/admin")
class IndexController {

    private val log = LoggerFactory.getLogger(IndexController::class.java.name)

    @GetMapping
    fun index(model: Model): String {
        log.info("Hello")
        return "index"
    }
}
