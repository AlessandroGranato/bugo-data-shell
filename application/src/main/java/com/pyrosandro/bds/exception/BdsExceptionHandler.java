package com.pyrosandro.bds.exception;

import com.pyrosandro.common.error.GlobalExceptionHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.util.Locale;

@Slf4j
@ControllerAdvice
public class BdsExceptionHandler extends GlobalExceptionHandler {

    public BdsExceptionHandler(@Value("${common.printstacktrace:false}") boolean printStackTrace, MessageSource messageSource) {
        super(printStackTrace, messageSource);
    }


    @ExceptionHandler({BdsException.class})
    public ResponseEntity<Object> handleBdsException(BdsException ex, WebRequest request) {
        return buildErrorDTO(ex, messageSource.getMessage(String.valueOf(ex.getErrorCode().getCode()), ex.getErrorArgs(), Locale.getDefault()), ex.getHttpStatus(), request);
    }
}
