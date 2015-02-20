package com.excilys.computerdatabase.persistence;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.excilys.computerdatabase.core.model.Company;

/**
 * Company CRUD Repository
 * @author excilys
 *
 */
@Repository
public interface CompanyRepository extends JpaRepository<Company, Long>{
	
}
