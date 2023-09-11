package com.ecole221.microsdervices.course.first.service.exception;

import com.ecole221.microsdervices.course.common.service.exception.ErrorDTO;
import com.ecole221.microsdervices.course.common.service.exception.GlobalExceptionHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class FirstServiceException extends GlobalExceptionHandler {
    @ResponseBody
    @ExceptionHandler(value = {ErrorAgeException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorDTO handleException(ErrorAgeException orderDomainException){
        log.error(orderDomainException.getMessage(), orderDomainException);
        return ErrorDTO.builder()
                .code(HttpStatus.BAD_REQUEST.getReasonPhrase())
                .message(orderDomainException.getMessage())
                .build();
    }
}
