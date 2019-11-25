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
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import br.com.pitang.desafiopitang.exception.UsuarioEmailException;
import br.com.pitang.desafiopitang.exception.UsuarioLoginException;
import br.com.pitang.desafiopitang.model.MessageError;

@ControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
public class RestResponseEntityExceptionHandler 
  extends ResponseEntityExceptionHandler {
	
	@Autowired
	private Environment env;		
	
	@ExceptionHandler(value = {UsuarioLoginException.class, UsuarioEmailException.class})
	protected ResponseEntity<Object> handleConflictUsuario(RuntimeException ex, WebRequest request) {
	
		String value = new String();
		
		if(ex instanceof UsuarioLoginException) {
			value = env.getProperty("msg.login.cadastrado");
			
		}else {
			value = env.getProperty("msg.email.cadastrado");
		}
		
		MessageError error = new MessageError(value, HttpStatus.CONFLICT.value());
		
		return handleExceptionInternal(ex, error.toString(), new HttpHeaders(), HttpStatus.CONFLICT, request);
	}
	
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		
		List<MessageError> errors = new ArrayList<MessageError>();
		
		ex.getBindingResult().getFieldErrors().forEach(error -> errors.add(new MessageError(error.getField() + ": " + error.getDefaultMessage(), HttpStatus.BAD_REQUEST.value())));
    
	    return new ResponseEntity<Object>(errors, null,HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(value = {UsernameNotFoundException.class})
	protected ResponseEntity<Object> handleUserNotFound(UsernameNotFoundException ex, WebRequest request) {
	
		String msg = env.getProperty("msg.user.not.found");
		
		return handleExceptionInternal(ex, msg, new HttpHeaders(), HttpStatus.CONFLICT, request);
	}
	
	@ExceptionHandler(value = {DataIntegrityViolationException.class})
	protected ResponseEntity<Object> handlelicensePlate(DataIntegrityViolationException ex, WebRequest request) {
	
		String msg = env.getProperty("msg.car.plate.exception");
		
		MessageError error = new MessageError(msg, HttpStatus.CONFLICT.value());
		
		return handleExceptionInternal(ex, error, new HttpHeaders(), HttpStatus.CONFLICT, request);
	}

}
