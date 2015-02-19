package com.excilys.computerdatabase.binding.dto.model;

/**
 * @author excilys
 *Company Data Transfer Object
 */
public class CompanyDTO {
	private long id;
	private String name;
	
	public CompanyDTO() {
		super();
	}
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
	private CompanyDTO(Builder builder) {
	  this.id = builder.id;
	  this.name = builder.name;
	}
	public static class Builder{

		private long id;
		private String name;
		public Builder withId(long id) {
		  this.id = id;
		  return this;
		}
		public Builder withName(String name) {
		  this.name = name;
		  return this;
		}
		public CompanyDTO build() {
		  return new CompanyDTO(this);
		}
	}
	@Override
	public String toString() {
		StringBuilder builder2 = new StringBuilder();
		builder2.append("CompanyDTO [id=").append(id).append(", name=")
				.append(name).append("]");
		return builder2.toString();
	}
	
	
}
