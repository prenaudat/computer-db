package com.excilys.computerdatabase.mapper.dto.impl;

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
		return new ComputerDTO.Builder().withName(c.getName())
				.withId(c.getId()).withIntroduced(c.getIntroduced())
				.withDiscontinued(c.getDiscontinued())
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
		return new Computer.Builder().id(x.getId()).name(x.getName())
				.introduced(x.getIntroduced())
				.discontinued(x.getDiscontinued())
				.company(new Company.Builder().
						id(x.getCompanyId()).
						build())
				.build();
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
		List<Computer> computerList = new ArrayList<Computer>();
		x.forEach(c -> {
			computerList.add(mapFromDTO(c));
		});
		return computerList;
	}
}
