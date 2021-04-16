/* Copyright © 2021 */
package com.ab.ploy.filters

import java.util.concurrent.atomic.AtomicInteger
import javax.servlet.FilterChain
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.core.annotation.Order
import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter
import org.springframework.web.util.ContentCachingResponseWrapper

@Component
@Order(1)
class ReqResLoggingFilterOncePerRequest : OncePerRequestFilter() {

    companion object {
        val nextRequestId: AtomicInteger = AtomicInteger(0)
    }
    private val log: Logger = LoggerFactory.getLogger(ReqResLoggingFilterOncePerRequest::class.java)

    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {
        val requestId = nextRequestId.incrementAndGet()

        val res = PloyResponseWrapper(ContentCachingResponseWrapper(response), requestId)

        filterChain.doFilter(PloyRequestWrapper(request, requestId), res)

        // Log the response just before returning it from our server
        res.logResponse()
    }
}
