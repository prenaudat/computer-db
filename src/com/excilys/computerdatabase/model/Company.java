package com.excilys.computerdatabase.model;

import java.math.BigInteger;

import com.sun.istack.internal.NotNull;

public class Company {
	
	//Instance variables
	@NotNull private long id;
	@NotNull private String name;
	
	//Behavior : getters and setter
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
	
	//Constructor from 
	public Company(long id, String name) {
		super();
		this.id = id;
		this.name = name;
	}
	
	@Override
	public String toString() {
		return new StringBuilder("Company [id=")
			.append(id)
			.append(", name=")
			.append(name)
			.append("]").toString();
	}

}
