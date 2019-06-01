package com.autogeneral.test.api.AutoGeneralTestAPI.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.autogeneral.test.api.AutoGeneralTestAPI.exception.TodoNotFoundException;
import com.autogeneral.test.api.AutoGeneralTestAPI.exception.ValidationException;
import com.autogeneral.test.api.AutoGeneralTestAPI.model.TodoDTO;
import com.autogeneral.test.api.AutoGeneralTestAPI.model.TodoItemAddRequest;
import com.autogeneral.test.api.AutoGeneralTestAPI.model.TodoItemNotFoundError;
import com.autogeneral.test.api.AutoGeneralTestAPI.model.TodoItemUpdateRequest;
import com.autogeneral.test.api.AutoGeneralTestAPI.model.TodoItemValidationError;
import com.autogeneral.test.api.AutoGeneralTestAPI.service.TodoService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

/**
 * 
 * The Controller class which handles all todo related incoming REST requests for the application
 *
 */
@RestController
@Api(value="AutoGeneralAPIForTodos", tags = "todo", description = " To Do List endpoints" ,produces=MediaType.APPLICATION_JSON_VALUE)
public class TodoController {
		
	@Autowired
	TodoService todoService;
	
	/**
	 * Accepts a text string and adds the string as a description of a Todo item.
	 * 
	 * @param Custom Object with a text string
	 * 
	 * @return ToDo Object with the input text string, date created, a default ID and a default completed status
	 */
	@RequestMapping(value = "/todo", method = RequestMethod.POST)
	@ApiOperation("Creates a todo item")
	@ApiResponses(value= {@ApiResponse(code=200, message = "OK", response = TodoDTO.class),
			@ApiResponse(code=400,message="Validation Error", response = TodoItemValidationError.class)})
	public TodoDTO addTodo(@RequestBody TodoItemAddRequest body, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			throw new ValidationException("","");
		}
		String text = body.getText();
		if(text ==null || text.length()<1 || text.length()>50) {
			throw new ValidationException("Must be between 1 and 50 chars long", text);
		}
		TodoDTO todoObj = todoService.addTodo(text, new Date(), false);
		return todoObj;
	}
	
	/**
	 * Fetches the ToDo Item for the ID entered
	 * 
	 * @param ID as path variable
	 * 
	 * @return The corresponding ToDo object for the ID entered
	 */
	@RequestMapping(value = "/todo/{id}", method = RequestMethod.GET)
	@ApiOperation("Retrieve a specific todo item by id")
	@ApiResponses(value= {@ApiResponse(code=200, message = "OK", response = TodoDTO.class),
			@ApiResponse(code=400,message="Validation Error", response = TodoItemValidationError.class),
			@ApiResponse(code=404,message="Not Found Error", response = TodoItemNotFoundError.class)})
	public TodoDTO retrieveTodo(@PathVariable int id) {
		TodoDTO todo = todoService.retrieveTodo(id);
		if(todo == null) {
			throw new TodoNotFoundException(String.valueOf(id));
		}
		return todo;
	}
	
	/**
	 * Accepts a text string and a completed status along with the ID of a ToDo item and accordingly modifies 
	 * the corresponding ToDo object based on the details entered.
	 * 
	 * @param Custom Object with a text string and completed status and ID as path variable
	 * 
	 * @return ToDo Object with the updated details
	 */
	@RequestMapping(value = "/todo/{id}", method = RequestMethod.PATCH)
	@ResponseBody
	@ApiOperation("Modify a todo item")
	@ApiResponses(value= {@ApiResponse(code=200, message = "OK", response = TodoDTO.class),
			@ApiResponse(code=400,message="Validation Error", response = TodoItemValidationError.class),
			@ApiResponse(code=404,message="Not Found Error", response = TodoItemNotFoundError.class)})
	public TodoDTO updateTodo(@RequestBody TodoItemUpdateRequest body, BindingResult bindingResult, @PathVariable int id) {
		if (bindingResult.hasErrors()) {
			throw new ValidationException("","");
		}
		String text = body.getText();
		boolean isCompleted = body.isCompleted();
		if(text ==null || text.length()<1 || text.length()>50) {
			throw new ValidationException("Must be between 1 and 50 chars long", text);
		}
		TodoDTO todoObj = todoService.updateTodo(text, isCompleted, id);
		if(todoObj==null) {
			throw new TodoNotFoundException(String.valueOf(id));
		}
		return todoObj;
	}
	
}
