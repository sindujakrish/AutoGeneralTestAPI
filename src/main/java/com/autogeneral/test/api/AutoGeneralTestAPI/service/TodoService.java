package com.autogeneral.test.api.AutoGeneralTestAPI.service;

import java.util.Date;
import java.util.List;

import com.autogeneral.test.api.AutoGeneralTestAPI.model.TodoDTO;

/**
 *	TodoService interface exposes the contract for the following:
 *
 *	1. initialiseTodoList: Initialises a list of todo objects and persists them onto the database.
 *	2. retrieveTodo: Fetches a todo object based on the ID entered
 *	3. addTodo: Adds the description, targetDate and a completed status and creates a new todo object.
 *	4. updateTodo: Updates the text and isCompleted status for the todo object identified by the ID entered.
 *	
 */
public interface TodoService {
	
	public void initialiseTodoList(List<TodoDTO> todoList);

	public TodoDTO retrieveTodo(int id);
	
	public TodoDTO addTodo(String desc, Date targetDate, boolean isDone);
	
	public TodoDTO updateTodo(String text, boolean isCompleted, int id);
}
