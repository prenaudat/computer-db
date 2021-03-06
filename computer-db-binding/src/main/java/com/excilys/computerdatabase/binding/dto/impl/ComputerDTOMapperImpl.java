package com.excilys.computerdatabase.binding.dto.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.i18n.LocaleContextHolder;

import com.excilys.computerdatabase.binding.dto.DTOMapper;
import com.excilys.computerdatabase.binding.dto.model.ComputerDTO;
import com.excilys.computerdatabase.core.model.Company;
import com.excilys.computerdatabase.core.model.Computer;

/**
 * Map between Computer and ComputerDTO
 * 
 * @author excilys
 *
 */
public class ComputerDTOMapperImpl implements DTOMapper<ComputerDTO, Computer> {

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.excilys.computerdatabase.mapper.dto.DTOMapper#mapToDTO(java.lang.
	 * Object)
	 */
	@Override
	public ComputerDTO mapToDTO(Computer c) {
		return new ComputerDTO.Builder()
				.withName(c.getName())
				.withId(c.getId())
				.withIntroduced(c.getIntroduced(),
						LocaleContextHolder.getLocale().toLanguageTag())
				.withDiscontinued(c.getDiscontinued(),
						LocaleContextHolder.getLocale().toLanguageTag())
				.withCompanyId(c.getCompanyId())
				.withCompanyName(c.getCompanyName()).build();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.excilys.computerdatabase.mapper.dto.DTOMapper#mapFromDTO(java.lang
	 * .Object)
	 */
	@Override
	public Computer mapFromDTO(ComputerDTO x) {
		return new Computer.Builder()
				.id(x.getId())
				.name(x.getName())
				.introduced(x.getIntroduced(),
						LocaleContextHolder.getLocale().toLanguageTag())
				.discontinued(x.getDiscontinued(),
						LocaleContextHolder.getLocale().toLanguageTag())
				.company(new Company.Builder().id(x.getCompanyId()).build())
				.build();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.excilys.computerdatabase.binding.dto.DTOMapper#mapToDTO(java.util
	 * .List)
	 */
	@Override
	public List<ComputerDTO> mapToDTO(List<Computer> computer) {
		List<ComputerDTO> dtoList = new ArrayList<ComputerDTO>();
		computer.forEach(c -> {
			dtoList.add(mapToDTO(c));
		});
		return dtoList;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.excilys.computerdatabase.binding.dto.DTOMapper#mapFromDTO(java.util
	 * .List)
	 */
	@Override
	public List<Computer> mapFromDTO(List<ComputerDTO> x) {
		List<Computer> computerList = new ArrayList<Computer>();
		x.forEach(c -> {
			computerList.add(mapFromDTO(c));
		});
		return computerList;
	}
}
