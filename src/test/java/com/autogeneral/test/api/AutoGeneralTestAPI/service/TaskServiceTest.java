package com.autogeneral.test.api.AutoGeneralTestAPI.service;


import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@WebMvcTest(value = TaskService.class, secure = false)
public class TaskServiceTest {
	
	@Autowired
	private TaskService taskService;
	
	@Test
	public void validateBracketsTrue() throws Exception {
		Boolean balanced = taskService.validateBrackets("({})");
		assertEquals(true, balanced);
	}
	
	@Test
	public void validateBracketsFalse() throws Exception {
		Boolean balanced = taskService.validateBrackets("(])");
		assertEquals(false, balanced);
	}
}
