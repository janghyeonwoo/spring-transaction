package com.example.trans.exception;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class DuplicateGameException extends RuntimeException{
    public DuplicateGameException() {
    }

    public DuplicateGameException(String message) {
        super(message);
    }

    public DuplicateGameException(String message, Throwable cause) {
        super(message, cause);
    }

    public DuplicateGameException(Throwable cause) {
        super(cause);
    }

    public DuplicateGameException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
