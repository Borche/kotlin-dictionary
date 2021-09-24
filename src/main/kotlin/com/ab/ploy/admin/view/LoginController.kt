/* Copyright © 2021 */
package com.ab.ploy.admin.view

import org.slf4j.LoggerFactory
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping

@Controller
@RequestMapping("/admin")
class LoginController {

    private val log = LoggerFactory.getLogger(LoginController::class.java.name)

    @GetMapping
    fun login(model: Model): String {
        log.info("Hello from Admin Login 2")
        return "admin/login"
    }
}
