package com.excilys.computerdatabase.service.impl;

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
 * @author paulr_000
 *
 */
@Service("computerService")
public class ComputerDBServiceImpl implements ComputerDBService {
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

	/**
	 * Removes computer at specified id.
	 * 
	 * @param i
	 *            id to be deleted.
	 */
	public void delete(String id) {
		if (GenericValidator.isLong(id)) {
			computerRepository.delete(Long.parseLong(id));
		}
	}

	@Override
	public boolean exists(long id) {
		return computerRepository.exists(id);
	}
}
