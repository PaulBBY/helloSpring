package fr.diginamic.hello.exceptions;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class CustomExeptionHandler {

	@ExceptionHandler(CustomException.class)
	protected String traiterErreurs(CustomException ex) {
		return ex.toString();
	}
}
