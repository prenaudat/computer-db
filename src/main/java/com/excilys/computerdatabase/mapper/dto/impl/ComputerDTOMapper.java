package com.excilys.computerdatabase.mapper.dto.impl;

import java.util.ArrayList;
import java.util.List;

import com.excilys.computerdatabase.dto.ComputerDTO;
import com.excilys.computerdatabase.mapper.dto.DTOMapper;
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
		return new ComputerDTO.Builder().withName(c.getName())
				.withId(c.getId()).withIntroduced(c.getIntroduced())
				.withDiscontinued(c.getDiscontinued())
				.withCompany(c.getCompany()).build();
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
		return null;
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
