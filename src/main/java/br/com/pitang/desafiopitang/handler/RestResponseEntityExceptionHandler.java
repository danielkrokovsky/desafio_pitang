package br.com.pitang.desafiopitang.handler;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.core.env.Environment;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
public class RestResponseEntityExceptionHandler 
  extends ResponseEntityExceptionHandler {
	
	@Autowired
	private Environment env;		
	
	@ExceptionHandler(value = {DataIntegrityViolationException.class})
	protected ResponseEntity<Object> handleConflict(RuntimeException ex, WebRequest request) {
		
		boolean constraintName = ex.getMessage().contains("EMAIL");
		String value = "";
		
		if(constraintName) {
			value = env.getProperty("msg.email.cadastrado");				
		}else {
			value = env.getProperty("msg.login.cadastrado");			
		}
		
		return handleExceptionInternal(ex, value, new HttpHeaders(), HttpStatus.CONFLICT, request);
	}
	
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		
		List<String> errors = new ArrayList<String>();
		
		ex.getBindingResult().getFieldErrors().forEach(error -> errors.add(error.getField() + ": " + error.getDefaultMessage()));
    
	    return new ResponseEntity<Object>(
	    		errors, new HttpHeaders(), HttpStatus.BAD_REQUEST);
	}

}
