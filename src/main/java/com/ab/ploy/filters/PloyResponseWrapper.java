/* Copyright © 2021 */
package com.ab.ploy.filters;

import java.awt.*;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.stream.Collectors;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.util.ContentCachingResponseWrapper;

@Slf4j
public class PloyResponseWrapper extends HttpServletResponseWrapper {

  private final ContentCachingResponseWrapper response;
  private final int requestId;

  public PloyResponseWrapper(ContentCachingResponseWrapper response, int requestId) {
    super(response);
    this.response = response;
    this.requestId = requestId;
  }

  public void logResponse() throws IOException {
    StringBuilder b = new StringBuilder();
    b.append("Outgoing response");
    b.append("\n--------------------------- RESPONSE: ")
        .append(requestId)
        .append(" ---------------------------");
    b.append("\nResponse-Code: ").append(response.getStatus());
    b.append("\nContent-Type: ").append(response.getContentType());
    b.append("\nContent-Length: ").append(response.getContentSize());
    b.append("\nCharacter-Encoding: ").append(response.getCharacterEncoding());
    b.append("\nHeaders: ").append(buildHeaderString(response));

    // TODO: More info needs logging

    byte[] allBytes = response.getContentAsByteArray();
    if (response.getContentSize() > 0
        && MediaType.APPLICATION_JSON_VALUE.equals(response.getContentType())) {
      b.append("Body: ")
          .append(new String(allBytes, Charset.forName(response.getCharacterEncoding())));
    }
    response.copyBodyToResponse();

    b.append("\n---------------------------");

    log.info(b.toString());
  }

  private String buildHeaderString(HttpServletResponse response) {
    if (response.getHeaderNames().isEmpty()) return "{}";

    return response.getHeaderNames().stream()
        .map(h -> h + "=" + response.getHeader(h))
        .collect(Collectors.joining(", ", "{ ", " }"));
  }
}
