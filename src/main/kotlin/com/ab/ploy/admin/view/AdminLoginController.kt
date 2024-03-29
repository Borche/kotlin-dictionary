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
class AdminLoginController {

    private val log = LoggerFactory.getLogger(AdminLoginController::class.java.name)

    @GetMapping("/login")
    fun login(model: Model): ModelAndView {
        log.info("Hello from /login")
        val loginView = ModelAndView()
        loginView.viewName = "admin/login"
        return loginView
    }

    @GetMapping("", "/home")
    fun home(model: Model): ModelAndView {
        log.info("Hello from /home")
        val homeView = ModelAndView()
        homeView.viewName = "admin/home"
        return homeView
    }

    @GetMapping("/index")
    fun index(model: Model): ModelAndView {
        log.info("Hello from /home")
        val homeView = ModelAndView()
        homeView.viewName = "admin/index"
        return homeView
    }
}
