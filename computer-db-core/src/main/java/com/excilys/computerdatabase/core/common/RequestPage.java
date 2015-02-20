package com.excilys.computerdatabase.core.common;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

/**
 * Page Class for sorting, ordering, sizing and searching
 * 
 * @author excilys
 */
public class RequestPage extends PageRequest {
	private static final long serialVersionUID = 1L;
	private OrderBy orderBy;

	/**
	 * ComputerPage constructor
	 * 
	 * @param page
	 *            pageNumber
	 * @param size
	 *            pageSize
	 */
	public RequestPage(int page, int size) {
		super(page, size);

	}

	/**
	 * Constructor
	 * 
	 * @param page
	 *            pageNumber
	 * @param size
	 *            size of page
	 * @param orderBy
	 *            OrderBy
	 */
	public RequestPage(int page, int size, OrderBy orderBy) {
		super(page, size);
		this.setOrderBy(orderBy);
	}

	/**
	 * Get order by
	 * @return OrderBy 
	 */
	public OrderBy getOrderBy() {
		return orderBy;
	}

	/**
	 * Set OrderBy
	 * @param orderBy Sorting parameter for the page
	 */
	public void setOrderBy(OrderBy orderBy) {
		this.orderBy = orderBy;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.data.domain.PageRequest#getSort()
	 */
	public Sort getSort() {
		if (orderBy != null) {
			return orderBy.getValue();
		}
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.data.domain.PageRequest#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Page [sort=").append(orderBy.getValue())
				.append(" size=").append(this.getPageSize())
				.append(" pageNumber=").append(this.getPageNumber())
				.append("]");
		return builder.toString();
	}

}
