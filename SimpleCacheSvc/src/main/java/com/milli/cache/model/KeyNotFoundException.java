package com.milli.cache.model;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class KeyNotFoundException extends RuntimeException {
   
	public KeyNotFoundException(String message) {
        super(message);
    }
    
    @Override
    public synchronized Throwable fillInStackTrace() {
        return this;
    }
}