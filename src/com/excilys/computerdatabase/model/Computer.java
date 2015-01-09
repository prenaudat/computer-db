package com.excilys.computerdatabase.model;

import java.time.LocalDateTime;

import com.sun.istack.internal.NotNull;

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
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public LocalDateTime getIntroduced() {
		return introduced;
	}

	public void setIntroduced(LocalDateTime introduced) {
		this.introduced = introduced;
	}

	public LocalDateTime getDiscontinued() {
		return discontinued;
	}

	public void setDiscontinued(LocalDateTime discontinued) {
		this.discontinued = discontinued;
	}

	public long getCompanyId() {
		return companyId;
	}

	public void setCompanyId(long companyId) {
		this.companyId = companyId;
	}

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
	public Computer(ComputerBuilder b){
		this.id=b.id;
		this.name=b.name;
		this.introduced=b.introduced;
		this.discontinued=b.discontinued;
		this.companyId=b.companyId;
	}

	public static class ComputerBuilder {
		@NotNull
		private long id;
		@NotNull
		private String name;
		private LocalDateTime introduced;
		private LocalDateTime discontinued;
		private long companyId;

		public ComputerBuilder(int id) {
			this.id = id;
		}

		public ComputerBuilder name(String name) {
			this.name = name;
			return this;
		}

		public ComputerBuilder introduced(LocalDateTime introduced) {
			this.introduced = introduced;
			return this;
		}

		public ComputerBuilder discontinued(LocalDateTime discontinued) {
			this.discontinued = discontinued;
			return this;
		}

		public ComputerBuilder companyId(long companyId) {
			this.companyId = companyId;
			return this;
		}
		private Computer build(){
			return new Computer(this);
		}

	}

}
