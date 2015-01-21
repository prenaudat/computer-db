package com.excilys.computerdatabase.mapper.dto;

/**
 * Maps class X to class Y
 * @author excilys
 * @param <X> DTO class
 * @param <Y> Class to be mapped to DTO
 * 
 */
public interface DTOMapper<X, Y> {
	
	/**
	 * Creates a DTO from object y
	 * @param y the Object to be mapped to a DTO object
	 * @return new DTO object
	 */
	X mapToDTO(Y y);

	/**
	 * Maps an object from the DTO
	 * @param x the DTO to extract data from object x
	 * @return X object
	 */
	Y mapFromDTO(X x);
}
