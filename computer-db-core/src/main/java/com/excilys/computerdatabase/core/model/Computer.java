package com.excilys.computerdatabase.core.model;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.excilys.computerdatabase.core.common.LocalDatePersistenceConverter;

/**
 * Computer Object Class
 * 
 * @author paulr_000
 *
 */
@Entity
@Table(name = "computer")
public class Computer {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@Column(name = "name")
	private String name;
	@Column(name = "introduced")
	@Convert(converter = LocalDatePersistenceConverter.class)
	private LocalDate introduced;
	@Column(name = "discontinued")
	@Convert(converter = LocalDatePersistenceConverter.class)
	private LocalDate discontinued;
	@ManyToOne(targetEntity = com.excilys.computerdatabase.core.model.Company.class)
	@JoinColumn(name = "company_id", nullable = true)
	private Company company;

	// Behavior : getters and setter
	/**
	 * @return Id of computer
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id  Id to be set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @return Name of computer
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name  Name to be set
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
	 * @param introduced  set Introduction date
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
	 * @param discontinued
	 *            Set discontinuation date
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
	 * @param company
	 *            Company to be set for computer
	 */
	public void setCompany(Company company) {
		this.company = company;
	}

	public LocalDateTime getIntroducedAsLDT() {
		if (introduced != null) {
			return introduced.atStartOfDay();
		} else {
			return null;
		}
	}

	public LocalDateTime getDiscontinuedAsLDT() {
		if (discontinued != null) {
			return discontinued.atStartOfDay();
		} else {
			return null;
		}
	}

	public Timestamp getIntroducedAsTimestamp() {
		if (introduced != null) {
			return Timestamp.valueOf(introduced.atStartOfDay());
		} else {
			return null;
		}
	}

	public Timestamp getDiscontinuedAsTimestamp() {
		if (discontinued != null) {
			return Timestamp.valueOf(discontinued.atStartOfDay());
		} else {
			return null;
		}
	}

	public Long getCompanyId() {
		if (company != null && company.getId() != 0) {
			return company.getId();
		} else {
			return null;
		}
	}

	public String getCompanyName() {
		if (company == null) {
			return null;
		}
		return company.getName();
	}

	/**
	 * Constructor from all fields
	 * 
	 * @param id   id of computer
	 * @param name    name of computer
	 * @param introduced introducation date of computer
	 * @param discontinued  discontinuation date of computer
	 * @param company   company of computer
	 */
	public Computer(Long id, String name, LocalDate introduced,
			LocalDate discontinued, Company company) {
		super();
		this.id = id;
		this.name = name;
		this.introduced = introduced;
		this.discontinued = discontinued;
		this.company = company;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return new StringBuilder("Computer [id=").append(id).append(", name=")
				.append(name).append(", introduced=").append(introduced)
				.append(", discontinued=").append(discontinued).append(company)
				.append("]").toString();
	}

	public Computer() {
		super();
		this.id = null;
		this.name = null;
		this.introduced = null;
		this.discontinued = null;
		this.company = null;
	}

	/*
	 * (non-Javadoc)
	 * 
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

	/*
	 * (non-Javadoc)
	 * 
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
	 * 
	 * @author excilys
	 *
	 */
	public static class Builder {
		private Computer computer = new Computer();

		public Builder() {
			computer.id = null;
			computer.name = "";
			computer.introduced = null;
			computer.discontinued = null;
			computer.company = new Company();
		}

		public Builder id(final Long newId) {
			computer.setId(newId);
			return this;
		}

		public Builder name(final String newName) {
			this.computer.setName(newName);
			return this;
		}

		public Builder introduced(final LocalDate newIntroduced) {
			this.computer.setIntroduced(newIntroduced);
			return this;
		}

		public Builder introduced(final String newIntroduced, final String lang) {
			if (newIntroduced != null && newIntroduced.length() != 0) {
				LocalDate date = null;
				try{
				DateTimeFormatter formatter;
				if (lang.equals("fr")) {
					formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
					System.out.println(formatter.toString());
					date = LocalDate.parse(newIntroduced, formatter);
				} else {
					formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
					date = LocalDate.parse(newIntroduced, formatter);
				}
			}catch (Exception e) {
				date = null;
			}
				this.computer.setIntroduced(date);
			}
			return this;
		}

		public Builder discontinued(final LocalDate newDiscontinued) {
			this.computer.setDiscontinued(newDiscontinued);
			return this;
		}

		public Builder discontinued(final String discontinued, final String lang) {
			if (discontinued != null && discontinued.length() != 0) {
				LocalDate date = null;
				try {
				DateTimeFormatter formatter;
				if (lang.equals("fr")) {
					formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
					System.out.println(formatter.toString());
					date = LocalDate.parse(discontinued, formatter);
				} else {
					formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
					date = LocalDate.parse(discontinued, formatter);
				}
				} catch (Exception e) {
					date = null;
				}
				this.computer.setDiscontinued(date);
			}
			return this;
		}

		public Builder company(final Company company) {
			if(company.getId()!= null){
			this.computer.company.setId(company.getId());
			this.computer.company.setName(company.getName());
			}
			return this;
			
		}

		public Computer build() {
			return computer;
		}
	}

}
