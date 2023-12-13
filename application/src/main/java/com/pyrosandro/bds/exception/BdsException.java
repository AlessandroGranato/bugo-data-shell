package com.pyrosandro.bds.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.http.HttpStatus;

@Data
@EqualsAndHashCode(callSuper = true)
public class BdsException extends Exception {

    private final BdsErrorConstants errorCode;
    private final Object[] errorArgs;
    private final HttpStatus httpStatus;

    public BdsException(BdsErrorConstants errorCode, Object[] errorArgs, HttpStatus httpStatus) {
        this.errorCode = errorCode;
        this.errorArgs = errorArgs;
        this.httpStatus = httpStatus;
    }
}
