package com.swiggato.user.exception;

import java.time.LocalDateTime;

import org.hibernate.LazyInitializationException;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import jakarta.servlet.http.HttpServletRequest;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(UsernameNotFoundException.class)
	public ResponseEntity<Object> handleUsernameNotFoundException(Exception e, HttpServletRequest request) {
		ErrorDetails errorDetails = new ErrorDetails(LocalDateTime.now(), e.getMessage(), request.getRequestURI());
		return ResponseEntity.status(404).body(errorDetails);
	}

	@ExceptionHandler(BadCredentialsException.class)
	public ResponseEntity<Object> handleBadCredentialsException(Exception e, HttpServletRequest request) {
		ErrorDetails errorDetails = new ErrorDetails(LocalDateTime.now(), e.getMessage(), request.getRequestURI());
		return ResponseEntity.status(401).body(errorDetails);
	}

	@ExceptionHandler(LazyInitializationException.class)
	public ResponseEntity<Object> handlLazyInitializationException(Exception e, HttpServletRequest request) {
		ErrorDetails errorDetails = new ErrorDetails(LocalDateTime.now(), e.getMessage(), request.getRequestURI());
		return ResponseEntity.status(500).body(errorDetails);
	}

}
