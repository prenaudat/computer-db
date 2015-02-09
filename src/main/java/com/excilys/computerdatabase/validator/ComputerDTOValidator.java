package com.excilys.computerdatabase.validator;

import org.apache.commons.validator.GenericValidator;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.excilys.computerdatabase.dto.ComputerDTO;

public class ComputerDTOValidator implements Validator {
	/**
	 * This Validator validates *just* ComputerDTO instances
	 */
	@Override
	public boolean supports(Class<?> clazz) {
		return ComputerDTO.class.equals(clazz);
	}

	@Override
	public void validate(Object obj, Errors e) {
		ComputerDTO dto = (ComputerDTO) obj;
		System.out.println(dto);
		if (dto.getName() != null
				&& (dto.getName().length() == 0 || dto.getName().length() > 255)) {
			e.rejectValue("name", "error.name");
		}
		if (dto.getIntroduced() != null && !GenericValidator.isDate(dto.getIntroduced(),"yyyy-MM-dd" ,false)) {
			e.rejectValue("introduced",
					"error.introduced");
		}
		if (dto.getDiscontinued() != null && !GenericValidator.isDate(dto.getDiscontinued(),"yyyy-MM-dd" ,false)) {
			e.rejectValue("discontinued",
					"error.discontinued");
		}
	}
}
