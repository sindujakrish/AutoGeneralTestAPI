package com.autogeneral.test.api.AutoGeneralTestAPI;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.autogeneral.test.api.AutoGeneralTestAPI.model.TodoDTO;

/**
 * 
 * The component class which holds the default list of todo description values.
 * This will be used to initialise the application with a default set of todo items called during application start-up.
 * 
 * This class will no longer be valid once the application uses a database outside the application.
 */
@Component
public class DefaultTodo {

	@Value("${default.todoDesc:Learn Spring MVC,Learn Struts,Learn Spring Boot}")
    private String[] todoArray;

	private String[] getTodoArray() {
		return todoArray;
	}

	public List<TodoDTO> getDefaults() {
		List<TodoDTO> todoList = new ArrayList<TodoDTO>();
		for(String desc: getTodoArray()) {
			todoList.add(new TodoDTO(desc, false, new Date()));
		}
		return todoList;
	}
}

