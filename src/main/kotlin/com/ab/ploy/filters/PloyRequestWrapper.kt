/* Copyright © 2021 */
package com.ab.ploy.filters

import java.nio.charset.Charset
import javax.servlet.ServletInputStream
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletRequestWrapper
import kotlin.text.StringBuilder
import org.slf4j.Logger
import org.slf4j.LoggerFactory

class PloyRequestWrapper(var request: HttpServletRequest, requestId: Int) :
    HttpServletRequestWrapper(request) {

    private val log: Logger = LoggerFactory.getLogger(PloyRequestWrapper::class.java)

    var bytes: ByteArray? = null
    var stream: PloyStreamWrapper? = null

    init {
        val builder = StringBuilder()

        builder.append("Incoming Request")
        builder.append(
            "\n--------------------------- REQUEST: $requestId ---------------------------")
        builder.append("\n${request.protocol} ${request.method} ${buildAddress(request)}")
        builder.append("\nEncoding: ${request.characterEncoding}")
        builder.append("\nContent-Type: ${request.contentType}")
        builder.append("\nContent-Length: ${request.contentLength}")
        builder.append("\nHeaders: ${buildHeaderString(request)}")

        if (request.contentLength > 0) {
            val allBytes: ByteArray = request.inputStream.readAllBytes()

            builder.append("\nBody: ${buildPayload(allBytes)}")

            this.bytes = allBytes.copyOfRange(0, allBytes.size)
            stream = PloyStreamWrapper(request.inputStream, this.bytes!!.inputStream())
        }

        builder.append("\n---------------------------")
        log.info(builder.toString())
    }

    private fun buildAddress(request: HttpServletRequest) =
        request.requestURL.toString() +
            if (request.queryString == null) "" else "?" + request.queryString

    private fun buildPayload(allBytes: ByteArray) =
        String(allBytes, Charset.forName(request.characterEncoding))
            .replace("\n", "")
            .replace(" ", "")
            .replace("\t", "")

    private fun buildHeaderString(request: HttpServletRequest) =
        if (request.headerNames.toList().isEmpty()) "{}"
        else
            request
                .headerNames
                .toList()
                .map { "$it=${request.getHeader(it)}" }
                .joinToString(", ", "{ ", " }")

    override fun getInputStream(): ServletInputStream {
        return stream ?: request.inputStream
    }
}
