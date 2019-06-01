package com.autogeneral.test.api.AutoGeneralTestAPI.controller;


import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.autogeneral.test.api.AutoGeneralTestAPI.service.TaskServiceImpl;

@RunWith(SpringRunner.class)
@WebMvcTest(value = TaskController.class, secure = false)
public class TaskControllerTest {
	
	@MockBean
	private TaskServiceImpl taskService;
	
	@Autowired
	private MockMvc mockMvc;
	
	@Test
	public void validateBracketsTrue() throws Exception {
		Mockito.when(taskService.validateBrackets("({})")).thenReturn(true);
		RequestBuilder reqBuilder = MockMvcRequestBuilders.get("/tasks/validateBrackets?input=({})").accept(MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(reqBuilder).andReturn();
		String actual = result.getResponse().getContentAsString();
		String expected = "{\"input\":\"({})\",\"balanced\":true}";
		assertEquals(expected, actual);
	}
	
	@Test
	public void validateBracketsFalse() throws Exception {
		Mockito.when(taskService.validateBrackets("(])")).thenReturn(false);
		RequestBuilder reqBuilder = MockMvcRequestBuilders.get("/tasks/validateBrackets?input=(])").accept(MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(reqBuilder).andReturn();
		String actual = result.getResponse().getContentAsString();
		String expected = "{\"input\":\"(])\",\"balanced\":false}";
		assertEquals(expected, actual);
	}
	
	@Test
	public void validateBracketsValidationError() throws Exception {
		
		RequestBuilder reqBuilder = MockMvcRequestBuilders.get("/tasks/validateBrackets?input=").accept(MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(reqBuilder).andReturn();
		String actual = result.getResponse().getContentAsString();
		String expected = "{\"details\":{\"location\":\"params\",\"param\":\"text\",\"msg\":\"Must be between 1 and 50 chars long\",\"value\":\"\"},\"name\":\"ValidationError\"}";
		assertEquals(expected, actual);
	}

}
