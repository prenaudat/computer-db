package com.excilys.computerdatabase.persistence;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.excilys.computerdatabase.core.model.Computer;

/**
 * Company Repository bean
 * 
 * @author excilys
 *
 */
@Repository
public interface ComputerRepository extends
		PagingAndSortingRepository<Computer, Long> {
	public static final String SELECT_QUERY = "select c from Computer c left join c.company cy where c.name like :search or cy.name like :search";
	public static final String COUNT_QUERY = "select count(c.id) from Computer c left join c.company cy where c.name like :search or cy.name like :search";

	/**
	 * findAll by query
	 * 
	 * @param search
	 *            String
	 * @param pageable
	 *            definition of page to be returned
	 * @return Computer Page
	 */
	@Query(value = SELECT_QUERY, countQuery = COUNT_QUERY)
	public Page<Computer> findAll(@Param("search") String search,
			Pageable pageable);

	/**
	 * Delete by company id
	 * 
	 * @param id
	 *            id of company
	 */
	public void deleteByCompany_Id(Long id);
}