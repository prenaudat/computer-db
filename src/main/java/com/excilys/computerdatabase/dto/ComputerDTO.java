package com.excilys.computerdatabase.dto;

/**
 * @author excilys
 *Computer Data Transfer Object
 */
public class ComputerDTO implements java.io.Serializable{

	private static final long serialVersionUID = 5760780429754830864L;
	
	private long id;
	private String name;
	private String introduced;
	private String discontinued;
	private String company_Name;
	private long company_Id;
	
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
	public void setIntroduced(String introduced) {
		this.introduced = introduced;
	}
	public String getDiscontinued() {
		return discontinued;
	}
	public void setDiscontinued(String discontinued) {
		this.discontinued = discontinued;
	}
	public String getCompany_Name() {
		return company_Name;
	}
	public void setCompany_Name(String company_Name) {
		this.company_Name = company_Name;
	}
	public long getCompany_Id() {
		return company_Id;
	}
	public void setCompany_Id(long company_Id) {
		this.company_Id = company_Id;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ComputerDTO [id=").append(id).append(", name=")
				.append(name).append(", introduced=").append(introduced)
				.append(", discontinued=").append(discontinued)
				.append(", company_Name=").append(company_Name)
				.append(", company_Id=").append(company_Id).append("]");
		return builder.toString();
	}
	public ComputerDTO(long id, String name, String introduced,
			String discontinued, String company_Name, long company_Id) {
		super();
		this.id = id;
		this.name = name;
		this.introduced = introduced;
		this.discontinued = discontinued;
		this.company_Name = company_Name;
		this.company_Id = company_Id;
	}

	
}
