/* Copyright © 2021 */
package com.ab.ploy.filters

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.core.annotation.Order
import org.springframework.stereotype.Component
import javax.servlet.Filter
import javax.servlet.FilterChain
import javax.servlet.ServletRequest
import javax.servlet.ServletResponse
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@Component
@Order(2)
class ReqResLoggingFilter : Filter {

    private val log: Logger = LoggerFactory.getLogger(ReqResLoggingFilter::class.java)

    override fun doFilter(request: ServletRequest?, response: ServletResponse?, filterChain: FilterChain?) {
        val req = request as HttpServletRequest
        val res = response as HttpServletResponse

        filterChain?.doFilter(request, response)
    }
}
