package com.excilys.computerdatabase.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.excilys.computerdatabase.dto.ComputerDTO;

public class ComputerDTOValidator implements Validator {

	/**
	 * This Validator validates *just* ComputerDTO instances
	 */
	@Override
	public boolean supports(Class<?> classz) {
		return ComputerDTO.class.equals(classz);
	}

	@Override
	public void validate(Object obj, Errors e) {
		ComputerDTO dto = (ComputerDTO) obj;
		if (dto.getName() != null
				&& (dto.getName().length() == 0 || dto.getName().length() > 255)) {
			e.rejectValue("name", "Name is not between 1 and 255 characters");
		}
		if (dto.getIntroduced() != null && dto.getIntroduced().length() != 10 && dto.getIntroduced().length() != 0) {
			System.out.println(dto.getIntroduced().length());
			e.rejectValue("introduced",
					"Introduction date must be in correct format yyyy-mm-dd");
		}
		if (dto.getDiscontinued() != null && dto.getDiscontinued().length() != 10 && dto.getDiscontinued().length() != 0) {
			e.rejectValue("discontinued",
					"Discontinuation date must be in correct format yyyy-mm-dd");
		}
	}
}
