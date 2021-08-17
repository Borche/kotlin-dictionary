package com.ab.ploy.filters;

import java.io.IOException;
import java.io.InputStream;
import javax.servlet.ReadListener;
import javax.servlet.ServletInputStream;

public class PloyStreamWrapper extends ServletInputStream {

    private final InputStream oldStream;
    private final InputStream newStream;

    public PloyStreamWrapper(InputStream oldStream, InputStream newStream) {
        this.oldStream = oldStream;
        this.newStream = newStream;
    }

    @Override
    public void close() throws IOException {
        oldStream.close();
        newStream.close();
    }

    @Override
    public boolean isFinished() {
        return true;
    }

    @Override
    public boolean isReady() {
        return true;
    }

    @Override
    public void setReadListener(ReadListener listener) {
    }

    @Override
    public int read() throws IOException {
        return newStream.read();
    }
}
