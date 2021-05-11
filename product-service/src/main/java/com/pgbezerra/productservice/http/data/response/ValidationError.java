package com.pgbezerra.productservice.http.data.response;

import java.util.LinkedList;
import java.util.List;

public class ValidationError extends StandardError {

    private final List<FieldMessage> errors = new LinkedList<>();

    public ValidationError(String errorCode, String message, String path, String documentation) {
        super(errorCode, message, path, documentation);
    }

    public List<FieldMessage> getErrors() {
        return errors;
    }
}
