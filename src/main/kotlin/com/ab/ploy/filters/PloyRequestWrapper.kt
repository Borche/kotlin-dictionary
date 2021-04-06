/* Copyright © 2021 */
package com.ab.ploy.filters

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import java.nio.charset.Charset
import javax.servlet.ServletInputStream
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletRequestWrapper

class PloyRequestWrapper(var request: HttpServletRequest, val requestId: Int) : HttpServletRequestWrapper(request) {

    private val log: Logger = LoggerFactory.getLogger(PloyRequestWrapper::class.java)

    var bytes: ByteArray? = null
    var stream: PloyStreamWrapper? = null

    init {
        if (request.contentLength > 0) {
            val allBytes: ByteArray = request.inputStream.readAllBytes()

            log.info(
                "Incoming Request" +
                    "\n---------------------------" +
                    "\nType: Request" +
                    "\nID: {}" +
                    "\nAddress: {}" +
                    "\nProtocol: {}" +
                    "\nEncoding: {}" +
                    "\nHttp-Method: {}" +
                    "\nContent-Type: {}" +
                    "\nContent-Length: {}" +
                    "\nHeaders: {}" +
                    "\nPayload: {}" +
                    "\n---------------------------",
                requestId,
                buildAddress(request),
                request.protocol,
                request.characterEncoding,
                request.method,
                request.contentType,
                request.contentLength,
                buildHeaderString(request),
                buildPayload(allBytes)
            )

            this.bytes = allBytes.copyOfRange(0, allBytes.size)
            stream = PloyStreamWrapper(request.inputStream, this.bytes!!.inputStream())
        }
    }

    private fun buildAddress(request: HttpServletRequest) =
        request.requestURL.toString() + if (request.queryString == null) "" else "?" + request.queryString

    private fun buildPayload(allBytes: ByteArray) =
        String(
            allBytes,
            Charset.forName(request.characterEncoding)
        )
            .replace("\n", "")
            .replace(" ", "")
            .replace("\t", "")

    private fun buildHeaderString(request: HttpServletRequest) =
        if (request.headerNames.toList().isEmpty()) "{}" else request
            .headerNames
            .toList()
            .map { "$it=${request.getHeader(it)}" }
            .joinToString(", ", "{ ", " }")

    override fun getInputStream(): ServletInputStream {
        return stream ?: request.inputStream
    }
}
