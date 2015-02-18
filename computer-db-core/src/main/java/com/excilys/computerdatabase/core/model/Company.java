package com.excilys.computerdatabase.core.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 * Company Model
 * 
 * @author excilys
 *
 */
@Entity
@Table(name = "company")
public class Company {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@NotNull
	private Long id;
	@Column(name = "name")
	@NotNull
	private String name;
	// Default Constructor
	protected Company() {
	}

	// Behavior : getters and setter
	/**
	 * @return return ID of company
	 */
	public long getId() {
		return id;
	}

	/**
	 * @param id
	 *            ID to be set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @return the name
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

	// Constructor from
	/**
	 * @param id
	 *            Id of comapany
	 * @param name
	 *            Name of company
	 */
	public Company(long id, String name) {
		super();
		this.id = id;
		this.name = name;
	}

	@Override
	public String toString() {
		return new StringBuilder("Company [id=").append(id).append(", name=")
				.append(name).append("]").toString();
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
		result = prime * result + (int) (id ^ (id >>> 32));
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(final Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Company other = (Company) obj;
		if (id != other.id)
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
		private Company company = new Company();

		public Builder() {
			company.setId(null);
			company.name = null;
		}

		public Builder id(final long id) {
			company.id = id;
			return this;
		}

		public Builder name(final String name) {
			company.name = name;
			return this;
		}

		public Company build() {
			return company;
		}

	}
}
