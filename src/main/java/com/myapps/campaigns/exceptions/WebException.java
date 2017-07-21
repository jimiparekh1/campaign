package com.myapps.campaigns.exceptions;

import org.springframework.http.HttpStatus;

public abstract class WebException extends RuntimeException {
    private static final long serialVersionUID = 6468001969813379135L;

    public WebException() {
        super();
    }

    public WebException(String msg) {
        super(msg);
    }

    public abstract HttpStatus getStatus();

}
