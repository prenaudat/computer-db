package com.excilys.computerdatabase.binding.dto.impl;

import java.util.ArrayList;
import java.util.List;

import com.excilys.computerdatabase.binding.dto.model.CompanyDTO;
import com.excilys.computerdatabase.binding.dto.DTOMapper;
import com.excilys.computerdatabase.core.model.Company;

/**
 * Map between Company and CompanyDTO
 * 
 * @author excilys
 *
 */
public class CompanyDTOMapperImpl implements DTOMapper<CompanyDTO, Company> {

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.excilys.computerdatabase.mapper.dto.DTOMapper#mapToDTO(java.lang.
	 * Object)
	 */
	@Override
	public CompanyDTO mapToDTO(Company c) {
		return new CompanyDTO(c.getId(), c.getName());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.excilys.computerdatabase.mapper.dto.DTOMapper#mapFromDTO(java.lang
	 * .Object)
	 */
	@Override
	public Company mapFromDTO(CompanyDTO c) {
		return new Company(c.getId(), c.getName());

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.excilys.computerdatabase.mapper.dto.DTOMapper#mapToDTO(java.util.
	 * List)
	 */
	@Override
	public List<CompanyDTO> mapToDTO(List<Company> y) {
		List<CompanyDTO> dtoList = new ArrayList<CompanyDTO>();
		y.forEach(c -> {
			dtoList.add(mapToDTO(c));
		});
		return dtoList;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.excilys.computerdatabase.mapper.dto.DTOMapper#mapFromDTO(java.util
	 * .List)
	 */
	@Override
	public List<Company> mapFromDTO(List<CompanyDTO> x) {
		List<Company> companyList = new ArrayList<Company>();
		x.forEach(c -> {
			companyList.add(mapFromDTO(c));
		});
		return companyList;
	}
}
