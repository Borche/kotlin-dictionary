/* Copyright © 2021 */
package com.ab.ploy.public.view

import org.slf4j.LoggerFactory
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping

@Controller
@RequestMapping
class PublicLoginController {

    private val log = LoggerFactory.getLogger(PublicLoginController::class.java.name)

    @GetMapping("/login") fun login(model: Model) = "public/login"

    @GetMapping("/") fun home(model: Model) = "public/home"
}
