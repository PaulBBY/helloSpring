package fr.diginamic.hello.exceptions;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.itextpdf.text.DocumentException;

@ControllerAdvice
public class CustomExeptionHandler {

	@ExceptionHandler(CustomExceptions.class)
	protected ResponseEntity<String> traiterErreursCustom	(CustomExceptions ex) {
		return ResponseEntity.badRequest().body(ex.getMessage());
	}

	@ExceptionHandler(CustomEmptyTownExceptions.class)
	protected ResponseEntity<String> traiterErreursTown(CustomEmptyTownExceptions ex) {
		return ResponseEntity.badRequest().body(ex.getMessage());
	}
	
	@ExceptionHandler(CustomEmptyDepartmentExceptions.class)
	protected ResponseEntity<String> traiterErreursDepartment(CustomEmptyDepartmentExceptions ex) {
		return ResponseEntity.badRequest().body(ex.getMessage());
	}
	
	@ExceptionHandler(IOException.class)
    public ResponseEntity<String> handleIOException(IOException ex) {
		return ResponseEntity.badRequest().body(ex.getMessage());

    }

    @ExceptionHandler(DocumentException.class)
    public ResponseEntity<String> handleDocumentException(DocumentException ex) {
		return ResponseEntity.badRequest().body(ex.getMessage());
    }
	
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	protected ResponseEntity<Map<String, String>> functionalError(MethodArgumentNotValidException ex) {

		Map<String, String> errors = new HashMap<>();
		ex.getBindingResult().getAllErrors().forEach(error -> {
			String fieldName = ((FieldError) error).getField();
			String errorMessage = error.getDefaultMessage();
			errors.put(fieldName, errorMessage);
		});
		return ResponseEntity.badRequest().body(errors);
	}

}
