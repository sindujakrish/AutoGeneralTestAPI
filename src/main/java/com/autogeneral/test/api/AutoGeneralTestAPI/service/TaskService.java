package com.autogeneral.test.api.AutoGeneralTestAPI.service;

/**
 *	TaskService interface exposes the contract for the following:
 *
 *	1. validateBrackets: Validates an input string and checks if the brackets in the string are balanced.
 *	
 */
public interface TaskService {
	
	public Boolean validateBrackets(String input);
	
}
