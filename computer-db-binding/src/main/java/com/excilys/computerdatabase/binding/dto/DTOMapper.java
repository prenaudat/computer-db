package com.excilys.computerdatabase.binding.dto;

import java.util.List;

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
	
	/**
	 * Map a list of Objects into DTOS
	 * @param y list of objects
	 * @return list of DTOs
	 */
	List<X> mapToDTO(List<Y> y);

	/**
	 * Map list of DTOs to Objects
	 * @param x List of DTOs
	 * @return List of Objects
	 */
	List<Y> mapFromDTO(List<X> x);


}
