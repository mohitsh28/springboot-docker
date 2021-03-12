package com.poc.spring.docker.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;


@ControllerAdvice
@Slf4j
public class ServiceExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = {IllegalArgumentException.class,IllegalStateException.class})
    protected ResponseEntity<Object> handleConflict(Exception ex,WebRequest request) {
        log.info(ex.getMessage());
        String bodyOfResponse = "The provided data has some inconsistency";
        return handleExceptionInternal(ex,bodyOfResponse,new HttpHeaders(),HttpStatus.CONFLICT,request);
    }


    @ExceptionHandler(value = {RuntimeException.class})
    protected ResponseEntity<Object> handleRunTimException(Exception ex,WebRequest request) {
        log.info(ex.getMessage());
        String bodyOfResponse = "There was a server error while processing the request";
        return handleExceptionInternal(ex,bodyOfResponse,new HttpHeaders(),HttpStatus.INTERNAL_SERVER_ERROR,request);
    }

    @ExceptionHandler(value = {ConstraintViolationException.class})
    protected ResponseEntity<Object> handleConstraintViolationOnRequest(ConstraintViolationException ex,WebRequest request) {
        List<String> errors = ex.getConstraintViolations().stream().map(
                ConstraintViolation::getMessage
        ).collect(Collectors.toList());

        return handleExceptionInternal(ex,errors,new HttpHeaders(),HttpStatus.BAD_REQUEST,request);
    }

    @Override
    protected ResponseEntity<Object> handleMissingServletRequestParameter(MissingServletRequestParameterException ex,HttpHeaders headers,HttpStatus status,WebRequest request) {
        return handleExceptionInternal(ex,ex.getMessage(),headers,status,request);
    }

    private String toUUID(WebRequest request,String headerName) {
        String headerValue = request.getHeader(headerName);
        if (headerValue != null) {
            try {
                return headerValue;
            } catch (IllegalArgumentException e) {
                return null;
            }
        }
        return null;
    }

    private HttpStatus getHttpStatus(String errorCode) {
        List<String> badRequestCodes = Arrays.asList("35","36");
        List<String> notFoundCodes = Arrays.asList("07","26");
        List<String> internalServerCode = Arrays.asList("12","13");
        List<String> conflictCode = Arrays.asList("19","34");
        List<String> unauthorizedCode = Arrays.asList("27","28","29");
        if (internalServerCode.contains(errorCode)) {
            return HttpStatus.INTERNAL_SERVER_ERROR;
        } else if (notFoundCodes.contains(errorCode)) {
            return HttpStatus.NOT_FOUND;
        } else if (conflictCode.contains(errorCode)) {
            return HttpStatus.CONFLICT;
        } else if (unauthorizedCode.contains(errorCode)) {
            return HttpStatus.UNAUTHORIZED;
        } else {
            return HttpStatus.BAD_REQUEST;
        }
    }
}
