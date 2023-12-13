package com.pyrosandro.bds.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum BdsErrorConstants {

    DEVICE_ALREADY_EXISTS(2001),
    DEVICE_ID_PROVIDED_BY_CLIENT(2002),
    DEVICE_MISSING_INPUT_ID(2003),
    DEVICE_MISMATCHING_INPUT_ID(2004),
    DEVICE_NOT_FOUND(2005),
    DEVICE_IDENTIFIER_ALREADY_EXISTS(2006),
    USER_ALREADY_EXISTS(2007),
    ;

    public final int code;

}