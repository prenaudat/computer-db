package com.excilys.computerdatabase.service.impl;

import java.util.List;

import org.apache.commons.validator.GenericValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.excilys.computerdatabase.core.model.Computer;
import com.excilys.computerdatabase.persistence.CompanyRepository;
import com.excilys.computerdatabase.persistence.ComputerRepository;
import com.excilys.computerdatabase.service.ComputerDBService;

/**
 * ComputerDBService Imlpementation with the ComputerRepository
 * 
 * @author excilys
 *
 */
/**
 * @author excilys
 *
 */
@Service("computerService")
public class ComputerDBServiceImpl implements ComputerDBService {
	// Autowired components
	@Autowired
	ComputerRepository computerRepository;
	@Autowired
	CompanyRepository companyRepository;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.excilys.computerdatabase.service.ComputerDBService#getPage(org.
	 * springframework.data.domain.PageRequest)
	 */
	public Page<Computer> retrievePage(Pageable pageable, String search) {
		Page<Computer> page = null;
		if (search == null) {
			page = computerRepository.findAll(pageable);
		} else {
			page = computerRepository.findAll(
					new StringBuilder("%").append(search.toLowerCase())
							.append("%").toString(), pageable);
		}
		return page;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.excilys.computerdatabase.service.ComputerDBService#get(long)
	 */
	public Computer findOne(long id) {
		return computerRepository.findOne(id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.excilys.computerdatabase.service.ComputerDBService#save(com.excilys
	 * .computerdatabase.core.model.Computer)
	 */
	public void save(Computer computer) {
		computerRepository.save(computer);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.excilys.computerdatabase.service.ComputerDBServiceInterface#
	 * removeByCompany(long)
	 */
	@Transactional
	public void removeByCompany(long id) {
		computerRepository.delete(id);
		companyRepository.delete(id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.excilys.computerdatabase.service.ComputerDBService#delete(java.lang
	 * .String)
	 */
	public void delete(String id) {
		if (GenericValidator.isLong(id)) {
			computerRepository.delete(Long.parseLong(id));
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.excilys.computerdatabase.service.ComputerDBService#delete(long)
	 */
	public void delete(long id) {
		computerRepository.delete(id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.excilys.computerdatabase.service.ComputerDBService#exists(long)
	 */
	@Override
	public boolean exists(long id) {
		return computerRepository.exists(id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.excilys.computerdatabase.service.ComputerDBService#findAll()
	 */
	public List<Computer> findAll() {
		return (List<Computer>) computerRepository.findAll();
	}
}
