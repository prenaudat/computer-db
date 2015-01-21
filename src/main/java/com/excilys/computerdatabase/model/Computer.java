package com.excilys.computerdatabase.model;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * Computer Object Class
 * @author paulr_000
 *
 */
public class Computer {
	// Instance variables
	private long id;
	private String name;
	private LocalDate introduced;
	private LocalDate discontinued;
	private Company company;

	// Behavior : getters and setter
	/**
	 * @return Id of computer
	 */
	public long getId() {
		return id;
	}

	/**
	 * @param id Id to be set
	 */
	public void setId(long id) {
		this.id = id;
	}

	/**
	 * @return Name of computer
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name Name to be set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return Introduction date
	 */
	public LocalDate getIntroduced() {
		return introduced;
	}

	/** 
	 * @param introduced set Introduction date
	 */
	public void setIntroduced(LocalDate introduced) {
		this.introduced = introduced;
	}

	/**
	 * @return Discontinuation dates
	 */
	public LocalDate getDiscontinued() {
		return discontinued;
	}

	/**
	 * @param discontinued Set discontinuation date
	 */
	public void setDiscontinued(LocalDate discontinued) {
		this.discontinued = discontinued;
	}

	/**
	 * @return Company corresponding to Computer
	 */
	public Company getCompany() {
		return company;
	}

	/**
	 * @param company Company to be set for computer
	 */
	public void setCompany(Company company) {
		this.company = company;
	}

	/**
	 * Constructor from all fields
	 * 
	 * @param id id of computer
	 * @param name name of computer
	 * @param introduced introducation date of computer
	 * @param discontinued discontinuation date of computer
	 * @param companyId ID for company of computer
	 */
	public Computer(long id, String name, LocalDate introduced,
			LocalDate discontinued, Company company) {
		super();
		this.id = id;
		this.name = name;
		this.introduced = introduced;
		this.discontinued = discontinued;
		this.company = company;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return new StringBuilder("Computer [id=").append(id).append(", name=")
				.append(name).append(", introduced=").append(introduced)
				.append(", discontinued=").append(discontinued).append(company)
				.append("]").toString();
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((company == null) ? 0 : company.hashCode());
		result = prime * result
				+ ((discontinued == null) ? 0 : discontinued.hashCode());
		result = prime * result + (int) (id ^ (id >>> 32));
		result = prime * result
				+ ((introduced == null) ? 0 : introduced.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Computer other = (Computer) obj;
		if (company == null) {
			if (other.company != null)
				return false;
		} else if (!company.equals(other.company))
			return false;
		if (discontinued == null) {
			if (other.discontinued != null)
				return false;
		} else if (!discontinued.equals(other.discontinued))
			return false;
		if (id != other.id)
			return false;
		if (introduced == null) {
			if (other.introduced != null)
				return false;
		} else if (!introduced.equals(other.introduced))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

	/**
	 * Nested Builder class
	 * @author excilys
	 *
	 */
	public static class Builder {
		private long id;
		private String name;
		private LocalDate introduced;
		private LocalDate discontinued;
		private Company company;

		public Builder() {
			this.id = 0;
			this.name = null;
			this.introduced = null;
			this.discontinued = null;
			this.company = null;
		}

		public Builder id(final long newId) {
			this.id = newId;
			return this;
		}

		public Builder name(final String newName) {
			this.name = newName;
			return this;
		}

		public Builder introduced(final LocalDate newIntroduced) {
			this.introduced = newIntroduced;
			return this;
		}

		public Builder discontinued(final LocalDate newDiscontinued) {
			this.discontinued = newDiscontinued;
			return this;
		}

		public Builder company(final Company company) {
			this.company = new Company(company.getId(), company.getName());
			return this;
		}

		public Computer build() {
			return new Computer(id, name, introduced, discontinued, company);
		}
	}

}
