package com.autogeneral.test.api.AutoGeneralTestAPI.service;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import java.util.Date;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import com.autogeneral.test.api.AutoGeneralTestAPI.jpa.TodoRepository;
import com.autogeneral.test.api.AutoGeneralTestAPI.model.TodoDTO;
import com.autogeneral.test.api.AutoGeneralTestAPI.model.TodoItem;

@RunWith(SpringRunner.class)
public class TodoServiceTest {
	
	@TestConfiguration
    static class TodoServiceImplTestContextConfiguration {
  
        @Bean
        public TodoServiceImpl cashService() {
            return new TodoServiceImpl();
        }
    }
	
	@Autowired
	private TodoService todoService;
	
	@MockBean
	TodoRepository todoRepository;
	
	@Before
	public void setup() {
		TodoItem todoItem1 = new TodoItem("Sample todo", true, new Date());
		Mockito.when(todoRepository.save(Mockito.any())).thenReturn(todoItem1);
		Mockito.when(todoRepository.findById(10)).thenReturn(todoItem1);
	}
	
	@Test
	public void addTodoSuccess() throws Exception {
		TodoDTO actualTodo = todoService.addTodo("Sample todo",new Date(), true);
		assertNotEquals(null, actualTodo);
	}
		
	@Test
	public void retrieveTodoSuccess() throws Exception {
		TodoDTO todo = new TodoDTO("Sample todo", true, new Date());
		TodoDTO actualTodo = todoService.retrieveTodo(10);
		assertEquals(todo.getText(), actualTodo.getText());
		assertEquals(todo.isCompleted(), actualTodo.isCompleted());
	}
	
	@Test
	public void retrieveTodoNotFound() throws Exception {
		TodoDTO actualTodo = todoService.retrieveTodo(130);
		assertEquals(null, actualTodo);
	}
	
	@Test
	public void updateTodoSuccess() throws Exception {
		String text = "Sample todo";
		boolean isCompleted = true;
		TodoDTO mockTodo = new TodoDTO(text, isCompleted, new Date());
		TodoDTO actualTodo = todoService.updateTodo(text, isCompleted, 10);
		assertEquals(mockTodo.getText(), actualTodo.getText());
		assertEquals(mockTodo.isCompleted(), actualTodo.isCompleted());
	}
	
	@Test
	public void updateTodoNotFound() throws Exception {
		TodoDTO actualTodo = todoService.updateTodo("Sample Todo", false, 109);
		assertEquals(null, actualTodo);
	}
}
