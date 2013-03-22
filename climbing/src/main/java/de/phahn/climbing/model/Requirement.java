package de.phahn.climbing.model;

import org.springframework.data.annotation.Id;

public class Requirement {

	@Id
	private String id;
	
	private String title;
	private String description;
	
	// getters and setters
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	
	
}
