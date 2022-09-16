package com.bookservice.controller;

import java.util.HashMap;
import java.util.Map;

import javax.validation.ConstraintViolationException;

import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;

public class BaseController {

	@ExceptionHandler(ConstraintViolationException.class)
	Map<String, String> handleException(ConstraintViolationException ex) {
		Map<String, String> errors = new HashMap<String, String>();

		ex.getConstraintViolations().forEach((error) -> {
			String message = error.getMessageTemplate();
			// String message = ((FieldError) error).getField();
			String fieldname = String.valueOf(error.getPropertyPath());
			errors.put(fieldname, message);
		});

		return errors;
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	Map<String, String> handleException1(MethodArgumentNotValidException ex) {
		Map<String, String> errors = new HashMap<String, String>();

		ex.getBindingResult().getAllErrors().forEach((error) -> {
			String fieldname = ((FieldError) error).getField();
			String message = ((FieldError) error).getDefaultMessage();
			errors.put(fieldname, message);
		});
		return errors;

	}
}