package com.excilys.computerdatabase.mapper.row;

import java.sql.ResultSet;
import java.util.List;

/**
 * @author excilys
 *
 * @param <T> Class to convert ResultSet into
 */
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
