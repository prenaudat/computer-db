package com.excilys.computerdatabase.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.excilys.computerdatabase.core.model.Company;
import com.excilys.computerdatabase.persistence.CompanyRepository;
import com.excilys.computerdatabase.persistence.ComputerRepository;
import com.excilys.computerdatabase.service.CompanyDBService;

/**
 * CompanyDBService Implementation
 * 
 * @author excilys
 *
 */
@Service("companyService")
public class CompanyDBServiceImpl implements CompanyDBService {
	// autowired components
	@Autowired
	CompanyRepository companyRepository;
	@Autowired
	ComputerRepository computerRepository;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.excilys.computerdatabase.service.CompanyDBService#get(long)
	 */
	public Company findOne(long id) {
		return companyRepository.findOne(id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.excilys.computerdatabase.service.CompanyDBService#findAll()
	 */
	public List<Company> findAll() {
		return companyRepository.findAll();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.excilys.computerdatabase.service.CompanyDBService#exists(long)
	 */
	public boolean exists(long id) {
		return companyRepository.exists(id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.excilys.computerdatabase.service.CompanyDBService#remove(long)
	 */
	@Transactional
	public void delete(long id) {
		computerRepository.deleteByCompany_Id(id);
		companyRepository.delete(id);
	}

}
