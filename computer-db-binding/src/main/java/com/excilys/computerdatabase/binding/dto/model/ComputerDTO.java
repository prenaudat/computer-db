package com.excilys.computerdatabase.binding.dto.model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * @author excilys Computer Data Transfer Object
 */
public class ComputerDTO implements java.io.Serializable {

	private static final Long serialVersionUID = 5760780429754830864L;
	@NotNull
	@Min(0)
	@Size(max = 20)
	private Long id;
	@Min(1)
	@Size(max = 255)
	private String name;
	@Size(max = 10)
	private String introduced;
	@Size(max = 10)
	private String discontinued;
	private Long companyId;
	private String companyName;

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
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

	public String getDiscontinued() {
		return discontinued;
	}

	public void setDiscontinued(String discontinued) {
		if (discontinued != null) {
			this.discontinued = discontinued.toString();
		}
	}

	public Long getCompanyId() {
		return companyId;
	}

	public void setCompanyId(Long companyId) {
		this.companyId = companyId;
	}

	public void setIntroduced(String introduced) {
		if (introduced != null) {
			this.introduced = introduced.toString();
		}
	}

	@Override
	public String toString() {
		StringBuilder builder2 = new StringBuilder();
		builder2.append("ComputerDTO [id=").append(id).append(", name=")
				.append(name).append(", introduced=").append(introduced)
				.append(", discontinued=").append(discontinued)
				.append(", companyId=").append(companyId)
				.append(", companyName=").append(companyName).append("]");
		return builder2.toString();
	}

	public ComputerDTO(Long id, String name, String introduced,
			String discontinued, String company_Name, Long company_Id) {
		super();
		this.id = id;
		this.name = name;
		this.introduced = introduced;
		this.discontinued = discontinued;
		this.companyId = company_Id;
		this.companyName = company_Name;
	}

	public ComputerDTO() {
		this.id = null;
		this.name = null;
		this.introduced = null;
		this.discontinued = null;
		this.companyId = null;
		this.companyName = null;
	}

	private ComputerDTO(Builder builder) {
		this.id = builder.id;
		this.name = builder.name;
		this.introduced = builder.introduced;
		this.discontinued = builder.discontinued;
		this.companyId = builder.companyId;
		this.companyName = builder.companyName;
	}

	public static class Builder {

		private Long id;
		private String name;
		private String introduced;
		private String discontinued;
		private Long companyId;
		private String companyName;

		public Builder withId(Long id) {
			this.id = id;
			return this;
		}

		public Builder withName(String name) {
			this.name = name;
			return this;
		}

		public Builder withIntroduced(String introduced) {
			this.introduced = introduced.toString();
			return this;
		}

		public Builder withDiscontinued(String discontinued) {
			if (discontinued != null) {
				this.discontinued = discontinued.toString();
			}
			return this;
		}

		public Builder withIntroduced(LocalDate introduced, String locale) {
			if (introduced != null) {
				if (locale.equals("fr")) {
					this.introduced = introduced.format(DateTimeFormatter
							.ofPattern("dd-MM-yyyy"));
				} else {
					this.introduced = introduced.format(DateTimeFormatter
							.ofPattern("yyyy-MM-dd"));
				}
			}
			return this;
		}

		public Builder withDiscontinued(LocalDate discontinued, String locale) {
			if (discontinued != null) {
				if (locale.equals("fr")) {
					this.discontinued = discontinued.format(DateTimeFormatter
							.ofPattern("dd-MM-yyyy"));
				} else {
					this.discontinued = discontinued.format(DateTimeFormatter
							.ofPattern("yyyy-MM-dd"));
				}
			}
			return this;
		}

		public Builder withCompanyId(Long companyId) {
			this.companyId = companyId;
			return this;
		}

		public Builder withCompanyName(String companyName) {
			this.companyName = companyName;
			return this;
		}

		public ComputerDTO build() {
			return new ComputerDTO(this);
		}
	}

}
