package com.excilys.computerdatabase.mapper.dto.impl;

import java.util.List;

import com.excilys.computerdatabase.dto.CompanyDTO;
import com.excilys.computerdatabase.dto.ComputerDTO;
import com.excilys.computerdatabase.mapper.dto.DTOMapper;
import com.excilys.computerdatabase.model.Company;
import com.excilys.computerdatabase.model.Computer;

/**
 * Map between Company and CompanyDTO
 * @author excilys
 *
 */
public class CompanyDTOMapper implements DTOMapper<CompanyDTO, Company> {

	/* (non-Javadoc)
	 * @see com.excilys.computerdatabase.mapper.dto.DTOMapper#mapToDTO(java.lang.Object)
	 */
	@Override
	public CompanyDTO mapToDTO(Company c) {
		return new CompanyDTO(c.getId(),c.getName());
	}

	/* (non-Javadoc)
	 * @see com.excilys.computerdatabase.mapper.dto.DTOMapper#mapFromDTO(java.lang.Object)
	 */
	@Override
	public Company mapFromDTO(CompanyDTO c) {
		return new Company(c.getId(),c.getName());

	}

	@Override
	public List<CompanyDTO> mapToDTO(List<Company> y) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Company> mapFromDTO(List<CompanyDTO> x) {
		// TODO Auto-generated method stub
		return null;
	}


}
