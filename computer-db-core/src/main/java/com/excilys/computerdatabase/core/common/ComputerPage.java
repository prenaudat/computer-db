package com.excilys.computerdatabase.core.common;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

/**
 * Page Class for sorting, ordering, sizing and searching
 * 
 * @author excilys
 */
public class ComputerPage extends PageRequest {
	private static final long serialVersionUID = 1L;
	private OrderBy orderBy;

	/**
	 * ComputerPage constructor
	 * @param page pageNumber
	 * @param size pageSize
	 */
	public ComputerPage(int page, int size) {
		super(page, size);

	}

	public ComputerPage(int page, int size, OrderBy orderBy) {
		super(page, size);
		this.setOrderBy(orderBy);
		System.out.println(orderBy);
	}

	public OrderBy getOrderBy() {
		return orderBy;
	}

	public void setOrderBy(OrderBy orderBy) {
		this.orderBy = orderBy;
	}

	public Sort getSort() {
		if (orderBy != null) {
			return orderBy.getValue();
		}
		return null;
	}


	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Page [sort=").append(orderBy.getValue())
		.append(" size=").append(this.getPageSize())
		.append(" pageNumber=").append(this.getPageNumber()).append("]");
		return builder.toString();
	}

}
