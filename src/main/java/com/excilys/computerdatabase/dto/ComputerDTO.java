package com.excilys.computerdatabase.dto;

import java.time.LocalDate;

import org.apache.commons.lang3.StringUtils;

import com.excilys.computerdatabase.model.Company;
import com.google.common.base.Preconditions;

/**
 * @author excilys Computer Data Transfer Object
 */
public class ComputerDTO implements java.io.Serializable {

	private static final long serialVersionUID = 5760780429754830864L;

	private long id;
	private String name;
	private String introduced;
	private String discontinued;
	private Company company;

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

	public String getIntroduced() {
		return introduced;
	}

	public void setIntroduced(LocalDate introduced) {
		if(introduced!=null){
			this.introduced = introduced.toString();
		}
	}
	
	public String getDiscontinued() {
		return discontinued;
	}

	public void setDiscontinued(String discontinued) {
		if(discontinued!=null){
			this.discontinued = discontinued.toString();
		}	}


	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		if(company != null){
		this.company.setId(company.getId());
		this.company.setName(company.getName());
		}else{
			this.company = new Company();
		}
		
	}

	public void setIntroduced(String introduced) {
		this.introduced = introduced;
	}



	@Override
	public String toString() {
		StringBuilder builder= new StringBuilder();
		builder.append("ComputerDTO [id=").append(id).append(", name=")
				.append(name).append(", introduced=").append(introduced)
				.append(", discontinued=").append(discontinued)
				.append(", company=").append(company).append("]");
		return builder.toString();
	}

	public ComputerDTO(long id, String name, String introduced,
			String discontinued, String company_Name, long company_Id) {
		super();
		this.id = id;
		this.name = name;
		this.introduced = introduced;
		this.discontinued = discontinued;
	}

	private ComputerDTO(Builder builder) {
		this.id = builder.id;
		this.name = builder.name;
		this.introduced = builder.introduced;
		this.discontinued = builder.discontinued;
		this.company = builder.company;
	}

	public static class Builder {

		private long id;
		private String name;
		private String introduced;
		private String discontinued;
		private Company company;

		public Builder withId(long id) {
			this.id = id;
			return this;
		}

		public Builder withName(String name) {
			this.name = name;
			return this;
		}

		public Builder withIntroduced(LocalDate introduced) {
			if(introduced != null){
			this.introduced = introduced.toString();
			}
			return this;
		}

		public Builder withDiscontinued(LocalDate discontinued) {
			if(discontinued != null){
			this.discontinued = discontinued.toString();
			}
			return this;
		}

		public Builder withCompany(Company company) {
			this.company = company;
			return this;
		}

		public ComputerDTO build() {
			return new ComputerDTO(this);
		}
	}

}
