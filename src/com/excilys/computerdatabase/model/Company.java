package com.excilys.computerdatabase.model;

import java.math.BigInteger;

import com.sun.istack.internal.NotNull;

/**
 * @author paulr_000
 *
 */
public class Company {
	
	//Instance variables
	@NotNull private long id;
	@NotNull private String name;
	
	//Behavior : getters and setter
	/**
	 * @return
	 */
	public long getId() {
		return id;
	}
	/**
	 * @param id
	 */
	public void setId(long id) {
		this.id = id;
	}
	/**
	 * @return
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	//Constructor from 
	/**
	 * @param id
	 * @param name
	 */
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
