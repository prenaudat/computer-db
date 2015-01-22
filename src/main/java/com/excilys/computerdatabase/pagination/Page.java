package com.excilys.computerdatabase.pagination;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;

import com.excilys.computerdatabase.model.Computer;

/**
 * Page Class for sorting, ordering, sizing and searching 
 * @author excilys
 */
/**
 * @author excilys
 *
 */
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
	private String target;

	public String getTarget() {
		return target;
	}

	public void setTarget(String target) {
		this.target = target;
	}

	/**
	 * @returns Query for page
	 */
	public String getQuery() {
		return query;
	}

	/**
	 * @param query sets Query for page
	 */
	public void setQuery(String query) {
		this.query = query;
	}

	/**
	 * @return return total number of pages
	 */
	public int getPageCount() {
		return pageCount;
	}

	/**
	 * @param pageCount sets total number of pages
	 */
	public void setPageCount(int pageCount) {
		this.pageCount = pageCount;
	}

	/**
	 * @return size of page
	 */
	public int getSize() {
		return size;
	}

	/**
	 * @param size set size of page
	 */
	public void setSize(int size) {
		this.size = size;
	}

	/**
	 * @return current page number
	 */
	public int getPageNumber() {
		return pageNumber;
	}

	/**
	 * @param pageNumber set current page number
	 */
	public void setPageNumber(int pageNumber) {
		this.pageNumber = pageNumber;
	}

	/**
	 * @return List of computers corresponding to criteria
	 */
	public List<Computer> getList() {
		return list;
	}


	@Override
	public String toString() {
		StringBuilder builder2 = new StringBuilder();
		builder2.append("Page [size=").append(size).append(", pageNumber=")
				.append(pageNumber).append(", list=").append(list)
				.append(", orderBy=").append(orderBy).append(", sort=")
				.append(sort).append(", count=").append(count)
				.append(", pageCount=").append(pageCount).append(", query=")
				.append(query).append(", target=").append(target).append("]");
		return builder2.toString();
	}

	/**
	 * @param list set Computer List of page
	 */
	public void setList(List<Computer> list) {
		this.list = list;
	}

	/**
	 * @return get OrderBy criteria
	 */
	public OrderBy getOrderBy() {
		return orderBy;
	}

	/**
	 * @param orderBy set OrderBy criteria
	 */
	public void setOrderBy(OrderBy orderBy) {
		this.orderBy = orderBy;
	}

	/**
	 * @return Sort criteria
	 */
	public Sort getSort() {
		return sort;
	}

	/**
	 * @param sort set Sort criteria
	 */
	public void setSort(Sort sort) {
		this.sort = sort;
	}

	/**
	 * @return count of computers
	 */
	public int getCount() {
		return count;
	}

	/**
	 * @param count set count
	 */
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
		target = "home";
	}

	/***
	 * Full constructor for Page using all terms
	 * @param size
	 * @param pageNumber
	 * @param list
	 * @param orderBy
	 * @param sort
	 * @param count
	 * @param pageCount
	 * @param String query
	 */
	public Page(int size, int pageNumber, List<Computer> list, OrderBy orderBy,
			Sort sort, int count, int pageCount, String query, String target) {
		super();
		this.size = size;
		this.pageNumber = pageNumber;
		this.list = list;
		this.orderBy = orderBy;
		this.sort = sort;
		this.pageCount = pageCount;
		this.query = query;
		this.target = target;
	}

	/**
	 * Nested Builder Class 
	 * @author excilys
	 *
	 */
	public static class Builder {
		private int size;
		private int pageNumber;
		private List<Computer> list;
		private OrderBy orderBy;
		private Sort sort;
		private int count;
		private int pageCount;
		private String query;
		private String target;

		public Builder size(int size) {
			this.size = size;
			return this;
		}

		public Builder pageNumber(int pageNumber) {
			this.pageNumber = pageNumber;
			return this;
		}

		public Builder list(List<Computer> list) {
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
		public Builder query(String query) {
			this.query = query;
			return this;
		}

		public Builder target(String target) {
			this.target = target;
			return this;
		}
		
		public Page build() {
			return new Page(size, pageNumber, list, orderBy, sort, count,
					pageCount, query, target);
		}
	}
}
