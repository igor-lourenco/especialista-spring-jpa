package com.especialista.spring.jpa.configs;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.ThreadContext;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;

@Component
public class CorrelationIdFilter extends OncePerRequestFilter {

    private static final String CORRELATION_ID = "x-correlation-id";
    private AtomicInteger atomicInteger = new AtomicInteger(0);

    @Override
    protected void doFilterInternal(
        HttpServletRequest request,
        HttpServletResponse response,
        FilterChain filterChain
    ) throws ServletException, IOException {

        String correlationId = request.getHeader(CORRELATION_ID);

        if (correlationId == null || correlationId.isBlank()) {
            correlationId = UUID.randomUUID().toString();
        }

//      ThreadContext.put(CORRELATION_ID, correlationId);
        ThreadContext.put(CORRELATION_ID, "Thread-"+ atomicInteger.getAndIncrement());

        try {
            response.setHeader(CORRELATION_ID, correlationId);

            filterChain.doFilter(request, response);
        } finally {
            ThreadContext.remove(CORRELATION_ID);
        }
    }
}