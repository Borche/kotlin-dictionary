/* Copyright © 2021 */
package com.ab.ploy.filters;

import java.io.IOException;
import java.util.concurrent.atomic.AtomicInteger;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.util.ContentCachingResponseWrapper;

@Slf4j
public class ReqResLoggingFilterOncePerRequest extends OncePerRequestFilter {

  private static final AtomicInteger nextRequestId = new AtomicInteger(0);

  @Override
  protected void doFilterInternal(
      HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
      throws ServletException, IOException {

    int requestId = nextRequestId.incrementAndGet();

    PloyResponseWrapper responseWrapper =
        new PloyResponseWrapper(new ContentCachingResponseWrapper(response), requestId);

    PloyRequestWrapper requestWrapper = new PloyRequestWrapper(request, requestId);

    // Log the request before entering controller
    requestWrapper.logRequest();
    filterChain.doFilter(requestWrapper, responseWrapper);
    // Log the response before returning it from our server
    responseWrapper.logResponse();
  }
}
