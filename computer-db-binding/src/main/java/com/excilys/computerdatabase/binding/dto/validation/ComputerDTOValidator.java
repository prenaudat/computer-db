package com.excilys.computerdatabase.binding.dto.validation;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.excilys.computerdatabase.binding.dto.model.ComputerDTO;

public class ComputerDTOValidator implements Validator {
	/**
	 * This Validator validates *just* ComputerDTO instances
	 */
	private static final Logger LOGGER = LoggerFactory.getLogger(ComputerDTOValidator.class);

	@Override
	public boolean supports(Class<?> clazz) {
		return ComputerDTO.class.equals(clazz);
	}

	@Override
	public void validate(Object obj, Errors e) {
		ComputerDTO dto = (ComputerDTO) obj;
		LOGGER.info("GOT HERE "+dto.toString());
		if (dto.getName() != null
				&& (dto.getName().length() == 0 || dto.getName().length() > 255)) {
			e.rejectValue("name", "error.name");
		}
		LOGGER.info("GOT HERE "+LocaleContextHolder.getLocale().toLanguageTag());

		if (dto.getIntroduced() != "" && LocaleContextHolder.getLocale().toLanguageTag().equals("en")) {
			LOGGER.info("GOT HERE 1");
			try{
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			LOGGER.info(LocalDate.parse(dto.getIntroduced(), formatter).toString());
			} catch(Exception ex) {
				LOGGER.info("can't update introduced in english");
				e.rejectValue("introduced", "error.introduced");
			}
		}if (dto.getIntroduced() != "" && LocaleContextHolder.getLocale().toLanguageTag().equals("fr")) {
			LOGGER.info("GOT HERE V");
			try{
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-mm-yyyy");
				LOGGER.info(LocalDate.parse(dto.getIntroduced(), formatter).toString());
				} catch(Exception ex) {
					ex.printStackTrace();
					e.rejectValue("introduced", "error.introduced");
				}		}
		if (dto.getDiscontinued() != ""	&& LocaleContextHolder.getLocale().toLanguageTag().equals("en")) {
			try{
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
				LOGGER.info(LocalDate.parse(dto.getDiscontinued(), formatter).toString());
			} catch(Exception ex) {
				LOGGER.info("can't update discontinued in english");
				e.rejectValue("discontinued", "error.discontinued");
			}		
		}
		if (dto.getDiscontinued() != "" && LocaleContextHolder.getLocale().toLanguageTag().equals("fr")) {
			LOGGER.info("GOT HERE");
			try{
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-mm-yyyy");
				LOGGER.info(LocalDate.parse(dto.getDiscontinued(), formatter).toString());
			} catch(Exception ex) {
				LOGGER.info("can't update discontinued in french");
				e.rejectValue("discontinued", "error.discontinued");
			}
		}
	}
}
