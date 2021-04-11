/* Copyright © 2021 */
package com.ab.ploy.filters

import java.nio.charset.Charset
import javax.servlet.http.HttpServletResponse
import javax.servlet.http.HttpServletResponseWrapper
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.web.util.ContentCachingResponseWrapper

class PloyResponseWrapper(private val response: ContentCachingResponseWrapper, val requestId: Int) :
    HttpServletResponseWrapper(response) {

    private val log: Logger = LoggerFactory.getLogger(PloyResponseWrapper::class.java)

    fun logResponse() {
        val allBytes: ByteArray = response.contentAsByteArray
        log.info(
            "Outgoing Response" +
                "\n---------------------------" +
                "\nType: Response" +
                "\nID: {}" +
                "\nResponse-Code: {}" +
                "\nContent-Type: {}" +
                "\nContent-Length: {}" +
                "\nCharacter-Encoding: {}" +
                "\nHeaders: {}" +
                "\nPayload: {}" +
                "\n---------------------------",
            requestId,
            response.status,
            response.contentType,
            response.contentSize,
            response.characterEncoding,
            buildHeaderString(response),
            String(allBytes, Charset.forName(response.characterEncoding)))

        // Copy the response body content back into the real response
        response.copyBodyToResponse()
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
