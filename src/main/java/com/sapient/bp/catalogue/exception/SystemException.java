package com.sapient.bp.catalogue.exception;

public class SystemException extends RuntimeException {
    public SystemException(String s) {
        super(s);
    }

    public SystemException(String s, Throwable t) {
        super(s, t);
    }
}
