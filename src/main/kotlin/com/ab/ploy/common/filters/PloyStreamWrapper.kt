/* Copyright © 2021 */
package com.ab.ploy.common.filters

import java.io.InputStream
import javax.servlet.ReadListener
import javax.servlet.ServletInputStream

class PloyStreamWrapper(private val oldStream: InputStream, private val newStream: InputStream) :
    ServletInputStream() {
    override fun read(): Int {
        return newStream.read()
    }

    override fun close() {
        oldStream.close()
        newStream.close()
    }

    override fun isFinished(): Boolean {
        return true
    }

    override fun isReady(): Boolean {
        return true
    }

    override fun setReadListener(rl: ReadListener?) {}
}
