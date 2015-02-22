package com.excilys.computerdatabase.binding.dto.model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Computer Data Transfer Object
 * @author excilys 
 */
public class ComputerDTO implements java.io.Serializable {

	/**
	 * Serializable
	 */
	private static final long serialVersionUID = 1L;
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

	/**
	 * Get company name
	 * @return  companyName Name of company
	 */
	public String getCompanyName() {
		return companyName;
	}

	/**
	 * Set company name
	 * @param companyName Name of company
	 */
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	/**
	 * Get id
	 * @return id The id of ComputerDTO
	 */
	public Long getId() {
		return id;
	}

	/**
	 * Set id 
	 * @param id Id of computerDTO
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * get the ComputerDTO name
	 * @return String name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name Name of the computer
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Get introduction date 
	 * @return String representation of introduction date
	 */
	public String getIntroduced() {
		return introduced;
	}

	/**
	 * Get discontinuation date
	 * @return String representation of discontinuation date
	 */
	public String getDiscontinued() {
		return discontinued;
	}

	/**
	 * Set discontinuation date
	 * @param discontinued String representation of discontinuation date
	 */
	public void setDiscontinued(String discontinued) {
		if (discontinued != null) {
			this.discontinued = discontinued.toString();
		}
	}

	/**
	 * Get company Id
	 * @return company id
	 */
	public Long getCompanyId() {
		return companyId;
	}

	/**
	 * Set Company Id
	 * @param companyId Long company Id
	 */
	public void setCompanyId(Long companyId) {
		this.companyId = companyId;
	}

	/**
	 * Set introduction date
	 * @param introduced String introduction date
	 */
	public void setIntroduced(String introduced) {
		if (introduced != null) {
			this.introduced = introduced.toString();
		}
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
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

	/**
	 * Constructor from all parameters
	 * @param id Id of computer
	 * @param name Name of computer
	 * @param introduced Introduction date of ComputerDTO (String)
	 * @param discontinued Discontinuation date of computerDTO(String)
	 * @param company_Name Company name of ComupterDTO
	 * @param company_Id Company Id of ComputerDTO
	 */
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

	/**
	 * Null default constructor
	 */
	public ComputerDTO() {
		this.id = null;
		this.name = null;
		this.introduced = null;
		this.discontinued = null;
		this.companyId = null;
		this.companyName = null;
	}

	/**
	 * Constructor from ComputerDTO builder
	 * @param builder ComputerDTO.Builder
	 */
	private ComputerDTO(Builder builder) {
		this.id = builder.id;
		this.name = builder.name;
		this.introduced = builder.introduced;
		this.discontinued = builder.discontinued;
		this.companyId = builder.companyId;
		this.companyName = builder.companyName;
	}

	/**
	 * Nested Builder class
	 * @author excilys
	 *
	 */
	public static class Builder {

		private Long id;
		private String name;
		private String introduced;
		private String discontinued;
		private Long companyId;
		private String companyName;

		/**
		 * Add id to builder
		 * @param id Long id of ComputerDTO
		 * @return current builder
		 */
		public Builder withId(Long id) {
			this.id = id;
			return this;
		}

		/** 
		 * Add name to builder
		 * @param name String name
		 * @return current builder
		 */
		public Builder withName(String name) {
			this.name = name;
			return this;
		}

		/**
		 * Add string introduced to builder
		 * @param introduced String introduction date
		 * @return current builder
		 */
		public Builder withIntroduced(String introduced) {
			this.introduced = introduced.toString();
			return this;
		}

		/**
		 * Add string discontinued to builder
		 * @param discontinued String date
		 * @return current builder
		 */
		public Builder withDiscontinued(String discontinued) {
			if (discontinued != null) {
				this.discontinued = discontinued.toString();
			}
			return this;
		}

		/**
		 * Adds Introduced date with locale to DTO
		 * @param introduced LocalDate introduction
		 * @param locale en or fr supported
		 * @return current builder
		 */
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

		/**
		 * Adds Discontinued date with locale to DTO
		 * @param discontinued LocalDate discontinuation
		 * @param locale en or fr supported
		 * @return current builder
		 */
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

		/**
		 * Add company Id to builder
		 * @param companyId Id of company
		 * @return current builder
		 */
		public Builder withCompanyId(Long companyId) {
			this.companyId = companyId;
			return this;
		}

		/**
		 * Add companyName to ComputerDTO
		 * @param companyName Company name for ComputerDTO
		 * @return this current builder
		 */
		public Builder withCompanyName(String companyName) {
			this.companyName = companyName;
			return this;
		}

		/**
		 * Returns build computerDTO
		 * @return ComputerDTO
		 */
		public ComputerDTO build() {
			return new ComputerDTO(this);
		}
	}

}
