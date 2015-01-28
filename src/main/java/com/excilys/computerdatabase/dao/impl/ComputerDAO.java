package com.excilys.computerdatabase.dao.impl;

import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.excilys.computerdatabase.dao.ComputerDAOInterface;
import com.excilys.computerdatabase.mapper.dto.impl.ComputerDTOMapper;
import com.excilys.computerdatabase.mapper.row.impl.ComputerMapper;
import com.excilys.computerdatabase.model.Computer;
import com.excilys.computerdatabase.pagination.Page;

/**
 * @author excilys
 *
 */
@Repository
public class ComputerDAO implements ComputerDAOInterface {
	// Static Queries and Updates to be prepared
	private static final String SINGLE_QUERY_STMT = "SELECT cpt.id, cpt.name, cpt.introduced, cpt.discontinued, cmp.id as company_id, cmp.name as company_name FROM computer cpt LEFT JOIN company cmp ON cpt.company_id=cmp.id WHERE cpt.id=?;";
	private static final String LIST_QUERY_STMT = "SELECT cpt.id, cpt.name, cpt.introduced, cpt.discontinued, cmp.id AS company_id, cmp.name AS company_name FROM computer cpt LEFT JOIN company cmp ON cpt.company_id=cmp.id LIMIT ? , ?;";
	private static final String INSERT_STMT = "INSERT into computer(name, introduced, discontinued, company_id) VALUES (?,?,?,?);";
	private static final String UPDATE_STMT = "UPDATE computer SET name=?, introduced=?, discontinued=?, company_id=? WHERE id=?;";
	private static final String DELETE_STMT = "DELETE FROM computer WHERE id=?;";
	private static final String COUNT_STMT = "SELECT COUNT(cpt.id) FROM computer cpt LEFT JOIN company cmp ON cpt.company_id=cmp.id WHERE cpt.name LIKE ? OR cmp.name LIKE ?";
	private static final String PAGE_QUERY = "SELECT cpt.id, cpt.name, cpt.introduced, cpt.discontinued, cmp.id as company_id, cmp.name as company_name FROM computer cpt LEFT JOIN company cmp ON cpt.company_id=cmp.id WHERE cpt.name LIKE ? OR cmp.name LIKE ? ";
	private static final String DELETE_COMPANY_COMPUTERS = "DELETE FROM computer WHERE company_id=?";
	private ComputerDTOMapper computerDTOMapper = new ComputerDTOMapper();
	@Autowired
	private DataSource dataSource;
	private static int pageSize = 10;
	// Logger for this class
	private Logger LOGGER = LoggerFactory.getLogger(ComputerDAO.class);
	private JdbcTemplate template;

	/**
	 * Set datasource on innitialization
	 */
	@Autowired
	public void setDataSource(DataSource dataSource) {
		this.template = new JdbcTemplate(dataSource);
	}

	/**
	 * Retrieve a single computer identified by its unique ID
	 * 
	 * @param id
	 *            ID of computer to get
	 * @return Computer corresponding to ID
	 */
	public Computer get(final long id) {
		System.out.println(id);
		List<Computer> list = template.query(SINGLE_QUERY_STMT,
				new Object[] { id }, new ComputerMapper());
		System.out.println(list);
		if (list.size() > 0) {
			return list.get(0);
		} else {
			return null;
		}
	}

	/**
	 * Update an existing computer
	 * 
	 * @param id
	 *            Id of computer to update
	 * @param name
	 *            new name for computer
	 * @param introduced
	 *            new introduction date for computer
	 * @param discontinued
	 *            new discontinuation date for computer
	 * @param companyId
	 *            new company ID for computer
	 */
	public void update(final Computer computer) {
		System.out.println(computer);
		template.update(
				UPDATE_STMT,
				new Object[] { computer.getName(),
						computer.getIntroducedAsTimestamp(),
						computer.getDiscontinuedAsTimestamp(),
						null , computer.getId() });
	}

	/**
	 * Insert a new computer into the database
	 * 
	 * @param name
	 *            Name for new computer
	 * @param introduced
	 *            Introduction date for new Computer
	 * @param discontinued
	 *            Discontinuation date for new computer
	 * @param companyId
	 *            Company ID for new COmputer
	 */
	public void save(final Computer computer) {

		template.update(
				INSERT_STMT,
				new Object[] { computer.getName(),
						computer.getIntroducedAsTimestamp(),
						computer.getDiscontinuedAsTimestamp(),
						computer.getCompanyId()});
	}

	/**
	 * Retrieve a list of computers corresponding to the selection
	 * 
	 * @param pageNumber
	 *            indeConnection is null. x of Page to return
	 * @return Page of computers
	 * @throws SQLException
	 */
	public Page getPage(int pageNumber) {
		List<Computer> list = template.query(LIST_QUERY_STMT, new Object[] {
				pageNumber * pageSize, pageSize }, new ComputerMapper());
		Page page = new Page();
		page.setList(computerDTOMapper.mapToDTO(list));
		page.setPageNumber(pageNumber);
		page.setCount(getCount(page.getQuery()));
		page.setPageCount((int) Math.ceil(page.getCount() / pageSize));
		return page;
	}

	/**
	 * Delete a computer from database
	 * 
	 * @param id
	 *            ID of computer to delete
	 * @throws SQLException
	 */
	public void remove(final long id) {
		template.update(DELETE_STMT, new Object[] { id });
	}

	/**
	 * Removes a company and all its associated computers
	 * 
	 * @param id
	 *            ID of company to delete, along with its computers
	 */
	public void removeByCompany(long id) {
		template.update(DELETE_COMPANY_COMPUTERS, new Object[] { id });
	}

	/**
	 * Get count of Computersprivate static final Logger LOGGER = new Logger;
	 * 
	 * 
	 * @return number of computers
	 */
	public int getCount(String query) {
		return template.queryForObject(COUNT_STMT, new Object[] {
				"%" + query + "%", "%" + query + "%" }, Integer.class);
	}

	public Page getPage(Page page) {
		String query = PAGE_QUERY + "ORDER BY " + page.getOrderBy().toString()
				+ " " + page.getSort().toString() + " LIMIT ? , ?;";
		List<Computer> computerList = template
				.query(query,
						new Object[] { "%" + page.getQuery() + "%",
								"%" + page.getQuery() + "%",
								page.getPageNumber() * page.getSize(),
								page.getSize() }, new ComputerMapper());
		page.setList(computerDTOMapper.mapToDTO(computerList));
		page.setCount(getCount(page.getQuery()));
		page.setPageCount(page.getCount() / page.getSize());
		return page;
	}
}
