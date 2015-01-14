package com.excilys.computerdatabase.model;

/**
 * @author paulr_000
 *
 */
public class Company {

	// Instance variables
	private long id;
	private String name;

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

	// Constructor from
	/**
	 * @param id
	 * @param name
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (id ^ (id >>> 32));
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

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
