package com.autogeneral.test.api.AutoGeneralTestAPI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import com.autogeneral.test.api.AutoGeneralTestAPI.service.TodoService;

/**
 * 
 * This is a component class which listens to an event corresponding to application start-up
 * and immediately initialises the todo repository with a set of default values. This can be completely
 * removed once a persistent database outside the application is identified.
 *
 */
@Component
public class AutoGeneralTestApiAppInitialiser {

	@Autowired
	TodoService todoService;
	
	@Autowired
	DefaultTodo defaultTodo;

    @EventListener
    public void appReady(ApplicationReadyEvent event) {
    	todoService.initialiseTodoList(defaultTodo.getDefaults());
    }
}

