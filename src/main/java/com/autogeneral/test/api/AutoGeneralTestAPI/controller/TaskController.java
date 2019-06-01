package com.autogeneral.test.api.AutoGeneralTestAPI.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.autogeneral.test.api.AutoGeneralTestAPI.exception.ValidationException;
import com.autogeneral.test.api.AutoGeneralTestAPI.model.BalanceTestResult;
import com.autogeneral.test.api.AutoGeneralTestAPI.model.TodoItemValidationError;
import com.autogeneral.test.api.AutoGeneralTestAPI.service.TaskService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

/**
 * 
 * The Controller class which handles all task related incoming REST requests for the application
 *
 */
@RestController
@Api(value="AutoGeneralAPI", tags = "tasks", description="General Algorithmic tasks" ,produces=MediaType.APPLICATION_JSON_VALUE)
@RequestMapping("/tasks")
public class TaskController {
	
	@Autowired
	TaskService taskService;
	
	/**
	 * Accepts an input string as a request parameter and validates if the brackets in the string are
	 * balanced or not.
	 * 
	 * @return A Custom Object with the input string and the boolean result to indicate if it is balanced or not.
	 */
	@RequestMapping(value = "/validateBrackets", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation("Validates if the brackets in the input string are balanced or not")
	@ApiResponses(value= {@ApiResponse(code=200, message = "OK", response = BalanceTestResult.class),
			@ApiResponse(code=400,message="Validation Error", response = TodoItemValidationError.class)})
	public BalanceTestResult validateBrackets(@RequestParam(required =false) String input) {
		
		if(input ==null || input.length()<1 || input.length()>50) {
			throw new ValidationException("Must be between 1 and 50 chars long", input);
		}
		
		boolean isBalanced = taskService.validateBrackets(input);
		BalanceTestResult balanceTestResult = new BalanceTestResult(input, isBalanced);
		return balanceTestResult;
	}
		
}
