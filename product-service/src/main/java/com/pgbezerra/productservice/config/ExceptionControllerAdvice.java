package com.pgbezerra.productservice.config;

import com.github.fge.jsonpatch.JsonPatchException;
import com.pgbezerra.productservice.http.data.response.FieldMessage;
import com.pgbezerra.productservice.http.data.response.StandardError;
import com.pgbezerra.productservice.http.data.response.ValidationError;
import io.swagger.v3.oas.annotations.Hidden;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import javax.persistence.NoResultException;
import javax.servlet.http.HttpServletRequest;

@RestControllerAdvice
public class ExceptionControllerAdvice {

    @Value("${springdoc.swagger-ui.path}")
    private String documentationPath;

    @Hidden
    @ExceptionHandler(value = NoResultException.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public StandardError notFound(NoResultException ex, HttpServletRequest request){
        return new StandardError("X_100", ex.getMessage(), request.getRequestURI(), documentationPath);
    }

    @Hidden
    @ExceptionHandler(value = MethodArgumentTypeMismatchException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public StandardError badRequest(MethodArgumentTypeMismatchException ex, HttpServletRequest request) {
        return new StandardError("X_200", "Invalid argument", request.getRequestURI(), documentationPath);
    }

    @Hidden
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public StandardError badRequest(MethodArgumentNotValidException ex, HttpServletRequest request) {

        ValidationError validationError = new ValidationError("X_201", "Invalid argument", request.getRequestURI(), documentationPath);

        for(FieldError x: ex.getBindingResult().getFieldErrors())
            validationError.getErrors().add(new FieldMessage(x.getField(), x.getDefaultMessage()));

        return validationError;
    }



    @ExceptionHandler(value = JsonPatchException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public StandardError defaultHandler(JsonPatchException ex, HttpServletRequest request) {
        return new StandardError("X_202", ex.getMessage(), request.getRequestURI(), documentationPath);
    }

    @Hidden
    @ExceptionHandler(value = HttpRequestMethodNotSupportedException.class)
    @ResponseStatus(value = HttpStatus.METHOD_NOT_ALLOWED)
    public StandardError notSupported(HttpRequestMethodNotSupportedException ex, HttpServletRequest request) {
        return new StandardError("X_250", "Method not allowed", request.getRequestURI(), documentationPath);
    }



    @ExceptionHandler(value = Exception.class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    public StandardError defaultHandler(Exception ex, HttpServletRequest request) {
        return new StandardError("X_300", ex.getMessage(), request.getRequestURI(), documentationPath);
    }


}
