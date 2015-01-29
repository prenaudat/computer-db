package com.excilys.computerdatabase.mapper.dto.impl;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.excilys.computerdatabase.dto.ComputerDTO;
import com.excilys.computerdatabase.mapper.dto.DTOMapper;
import com.excilys.computerdatabase.model.Company;
import com.excilys.computerdatabase.model.Computer;

/**
 * Map between Computer and ComputerDTO
 * 
 * @author excilys
 *
 */
public class ComputerDTOMapper implements DTOMapper<ComputerDTO, Computer> {

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.excilys.computerdatabase.mapper.dto.DTOMapper#mapToDTO(java.lang.
	 * Object)
	 */
	@Override
	public ComputerDTO mapToDTO(Computer c) {
		ComputerDTO ret =new ComputerDTO.Builder().withName(c.getName())
				.withId(c.getId()).withIntroduced(c.getIntroduced())
				.withDiscontinued(c.getDiscontinued())
				.withCompanyId(c.getCompany().getId()).withCompanyName(c.getCompany().getName()).build();
		return ret;
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
		return new Computer.Builder().id(x.getId())
				.name(x.getName())
				.introduced(LocalDate.parse(x.getIntroduced()))
				.discontinued(LocalDate.parse(x.getDiscontinued()))
				.company(
						new Company.CompanyBuilder().id(x.getCompanyId())
								.build()).build();
	}

	@Override
	public List<ComputerDTO> mapToDTO(List<Computer> computer) {
		List<ComputerDTO> dtoList = new ArrayList<ComputerDTO>();
		computer.forEach(c -> {
			dtoList.add(mapToDTO(c));
		});
		return dtoList;
	}

	@Override
	public List<Computer> mapFromDTO(List<ComputerDTO> x) {
		return null;
	}

}
