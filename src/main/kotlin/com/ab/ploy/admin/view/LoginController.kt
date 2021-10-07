/* Copyright © 2021 */
package com.ab.ploy.admin.view

import org.slf4j.LoggerFactory
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.servlet.ModelAndView

@Controller
@RequestMapping("/admin")
class LoginController {

    private val log = LoggerFactory.getLogger(LoginController::class.java.name)

    @GetMapping("/login")
    fun login(model: Model): ModelAndView {
        log.info("Hello from /login")
        val loginView = ModelAndView()
        loginView.viewName = "admin/login"
        return loginView
    }

    @GetMapping("/", "/home")
    fun home(model: Model): ModelAndView {
        log.info("Hello from /home")
        val homeView = ModelAndView()
        homeView.viewName = "admin/home"
        return homeView
    }

    @GetMapping("/access-denied") fun access_denied(model: Model) = "admin/access_denied"
}
