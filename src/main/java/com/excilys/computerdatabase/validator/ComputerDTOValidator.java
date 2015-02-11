package com.excilys.computerdatabase.validator;

import org.apache.commons.validator.GenericValidator;
import org.springframework.context.i18n.LocaleContextHolder;
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
		if (dto.getName() != null
				&& (dto.getName().length() == 0 || dto.getName().length() > 255)) {
			e.rejectValue("name", "error.name");
		}

		if (dto.getIntroduced() != ""
				&& LocaleContextHolder.getLocale().toLanguageTag() == "en"
				&& !GenericValidator.isDate(dto.getIntroduced(), "yyyy-MM-dd",
						false)) {
			e.rejectValue("introduced", "error.introduced");
		}
		if (dto.getIntroduced() != ""
				&& LocaleContextHolder.getLocale().toLanguageTag() == "fr"
				&& !GenericValidator.isDate(dto.getIntroduced(), "dd-mm-yyyy",
						false)) {
			e.rejectValue("introduced", "error.introduced");
		}
		if (dto.getDiscontinued() != ""
				&& LocaleContextHolder.getLocale().toLanguageTag() == "en"
				&& !GenericValidator.isDate(dto.getDiscontinued(),
						"yyyy-MM-dd", false)) {
			e.rejectValue("introduced", "error.discontinued");
		}
		if (dto.getDiscontinued() != ""
				&& LocaleContextHolder.getLocale().toLanguageTag() == "fr"
				&& !GenericValidator.isDate(dto.getDiscontinued(),
						"dd-mm-yyyy", false)) {
			e.rejectValue("introduced", "error.discontinued");
		}
	}
}
