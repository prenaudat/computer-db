package com.excilys.computerdatabase.model;

/**
 * Company Model
 * @author paulr_000
 *
 */
public class Company {

	// Instance variables
	private long id;
	private String name;

	// Behavior : getters and setter
	/**
	 * @return return ID of company
	 */
	public long getId() {
		return id;
	}

	/**
	 * @param id ID to be set
	 */
	public void setId(long id) {
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
	 * @param id Id of comapany
	 * @param name Name of company
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

	/* (non-Javadoc)
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

	/* (non-Javadoc)
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
	 * @author excilys
	 *
	 */
	public static class CompanyBuilder {
		private long id;
		private String name;

		public CompanyBuilder() {
			this.id = 0;
			this.name = null;
		}

		public CompanyBuilder id(final long id) {
			this.id = id;
			return this;
		}

		public CompanyBuilder name(final String name) {
			this.name = name;
			return this;
		}

		public Company build() {
			return new Company(this.id, this.name);
		}

	}
}
