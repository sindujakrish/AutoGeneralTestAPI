package com.autogeneral.test.api.AutoGeneralTestAPI.controller;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.autogeneral.test.api.AutoGeneralTestAPI.model.TodoDTO;
import com.autogeneral.test.api.AutoGeneralTestAPI.service.TodoServiceImpl;

@RunWith(SpringRunner.class)
@WebMvcTest(value = TodoController.class, secure = false)
public class TodoControllerTest {
	
	@MockBean
	private TodoServiceImpl todoService;
	
	@Autowired
	private MockMvc mockMvc;
	
	@Test
	public void addTodoSuccess() throws Exception {
		String text = "Finish Unit Tests";
		boolean isCompleted = false;
		Date date = new Date();
		TodoDTO mockTodo = new TodoDTO(text, isCompleted, date);
		String jsonTodo = "{\"id\":100,\"text\":\"Finish Unit Tests\"}";
		Mockito.when(todoService.addTodo(text,date, isCompleted)).thenReturn(mockTodo);
		RequestBuilder reqBuilder = MockMvcRequestBuilders.post("/todo").content(jsonTodo).contentType(MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(reqBuilder).andReturn();
		MockHttpServletResponse actual = result.getResponse();
		assertEquals(HttpStatus.OK.value(), actual.getStatus());
	}
	
	@Test
	public void addTodoValidationError() throws Exception {
		String text = "";
		boolean isCompleted = false;
		Date date = new Date();
		TodoDTO mockTodo = new TodoDTO(text, isCompleted, date);
		String jsonTodo = "{\"id\":100,\"text\":\"\"}";
		Mockito.when(todoService.addTodo(text,date, isCompleted)).thenReturn(mockTodo);
		RequestBuilder reqBuilder = MockMvcRequestBuilders.post("/todo").content(jsonTodo).contentType(MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(reqBuilder).andReturn();
		String actual = result.getResponse().getContentAsString();
		String expected = "{\"details\":{\"location\":\"params\",\"param\":\"text\",\"msg\":\"Must be between 1 and 50 chars long\",\"value\":\"\"},\"name\":\"ValidationError\"}";
		assertEquals(expected, actual);
	}
	
	@Test
	public void retrieveTodoSuccess() throws Exception {
		Date date = new Date(1547855801500L);
		TodoDTO todo = new TodoDTO("Finish API", false, date);
		todo.setId(100);
		Mockito.when(todoService.retrieveTodo(100)).thenReturn(todo);
		RequestBuilder reqBuilder = MockMvcRequestBuilders.get("/todo/100").accept(MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(reqBuilder).andReturn();
		String actual = result.getResponse().getContentAsString();
		String expected = "{\"id\":100,\"text\":\"Finish API\",\"createdAt\":\"2019-01-18T23:56:41.500+0000\",\"completed\":false}";
		assertEquals(expected, actual);
	}
	
	@Test
	public void retrieveTodoNotFound() throws Exception {
		Mockito.when(todoService.retrieveTodo(102)).thenReturn(null);
		RequestBuilder reqBuilder = MockMvcRequestBuilders.get("/todo/102").accept(MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(reqBuilder).andReturn();
		String actual = result.getResponse().getContentAsString();
		assertTrue(actual.contains("Item with 102 not found"));
	}
	
	@Test
	public void updateTodoSuccess() throws Exception {
		String text = "Study Spring Boot";
		boolean isCompleted = true;
		Date date = new Date(1547855801500L);
		TodoDTO mockTodo = new TodoDTO(text, isCompleted, date);
		mockTodo.setId(1);
		String jsonTodo = " {\"text\": \"Study Spring Boot\",\"isCompleted\": true}";
		Mockito.when(todoService.updateTodo(Mockito.anyString(), Mockito.anyBoolean(), Mockito.anyInt())).thenReturn(mockTodo);
		RequestBuilder reqBuilder = MockMvcRequestBuilders.patch("/todo/1").content(jsonTodo).contentType(MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(reqBuilder).andReturn();
		String actual = result.getResponse().getContentAsString();
		String expected = "{\"id\":1,\"text\":\"Study Spring Boot\",\"createdAt\":\"2019-01-18T23:56:41.500+0000\",\"completed\":true}";
		assertEquals(expected, actual);
	}
	
	@Test
	public void updateTodoNotFound() throws Exception {
		String jsonTodo = " {\"text\": \"Study Spring Boot\",\"isCompleted\": true}";
		Mockito.when(todoService.updateTodo(Mockito.anyString(), Mockito.anyBoolean(), Mockito.anyInt())).thenReturn(null);
		RequestBuilder reqBuilder = MockMvcRequestBuilders.patch("/todo/102").content(jsonTodo).contentType(MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(reqBuilder).andReturn();
		String actual = result.getResponse().getContentAsString();
		assertTrue(actual.contains("Item with 102 not found"));
	}
}
