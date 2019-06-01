package com.autogeneral.test.api.AutoGeneralTestAPI;

import static org.junit.Assert.assertTrue;

import java.io.UnsupportedEncodingException;
import java.util.Arrays;

import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import com.autogeneral.test.api.AutoGeneralTestAPI.AutoGeneralTestApiApplication;
import com.autogeneral.test.api.AutoGeneralTestAPI.model.TodoItemAddRequest;
import com.autogeneral.test.api.AutoGeneralTestAPI.model.TodoItemUpdateRequest;

@RunWith(SpringRunner.class)
@SpringBootTest(classes=AutoGeneralTestApiApplication.class, webEnvironment= SpringBootTest.WebEnvironment.RANDOM_PORT)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class AutoGeneralTestAPIIT {
	
	HttpHeaders headers = new HttpHeaders();
	TestRestTemplate restTemplate = new TestRestTemplate();
	
    private RestTemplate patchRestTemplate;
	
	@Before
	public void before() {
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		this.patchRestTemplate = restTemplate.getRestTemplate();
        HttpClient httpClient = HttpClientBuilder.create().build();
        this.patchRestTemplate.setRequestFactory(new HttpComponentsClientHttpRequestFactory(httpClient));
	}
	
	@LocalServerPort
	private int port;

	@Test
	public void test1ValidateBrackets() throws UnsupportedEncodingException {
		String url = "http://localhost:"+port+"/tasks/validateBrackets?input={str}";
		String str = "{[]}";
		HttpEntity<String> requestEntity = new HttpEntity<String>(null, headers);
		ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, requestEntity, String.class,str);
		System.out.println(response.getBody());
		assertTrue(response.getBody().contains("\"balanced\":true"));
	}
	

	@Test
	public void test2AddTodo() {
		String url = "http://localhost:"+port+"/todo";
		TodoItemAddRequest todoObj = new TodoItemAddRequest("Sample Text");
		HttpEntity<TodoItemAddRequest> requestEntity = new HttpEntity<TodoItemAddRequest>(todoObj, headers);
		
		ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, requestEntity, String.class);
		assertTrue(response.getBody().contains("Sample Text"));
	}
	
	@Test
	public void test3RetrieveTodo() {
		String url = "http://localhost:"+port+"/todo/1";
		HttpEntity<String> requestEntity = new HttpEntity<String>(null, headers);
		
		ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, requestEntity, String.class);
		assertTrue(response.getBody().contains("Learn Spring MVC"));
	}
	
	@Test
	public void test4UpdateTodo() {
		String url = "http://localhost:"+port+"/todo/1";
		TodoItemUpdateRequest todoObj = new TodoItemUpdateRequest("Sample Text", true);
		HttpEntity<TodoItemUpdateRequest> requestEntity = new HttpEntity<TodoItemUpdateRequest>(todoObj, headers);
		
		ResponseEntity<String> response = patchRestTemplate.exchange(url, HttpMethod.PATCH, requestEntity, String.class);
		assertTrue(response.getBody().contains("Sample Text"));


	}
}

