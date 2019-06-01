package com.autogeneral.test.api.AutoGeneralTestAPI.exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.autogeneral.test.api.AutoGeneralTestAPI.model.Details;
import com.autogeneral.test.api.AutoGeneralTestAPI.model.TodoItemValidationError;
import com.autogeneral.test.api.AutoGeneralTestAPI.model.TodoItemNotFoundError;

/**
 * 
 * Customised exception handler class for the application
 *
 */
@ControllerAdvice
@RestController
public class CustomizedResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

	/**
	 * 
	 * Handles the TodoNotFoundException thrown by the application. Packages the
	 * message in a Data transfer Object and sends it back as a response.
	 * 
	 * @param ex
	 * @param request
	 * @return
	 */
	@ExceptionHandler(TodoNotFoundException.class)
	public final ResponseEntity<TodoItemNotFoundError> handleTodoNotFoundException(TodoNotFoundException ex,
			WebRequest request) {
		Map<String, String> details = new HashMap<String, String>();
		details.put("message", "Item with " + ex.getMessage() + " not found");
		TodoItemNotFoundError errorAtrributes = new TodoItemNotFoundError(details, "NotFoundError");
		return new ResponseEntity<>(errorAtrributes, HttpStatus.NOT_FOUND);
	}

	/**
	 * 
	 * Handles the ValidationException thrown by the application. Packages the
	 * message in a Data transfer Object and sends it back as a response.
	 * 
	 * @param ex
	 * @param request
	 * @return
	 */
	@ExceptionHandler(ValidationException.class)
	public final ResponseEntity<TodoItemValidationError> handleValidationException(ValidationException ex,
			WebRequest request) {
		Details details = new Details("params", "text", ex.getMessage(), ex.getValue());
		TodoItemValidationError exceptionResponse = new TodoItemValidationError(details, "ValidationError");
		return new ResponseEntity<>(exceptionResponse, HttpStatus.BAD_REQUEST);
	}

}
