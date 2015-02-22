package com.excilys.computerdatabase.service.impl;

import java.util.List;

import org.apache.commons.validator.GenericValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

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
@Service("computerService")
public class ComputerDBServiceImpl implements ComputerDBService {
	private static final Logger LOGGER = LoggerFactory.getLogger(UserDBServiceImpl.class);
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
		LOGGER.info("retrieving page {} with query : {}", pageable, search);
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
		LOGGER.info("retrieving computer with id : {}", id);
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
		LOGGER.info("persisted computer: {}", computer);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.excilys.computerdatabase.service.ComputerDBServiceInterface#
	 * removeByCompany(long)
	 */
	public void removeByCompany(long id) {
		LOGGER.info("Removing computers by company id {}", id);
		computerRepository.deleteByCompany_Id(id);
		LOGGER.info("Finished removing computers by company id : {}", id);
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
			LOGGER.info("Removed computer at id : {}", id);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.excilys.computerdatabase.service.ComputerDBService#delete(long)
	 */
	public void delete(long id) {
		computerRepository.delete(id);
		LOGGER.info("Removed computer at id : {}", id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.excilys.computerdatabase.service.ComputerDBService#exists(long)
	 */
	@Override
	public boolean exists(long id) {
		LOGGER.info("Checked existence of computer at id : {}", id);
		return computerRepository.exists(id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.excilys.computerdatabase.service.ComputerDBService#findAll()
	 */
	public List<Computer> findAll() {
		LOGGER.info("Queried database for all computers");
		return (List<Computer>) computerRepository.findAll();
	}
}
