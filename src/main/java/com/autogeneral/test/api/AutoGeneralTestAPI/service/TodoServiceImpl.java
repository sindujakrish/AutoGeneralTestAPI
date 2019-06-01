package com.autogeneral.test.api.AutoGeneralTestAPI.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.autogeneral.test.api.AutoGeneralTestAPI.jpa.TodoRepository;
import com.autogeneral.test.api.AutoGeneralTestAPI.model.TodoDTO;
import com.autogeneral.test.api.AutoGeneralTestAPI.model.TodoItem;


/**
 * Provides the service layer for the application. The business logic for 
 * all the core functionalities represented in the TodoController and exposed
 * via TodoService are all defined here.
 * 
 * 1. Initialises a list of todo objects and persists them onto the database.
 * 2. Adds a Todo object
 * 3. Updates a Todo object
 * 4. Retrieves a Todo object based on ID entered.
 * 
 */
@Service
public class TodoServiceImpl implements TodoService {
	
	@Autowired
	TodoRepository todoRepository;
	
	/**
	 * Used to initialise the todo database with a set of todo objects. This method will
	 * become obsolete once a persistent database outside the application scope is identified
	 * and comes to use. 
	 */
	public void initialiseTodoList(List<TodoDTO> todoList) {
		for(TodoDTO todoDTO: todoList) {
			todoRepository.save(new TodoItem(todoDTO.getText(),todoDTO.isCompleted(), todoDTO.getCreatedAt()));
		}
	}
	
	/**
	 * Accepts an ID integer as input and fetches the corresponding ToDo object from memory and returns
	 * the same. If no matching object is found, then this returns null. 
	 *
	 *@param ID integer
	 *
	 *@return Corresponding ToDoItem object
	 */
	public TodoDTO retrieveTodo(int id) {
		TodoDTO todo = null;
		TodoItem todoItem = todoRepository.findById(id);
		if(todoItem != null) {
			todo = new TodoDTO();
			todo.setCompleted(todoItem.isCompleted());
			todo.setCreatedAt(todoItem.getCreatedAt());
			todo.setId(todoItem.getId());
			todo.setText(todoItem.getText());
		}
		return todo;
	}
	
	/**
	 * Accepts a description string, a targetDate and a boolean value and creates a ToDo Item object 
	 * with a new ID and the details entered and returns the created object.
	 *
	 *@param desc String
	 *@param targetDate Date
	 *@param isDone boolean
	 *
	 *@return Created ToDoItem object
	 */
	public TodoDTO addTodo(String desc, Date targetDate, boolean isDone) {
		TodoItem todoItem = todoRepository.save(new TodoItem(desc,isDone, targetDate));
		TodoDTO todo = new TodoDTO();
		todo.setCompleted(todoItem.isCompleted());
		todo.setCreatedAt(todoItem.getCreatedAt());
		todo.setId(todoItem.getId());
		todo.setText(todoItem.getText());
		return todo;
	}
	
	/**
	 * Accepts a description string, isCompleted boolean value and an ID and fetches the corresponding ToDo Item object 
	 * with the ID entered and updates the description and targetDate details in the object and returns
	 * the updated object.
	 *
	 *@param desc String
	 *@param isDone boolean
	 *@param ID integer
	 *
	 *@return Updated ToDoItem object
	 */
	public TodoDTO updateTodo(String text, boolean isCompleted, int id) {
		TodoDTO todo = null;
		TodoItem todoItem = todoRepository.findById(id);
		if(todoItem != null) {
			todoItem.setCompleted(isCompleted);
			todoItem.setText(text);
			todoItem = todoRepository.save(todoItem);
			todo = new TodoDTO();
			todo.setCompleted(todoItem.isCompleted());
			todo.setCreatedAt(todoItem.getCreatedAt());
			todo.setId(todoItem.getId());
			todo.setText(todoItem.getText());
		}
		return todo;
	}
}
