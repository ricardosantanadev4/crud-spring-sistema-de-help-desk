package br.com.rsds.sistemadehelpdesk.controller;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import br.com.rsds.sistemadehelpdesk.exception.RecordNotFoundException;

@RestControllerAdvice
public class AplicationControllerAdvace {

	@ExceptionHandler(RecordNotFoundException.class)
	public String HandleNotFoundException(RecordNotFoundException ex) {
		return ex.getMessage();
	}

}
