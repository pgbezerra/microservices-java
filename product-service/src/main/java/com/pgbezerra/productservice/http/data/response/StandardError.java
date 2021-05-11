package com.pgbezerra.productservice.http.data.response;

import io.micrometer.core.lang.NonNull;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.time.Instant;

import static java.util.Objects.requireNonNull;

public class StandardError {

    private final String errorCode;
    private final String message;
    private final String documentation;
    private final String path;
    private final Instant timestamp;

    public StandardError(@NonNull String errorCode, @NonNull String message, @NonNull String path, String documentation) {
        this.errorCode = requireNonNull(errorCode);
        this.message = requireNonNull(message);
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
        this.documentation = request.getRequestURL().toString().replace(request.getRequestURI(), "") + documentation;
        this.path = requireNonNull(path);
        this.timestamp = Instant.now();
    }

    public String getErrorCode() {
        return errorCode;
    }

    public String getMessage() {
        return message;
    }

    public String getDocumentation() {
        return documentation;
    }

    public String getPath() {
        return path;
    }

    public Instant getTimestamp() {
        return timestamp;
    }
}
