/* Copyright © 2021 */
package com.ab.ploy.filters;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class PloyRequestWrapper extends HttpServletRequestWrapper {

  private final HttpServletRequest request;
  private final int requestId;
  private byte[] bytes;
  private PloyStreamWrapper stream;

  public PloyRequestWrapper(HttpServletRequest request, int requestId) {
    super(request);
    this.request = request;
    this.requestId = requestId;
  }

  public void logRequest() throws IOException {
    StringBuilder b = new StringBuilder();
    b.append("Incoming Request");
    b.append("\n--------------------------- REQUEST: ")
        .append(requestId)
        .append(" ---------------------------");
    b.append("\n")
        .append(request.getProtocol())
        .append(" ")
        .append(request.getMethod())
        .append(" ")
        .append(buildAddress(request));
    b.append("\nEncoding: ").append(request.getCharacterEncoding());
    b.append("\nContent-Type: ").append(request.getContentType());
    b.append("\nContent-Length: ").append(request.getContentLength());
    b.append("\nHeaders: ").append(buildHeaderString(request));

    if (request.getContentLength() > 0) {
      byte[] allBytes = request.getInputStream().readAllBytes();
      b.append("\nBody: ").append(buildBody(allBytes));
      this.bytes = Arrays.copyOf(allBytes, allBytes.length);
      this.stream = new PloyStreamWrapper(request.getInputStream(), new ByteArrayInputStream(allBytes));
    }

    b.append("\n---------------------------");

    log.info(b.toString());
  }

  private String buildBody(byte[] allBytes) {
    return new String(allBytes, Charset.forName(request.getCharacterEncoding()))
        .replaceAll("\n", "")
        .replaceAll(" ", "")
        .replaceAll("\t", "");
  }

  private String buildAddress(HttpServletRequest request) {
    StringBuilder b = new StringBuilder();
    b.append(request.getRequestURL());
    if (request.getQueryString() != null) {
      b.append(request.getQueryString());
    }
    return b.toString();
  }

  private String buildHeaderString(HttpServletRequest request) {
    if (!request.getHeaderNames().hasMoreElements()) return "{}";

    List<String> headers = new ArrayList<>();
    while (request.getHeaderNames().asIterator().hasNext()) {
      headers.add(request.getHeader(request.getHeaderNames().nextElement()));
    }

    return headers.stream()
        .map(h -> h + "=" + request.getHeader(h))
        .collect(Collectors.joining(", ", "{ ", " }"));
  }
}
