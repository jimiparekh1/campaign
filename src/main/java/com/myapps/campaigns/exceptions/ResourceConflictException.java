package com.myapps.campaigns.exceptions;

import static org.springframework.http.HttpStatus.CONFLICT;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = CONFLICT)
public class ResourceConflictException extends WebException {

    private static final long serialVersionUID = 4525167218641941851L;

    public ResourceConflictException() {}

    public ResourceConflictException(String msg) {
        super(msg);
    }

    @Override
   	public HttpStatus getStatus() {
   		return HttpStatus.CONFLICT;
   	}
}
