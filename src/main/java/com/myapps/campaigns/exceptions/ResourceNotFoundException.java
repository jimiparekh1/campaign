package com.myapps.campaigns.exceptions;

import static org.springframework.http.HttpStatus.NOT_FOUND;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = NOT_FOUND)
public class ResourceNotFoundException extends WebException {

    private static final long serialVersionUID = -4474618867663251529L;

    public ResourceNotFoundException() {}

    public ResourceNotFoundException(String msg) {
        super(msg);
    }

    @Override
	public HttpStatus getStatus() {
		return HttpStatus.NOT_FOUND;
	}

}
