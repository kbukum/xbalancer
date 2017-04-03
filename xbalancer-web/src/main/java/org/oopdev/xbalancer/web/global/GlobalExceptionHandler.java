package org.oopdev.xbalancer.web.global;


import org.oopdev.xbalancer.common.exception.XbCommonException;
import org.oopdev.xbalancer.common.lang.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;

/**
 * Created by kamilbukum on 01/04/2017.
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(XbCommonException.class)
    public Response<String> defaultErrorHandler(HttpServletResponse response, XbCommonException e) {
        response.setStatus(e.getCode());
        Response<String> result = new Response<>();
        result.setCode(e.getCode());
        result.setData(e.getMessage());
        LOGGER.error(e.getMessage());
        return result;
    }

    // Convert a predefined exception to an HTTP Status code
    @ResponseStatus(value = HttpStatus.CONFLICT, reason = "Data integrity violation")  // 409
    @ExceptionHandler(DataIntegrityViolationException.class)
    public Response<String> conflict(DataIntegrityViolationException e) {
        Response<String> result = new Response<>();
        result.setCode(HttpStatus.CONFLICT.value());
        result.setData(e.getLocalizedMessage());
        LOGGER.error(e.getMessage());
        return result;
    }

    // Specify name of a specific view that will be used to display the error:
    @ExceptionHandler({SQLException.class, DataAccessException.class})
    public Response<String> databaseError(HttpServletResponse response, Exception e) {
        if (response.getStatus() <= 0) {
            response.setStatus(HttpStatus.INSUFFICIENT_STORAGE.value());
        }
        Response<String> result = new Response<>();
        result.setCode(HttpStatus.INSUFFICIENT_STORAGE.value());
        result.setData(e.getLocalizedMessage());
        LOGGER.error(e.getMessage());
        return result;
    }

    // Total control - setup a model and return the view name yourself. Or
    // consider subclassing ExceptionHandlerExceptionResolver (see below).
    @ExceptionHandler(Exception.class)
    public Response<String> handleError(HttpServletResponse response, Exception e) {
        if (response.getStatus() <= 0) {
            response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
        }
        Response<String> result = new Response<>();
        result.setCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
        result.setData(e.getLocalizedMessage());
        LOGGER.error(e.getMessage());
        return result;
    }
}
