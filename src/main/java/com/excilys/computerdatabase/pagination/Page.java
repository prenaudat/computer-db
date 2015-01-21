package com.excilys.computerdatabase.pagination;

import java.util.ArrayList;
import java.util.List;

import com.excilys.computerdatabase.model.Computer;

public class Page {
	// A page has a size, a pageNumber, a list of Computers, is sorted(id, name,
	// introduced company or discontinued) and ordered(asc or desc)
	private int size;
	private int pageNumber;
	private List<Computer> list;
	private OrderBy orderBy;
	private Sort sort;
	private int count;
	private int pageCount;
	private String query;

	/**
	 * @return
	 */
	public String getQuery() {
		return query;
	}

	/**
	 * @param query
	 */
	public void setQuery(String query) {
		this.query = query;
	}

	/**
	 * @return
	 */
	public int getPageCount() {
		return pageCount;
	}

	/**
	 * @param pageCount
	 */
	public void setPageCount(int pageCount) {
		this.pageCount = pageCount;
	}

	/**
	 * @return
	 */
	public int getSize() {
		return size;
	}

	/**
	 * @param size
	 */
	public void setSize(int size) {
		this.size = size;
	}

	/**
	 * @return
	 */
	public int getPageNumber() {
		return pageNumber;
	}

	/**
	 * @param pageNumber
	 */
	public void setPageNumber(int pageNumber) {
		this.pageNumber = pageNumber;
	}

	/**
	 * @return
	 */
	/**
	 * @return
	 */
	public List<Computer> getList() {
		return list;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return new StringBuffer("Page [size=").append(size)
				.append(", pageNumber=").append(pageNumber).append(", list=")
				.append(list).append("orderBy=").append(orderBy)
				.append(", sort=").append(sort).append(", count=")
				.append(count).append(", pageCount=").append(pageCount)
				.append(", query=").append(query).append("]").toString();
	}

	/**
	 * @param list
	 */
	public void setList(List<Computer> list) {
		this.list = list;
	}

	/**
	 * @return
	 */
	public OrderBy getOrderBy() {
		return orderBy;
	}

	/**
	 * @param orderBy
	 */
	public void setOrderBy(OrderBy orderBy) {
		this.orderBy = orderBy;
	}

	/**
	 * @return
	 */
	public Sort getSort() {
		return sort;
	}

	/**
	 * @param sort
	 */
	public void setSort(Sort sort) {
		this.sort = sort;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	/**
	 * Default Constructor for a page;
	 */
	public Page() {
		size = 10;
		pageNumber = 0;
		orderBy = OrderBy.ID;
		list = new ArrayList<Computer>();
		sort = Sort.ASC;
		count = 0;
	}

	public Page(int size, int pageNumber, List<Computer> list, OrderBy orderBy,
			Sort sort, int count, int pageCount) {
		super();
		this.size = size;
		this.pageNumber = pageNumber;
		this.list = list;
		this.orderBy = orderBy;
		this.sort = sort;
		this.pageCount = pageCount;
	}

	public static class Builder {
		private int size;
		private int pageNumber;
		private List<Computer> list;
		private OrderBy orderBy;
		private Sort sort;
		private int count;
		private int pageCount;

		public Builder size(int size) {
			this.size = size;
			return this;
		}

		public Builder pageNumber(int pageNumber) {
			this.pageNumber = pageNumber;
			return this;
		}

		public Builder pageNumber(List<Computer> list) {
			this.list = list;
			return this;
		}

		public Builder orderBy(OrderBy orderBy) {
			this.orderBy = orderBy;
			return this;
		}

		public Builder sort(Sort sort) {
			this.sort = sort;
			return this;
		}

		public Builder count(int count) {
			this.count = count;
			return this;
		}

		public Builder pageCount(int count) {
			this.pageCount = count;
			return this;
		}

		public Page build() {
			return new Page(size, pageNumber, list, orderBy, sort, count,
					pageCount);
		}
	}
}
