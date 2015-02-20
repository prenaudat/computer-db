package com.excilys.computerdatabase.binding.dto.model;

/**
 * Company Data Transfer Object
 * 
 * @author excilys
 */
public class CompanyDTO {
	private long id;
	private String name;

	/**
	 * Public constructor (needed by jackson)
	 */
	public CompanyDTO() {
		super();
	}

	/**
	 * Return Id of The Company
	 * 
	 * @return id The id of
	 */
	public long getId() {
		return id;
	}

	/**
	 * Set id of the companyDTO
	 * 
	 * @param id
	 */
	public void setId(long id) {
		this.id = id;
	}

	/**
	 * Returns name of the computer
	 * 
	 * @return name The computers name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Set name of computer
	 * 
	 * @param name
	 *            Computer name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Public constructor
	 * 
	 * @param id
	 *            Computer id
	 * @param name
	 *            Computer name
	 */
	public CompanyDTO(long id, String name) {
		super();
		this.id = id;
		this.name = name;
	}

	/**
	 * Nested builder 
	 * @param builder
	 */
	private CompanyDTO(Builder builder) {
		this.id = builder.id;
		this.name = builder.name;
	}

	/**
	 * Nested builder class
	 * @author excilys
	 *
	 */
	public static class Builder {

		private long id;
		private String name;

		/**
		 * Add id to builder
		 * @param id Computer id
		 * @return Builder current builder
		 */
		public Builder withId(long id) {
			this.id = id;
			return this;
		}

		/**
		 * Add name to builder
		 * @param name Computer name
		 * @return Builder current builder
		 */
		public Builder withName(String name) {
			this.name = name;
			return this;
		}

		/**
		 * Build the builder
		 * @return CompanyDTO 
		 */
		public CompanyDTO build() {
			return new CompanyDTO(this);
		}
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder2 = new StringBuilder();
		builder2.append("CompanyDTO [id=").append(id).append(", name=")
				.append(name).append("]");
		return builder2.toString();
	}

}
