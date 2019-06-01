package com.autogeneral.test.api.AutoGeneralTestAPI.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * 
 * The model object within the application used to hold the todo details.
 *
 */
@Entity
public class TodoItem {
	
	@Id
	@GeneratedValue
	private int id;
	private String text;
	private boolean isCompleted;
	private Date createdAt;

	public TodoItem() {
		super();
	}

	public TodoItem(String text, boolean isCompleted, Date createdAt) {
		this.text = text;
		this.isCompleted = isCompleted;
		this.createdAt = createdAt;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public boolean isCompleted() {
		return isCompleted;
	}

	public void setCompleted(boolean isCompleted) {
		this.isCompleted = isCompleted;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}
	
	
}

