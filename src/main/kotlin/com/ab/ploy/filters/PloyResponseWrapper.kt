/* Copyright © 2021 */
package com.ab.ploy.filters

import java.nio.charset.Charset
import javax.servlet.http.HttpServletResponse
import javax.servlet.http.HttpServletResponseWrapper
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.http.MediaType
import org.springframework.web.util.ContentCachingResponseWrapper

class PloyResponseWrappesr(private val response: ContentCachingResponseWrapper, val requestId: Int) :
    HttpServletResponseWrapper(response) {

    private val log: Logger = LoggerFactory.getLogger(PloyResponseWrapper::class.java)

    fun logResponse() {
        val builder = StringBuilder()
        builder.append("Outgoing Response")
        builder.append(
            "\n--------------------------- RESPONSE: $requestId ---------------------------")
        builder.append("\nHttp-Status: ${response.status}")
        builder.append("\nContent-Type: ${response.contentType}")
        builder.append("\nContent-Length: ${response.contentSize}")
        builder.append("\nCharacter-Encoding: ${response.characterEncoding}")
        builder.append("\nHeaders: ${buildHeaderString(response)}")

        val allBytes: ByteArray = response.contentAsByteArray
        if (response.contentSize > 0 && response.contentType == MediaType.APPLICATION_JSON_VALUE) {
            builder.append("Body: ${String(allBytes, Charset.forName(response.characterEncoding))}")
        }

        // Copy the response body content back into the real response
        response.copyBodyToResponse()

        builder.append("\n---------------------------")
        log.info(builder.toString())
    }

    fun buildHeaderString(response: HttpServletResponse) =
        if (response.headerNames.isEmpty()) "{}"
        else
            response
                .headerNames
                .toList()
                .map { "$it=${response.getHeader(it)}" }
                .joinToString(", ", "{ ", " }")
}
