package com.excilys.computerdatabase.model;

import java.time.LocalDateTime;

import com.sun.istack.internal.NotNull;

/**
 * @author paulr_000
 *
 */
public class Computer {
	// Instance variables
	@NotNull
	private long id;
	@NotNull
	private String name;
	@NotNull
	private LocalDateTime introduced;
	private LocalDateTime discontinued;
	private long companyId;

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
	public LocalDateTime getIntroduced() {
		return introduced;
	}

	/**
	 * @param introduced
	 */
	public void setIntroduced(LocalDateTime introduced) {
		this.introduced = introduced;
	}

	/**
	 * @return
	 */
	public LocalDateTime getDiscontinued() {
		return discontinued;
	}

	/**
	 * @param discontinued
	 */
	public void setDiscontinued(LocalDateTime discontinued) {
		this.discontinued = discontinued;
	}

	/**
	 * @return
	 */
	public long getCompanyId() {
		return companyId;
	}

	/**
	 * @param companyId
	 */
	public void setCompanyId(long companyId) {
		this.companyId = companyId;
	}

	/**
	 * @param id
	 * @param name
	 * @param introduced
	 * @param discontinued
	 * @param companyId
	 */
	public Computer(long id, String name, LocalDateTime introduced,
			LocalDateTime discontinued, long companyId) {
		super();
		this.id = id;
		this.name = name;
		this.introduced = introduced;
		this.discontinued = discontinued;
		this.companyId = companyId;
	}

	@Override
	public String toString() {
		return new StringBuilder("Computer [id=").append(id).append(", name=")
				.append(name).append(", introduced=").append(introduced)
				.append(", discontinued=").append(discontinued)
				.append(", companyId=").append(companyId).append("]")
				.toString();
	}
}
