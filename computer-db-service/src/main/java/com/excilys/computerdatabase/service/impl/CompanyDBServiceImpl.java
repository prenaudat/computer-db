package com.excilys.computerdatabase.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.excilys.computerdatabase.core.model.Company;
import com.excilys.computerdatabase.persistence.CompanyRepository;
import com.excilys.computerdatabase.service.CompanyDBService;

/**
 * @author excilys
 *
 */
/**
 * @author excilys
 *
 */
@Service("companyService")
public class CompanyDBServiceImpl implements CompanyDBService {
	@Autowired
	CompanyRepository companyRepository;

	/* (non-Javadoc)
	 * @see com.excilys.computerdatabase.service.CompanyDBService#get(long)
	 */
	public Company get(long id) {
		return companyRepository.findOne(id);
	}


	/**
	 * @return List<Company> of all companies
	 */
	public List<Company> getAll() {
		return companyRepository.findAll();
	}

	/* (non-Javadoc)
	 * @see com.excilys.computerdatabase.service.CompanyDBService#remove(long)
	 */
	public void delete(long id) {
		companyRepository.delete(id);
	}

	/* (non-Javadoc)
	 * @see com.excilys.computerdatabase.service.CompanyDBService#getPage(org.springframework.data.domain.PageRequest)
	 */
	public Page<Company> getPage(PageRequest pageRequest) {
		return companyRepository.findAll(pageRequest);
	}

}
