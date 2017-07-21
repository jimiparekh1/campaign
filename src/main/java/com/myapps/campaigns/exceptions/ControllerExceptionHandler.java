package com.myapps.campaigns.exceptions;

import java.util.Iterator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ControllerExceptionHandler {

    private static final Logger log = LoggerFactory
            .getLogger(ControllerExceptionHandler.class);
    
    @ExceptionHandler()
	public ResponseEntity<?> handleException(WebException we) {
		
		return new ResponseEntity<Object>(we.getMessage(), we.getStatus());
	}
    
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> handleInvalidRequest(MethodArgumentNotValidException e) {

    	log.debug("In handleInvalidRequest");
    	String msg = getErrorMsg(e.getBindingResult());
        log.debug("Sending Error:" + msg);        
        return new ResponseEntity<Object>(msg, HttpStatus.BAD_REQUEST);
    }
     
    private String getErrorMsg(BindingResult result){
    	
    	if (null != result && result.hasErrors()) {

			String msg = "[";
			Iterator<ObjectError> it = result.getAllErrors().iterator();
			while (it.hasNext()){
				msg += it.next().getDefaultMessage() + ", ";
			}
			msg += "]";
			return msg;
		}
    	
    	return null;
    }

}

