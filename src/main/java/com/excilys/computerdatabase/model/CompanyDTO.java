package com.excilys.computerdatabase.model;

/**
 * @author excilys
 *Company Data Transfer Object
 */
public class CompanyDTO {
	private long id;
	private String name;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public CompanyDTO(long id, String name) {
		super();
		this.id = id;
		this.name = name;
	}
	
}
