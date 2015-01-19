package com.excilys.computerdatabase.model;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
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

	/**
	 * @return
	 */
	public LocalDate getIntroduced() {
		return introduced;
	}

	/**
	 * newDiscontinued
	 * 
	 * @param introduced
	 */
	public void setIntroduced(LocalDate introduced) {
		this.introduced = introduced;
	}

	/**
	 * @return
	 */
	public LocalDate getDiscontinued() {
		return discontinued;
	}

	/**
	 * @param discontinued
	 */
	public void setDiscontinued(LocalDate discontinued) {
		this.discontinued = discontinued;
	}

	/**
	 * @return
	 */
	public Company getCompany() {
		return company;
	}

	/**
	 * @param companyId
	 */
	public void setCompany(Company company) {
		this.company = company;
	}

	/**
	 * final String newFirstName, final String newCity, final String newState)
	 * 
	 * @param id
	 * @param name
	 * @param introduced
	 * @param discontinued
	 * @param companyId
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

	@Override
	public String toString() {
		return new StringBuilder("Computer [id=").append(id).append(", name=")
				.append(name).append(", introduced=").append(introduced)
				.append(", discontinued=").append(discontinued).append(company)
				.append("]").toString();
	}

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

	public static class ComputerBuilder {
		private long id;
		private String name;
		private LocalDate introduced;
		private LocalDate discontinued;
		private Company company;

		public ComputerBuilder() {
			this.id = 0;
			this.name = null;
			this.introduced = null;
			this.discontinued = null;
			this.company = null;
		}

		public ComputerBuilder id(final long newId) {
			this.id = newId;
			return this;
		}

		public ComputerBuilder name(final String newName) {
			this.name = newName;
			return this;
		}

		public ComputerBuilder introduced(final LocalDate newIntroduced) {
			this.introduced = newIntroduced;
			return this;
		}

		public ComputerBuilder discontinued(final LocalDate newDiscontinued) {
			this.discontinued = newDiscontinued;
			return this;
		}

		public ComputerBuilder company(final Company company) {
			this.company = new Company(company.getId(), company.getName());
			return this;
		}

		public Computer build() {
			return new Computer(id, name, introduced, discontinued, company);
		}
	}

}
