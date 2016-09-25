package com.solandra.exception;

public class SolandraException extends RuntimeException{

    static final long serialVersionUID = -7034897190745766939L;

    public SolandraException() {
        super();
    }

    public SolandraException(String message) {
        super(message);
    }

    public SolandraException(String message, Throwable cause) {
        super(message, cause);
    }

    public SolandraException(Throwable cause) {
        super(cause);
    }

    protected SolandraException(String message, Throwable cause,
                               boolean enableSuppression,
                               boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

}
