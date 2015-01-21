package com.excilys.computerdatabase.mapper;

import java.sql.ResultSet;
import java.util.List;

public interface RowMapper<T>{
	
	/**
	 * Map one element
	 * @param rs
	 * @return
	 */
	T mapRow(ResultSet rs);
	
	/**
	 * Map a List of elements
	 * @param rs
	 * @return
	 */
	List<T> mapRowList(ResultSet rs);

}
