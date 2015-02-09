package com.excilys.computerdatabase.persistence;

import org.springframework.data.repository.CrudRepository;

import com.excilys.computerdatabase.model.Company;

public interface CompanyRepository extends CrudRepository<Company, Long>  {
	


}
