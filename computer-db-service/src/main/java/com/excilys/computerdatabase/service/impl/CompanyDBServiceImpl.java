package com.excilys.computerdatabase.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
	private static final Logger LOGGER = LoggerFactory.getLogger(UserDBServiceImpl.class);
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
		LOGGER.info("Queried companyRepository for id "+id);
		return companyRepository.findOne(id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.excilys.computerdatabase.service.CompanyDBService#findAll()
	 */
	public List<Company> findAll() {
		LOGGER.info("Queried companyRepository for all companies");
		return companyRepository.findAll();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.excilys.computerdatabase.service.CompanyDBService#exists(long)
	 */
	public boolean exists(long id) {
		LOGGER.info("Checked companyRepository for id "+id);
		return companyRepository.exists(id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.excilys.computerdatabase.service.CompanyDBService#remove(long)
	 */
	@Transactional
	public void delete(long id) {
		LOGGER.info("Beginning transaction for deleting a company and its computers. Company id : "+id);
		computerRepository.deleteByCompany_Id(id);
		companyRepository.delete(id);
		LOGGER.info("Company successfully deleted at id : "+id);
	}

}
