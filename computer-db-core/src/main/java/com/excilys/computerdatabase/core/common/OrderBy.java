package com.excilys.computerdatabase.core.common;

import org.springframework.data.domain.Sort;

/**
 * Enum class for Ordering pages
 * 
 * @author excilys
 *
 */
public enum OrderBy {
	ID_ASC(new Sort(Sort.Direction.ASC, "id"), "id", "ASC"), 
	ID_DESC(new Sort(Sort.Direction.DESC, "id"), "id", "DESC"), 
	NAME_ASC(new Sort(Sort.Direction.ASC, "name"), "name", "ASC"), 
	NAME_DESC(new Sort(Sort.Direction.DESC, "name"), "name", "DESC"), 
	INTRODUCED_ASC(new Sort(Sort.Direction.ASC, "introduced"), "introduced", "ASC"), 
	INTRODUCED_DESC(new Sort(Sort.Direction.DESC, "introduced"), "introduced", "DESC"), 
	DISCONTINUED_ASC(new Sort(Sort.Direction.ASC, "discontinued"), "discontinued", "ASC"), 
	DISCONTINUED_DESC(new Sort(Sort.Direction.DESC, "discontinued"), "discontinued","DESC"), 
	COMPANY_ASC(new Sort(Sort.Direction.ASC, "company.name"),"company", "ASC"), 
	COMPANY_DESC(new Sort(Sort.Direction.DESC,"company.name"), "company", "DESC");
	private Sort value;
	private String colName = "id";
	private String direction = "ASC";

	/**
	 * Private constructor
	 * 
	 * @param value
	 *            Sort
	 * @param colname
	 *            colname
	 * @param direction
	 *            direction
	 */
	private OrderBy(Sort value, String colname, String direction) {
		this.setValue(value);
	}

	/**
	 * Get value
	 * 
	 * @return Sort value
	 */
	public Sort getValue() {
		return value;
	}

	/**
	 * Set value
	 * 
	 * @param value
	 *            Spring sort
	 */
	public void setValue(Sort value) {
		this.value = value;
	}

	/**
	 * Get column name
	 * 
	 * @return column name
	 */
	public String getColName() {
		return colName;
	}

	/**
	 * Set column name
	 * 
	 * @param colName
	 *            column name
	 */
	public void setColName(String colName) {
		this.colName = colName;
	}

	/**
	 * Get direction
	 * 
	 * @return String direction
	 */
	public String getDirection() {
		return direction;
	}

	/**
	 * Set direction
	 * 
	 * @param direction
	 *            String ASC or DESC
	 */
	public void setDirection(String direction) {
		this.direction = direction;
	}

	/**
	 * get Orderby from column name and direction
	 * 
	 * @param colName
	 *            Column name
	 * @param dir
	 *            Direction
	 * @return Orderby merge of colname and direction
	 */
	public static OrderBy get(String colName, String dir) {
		if (colName == null) {
			return null;
		}
		if (dir == null) {
			dir = "asc";
		}
		for (OrderBy col : OrderBy.values()) {
			if (col.getColName().equals(colName)
					&& col.getDirection().equals(dir)) {
				System.out.println("winner " + col);
				return col;
			}
		}
		return null;
	}

	/**
	 * Get direction for a certain column
	 * 
	 * @param col
	 *            The column whos direction you want
	 * @return The direction for that column
	 */
	public String getDirForCol(String col) {
		if (this.getColName().equals(col) && this.getDirection().equals("asc")) {
			return "DESC";
		}
		return "ASC";
	}

	/**
	 * Static method for gettubg OrderBy from Sort(Spring)
	 * 
	 * @param sort
	 *            Spring sort
	 * @return Orderby equivalent
	 */
	public static OrderBy getOrderByFromSort(Sort sort) {
		if (sort == null) {
			return null;
		}
		for (OrderBy col : OrderBy.values()) {
			if (col.getValue().equals(sort)) {
				return col;
			}
		}
		return null;
	}
}
