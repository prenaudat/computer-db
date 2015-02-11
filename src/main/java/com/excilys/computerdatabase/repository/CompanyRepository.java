package com.excilys.computerdatabase.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.excilys.computerdatabase.model.Company;

@Repository
public interface CompanyRepository extends JpaRepository<Company, Long>{
	
}
