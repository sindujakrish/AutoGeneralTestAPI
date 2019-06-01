package com.autogeneral.test.api.AutoGeneralTestAPI.service;

import java.util.Stack;

import org.springframework.stereotype.Service;

/**
 * Provides the service layer for the application. The business logic for 
 * all the core functionalities represented in the TaskController and exposed
 * via TaskService are all defined here.
 * 
 * 1. Validates Brackets in a string
 * 
 */
@Service
public class TaskServiceImpl implements TaskService{
	
	/**
	 * This method takes in an input string and validates the brackets in the string.
	 * If the brackets are balanced i.e.,for every opening bracket if there is a matching
	 * closing bracket of the same type, then it returns a true boolean value as result.
	 * Else returns false. 
	 * 
	 * @param An input string
	 * @return A Boolean value indicating if the brackets in the string are balanced or not.
	 * 
	 */
	public Boolean validateBrackets(String input) {
		// Using a stack collection to temporarily hold brackets in a string
		// and manipulate the result based on the last element on the stack.
		Stack<Character> s = new Stack<Character>();
		char x;
		
		for(char c: input.toCharArray()) {
			if(c=='(' || c== '{' || c == '[') {
				s.push(c);
				continue;
			}
			
			if(s.empty()) 
				return false;
			
			switch(c) {
			case ')':
				x=s.lastElement();
				s.pop();
				if(x== '{' || x== '[') {
					return false;
				}
				break;
			case '}':
				x=s.lastElement();
				s.pop();
				if(x== '(' || x== '[') {
					return false;
				}
				break;
			case ']':
				x=s.lastElement();
				s.pop();
				if(x== '(' || x== '{') {
					return false;
				}
				break;
			}
		}
		return s.empty();
	}

}
