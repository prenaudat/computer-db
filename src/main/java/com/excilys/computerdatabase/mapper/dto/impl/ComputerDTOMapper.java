package com.excilys.computerdatabase.mapper.dto.impl;

import com.excilys.computerdatabase.mapper.dto.DTOMapper;
import com.excilys.computerdatabase.model.Computer;
import com.excilys.computerdatabase.model.ComputerDTO;

/**
 * Map between Computer and ComputerDTO
 * @author excilys
 *
 */
public class ComputerDTOMapper implements DTOMapper<ComputerDTO, Computer> {

	/* (non-Javadoc)
	 * @see com.excilys.computerdatabase.mapper.dto.DTOMapper#mapToDTO(java.lang.Object)
	 */
	@Override
	public ComputerDTO mapToDTO(Computer c) {
		return new ComputerDTO(c.getId(), c.getName(), c.getIntroduced()
				.toString(), c.getDiscontinued().toString(), c.getCompany()
				.getName(), c.getCompany().getId());
	}

	/* (non-Javadoc)
	 * @see com.excilys.computerdatabase.mapper.dto.DTOMapper#mapFromDTO(java.lang.Object)
	 */
	@Override
	public Computer mapFromDTO(ComputerDTO x) {
		// TODO Auto-generated method stub
		return null;
	}

}
