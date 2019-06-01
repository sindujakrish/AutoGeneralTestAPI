package com.autogeneral.test.api.AutoGeneralTestAPI.jpa;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.autogeneral.test.api.AutoGeneralTestAPI.model.TodoItem;

/**
 * 
 * Represents the persistent layer of the application. Provides a default set of functions to
 * 
 * 1. Save TodoItem details onto the database.
 * 2. Retrieve TodoItem details from the database.
 * 3. Fetch a specific TodoItem record by providing the ID
 * 
 */
@Repository
public interface TodoRepository extends CrudRepository<TodoItem, Long> {
	
	TodoItem findById(int id);
}