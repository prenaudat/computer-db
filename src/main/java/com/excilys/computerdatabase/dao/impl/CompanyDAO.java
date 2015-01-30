package com.excilys.computerdatabase.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.stereotype.Repository;

import com.excilys.computerdatabase.dao.CompanyDAOInterface;
import com.excilys.computerdatabase.exception.PersistenceException;
import com.excilys.computerdatabase.mapper.row.impl.CompanyMapper;
import com.excilys.computerdatabase.model.Company;
import com.excilys.computerdatabase.pagination.Page;

/**
 * @author paulr_000 d
 */
// Computer Database Access object..
@Repository
public class CompanyDAO implements CompanyDAOInterface {
	private static final String UPDATE_STMT = "UPDATE company SET name=? WHERE id=? ;";
	private static final String INSERT_STMT = "INSERT INTO company(name) VALUES (?);";
	private static final String SINGLE_QUERY_STMT = "SELECT id, name FROM company WHERE id =?";
	private static final String QUERY_ALL = "SELECT id, name FROM company;";
	private static final String QUERY_PAGE = "SELECT id, name FROM company limit ?, ?;";
	private static final String DELETE_COMPANY = "DELETE FROM company WHERE id=?";
	private Logger LOGGER = LoggerFactory.getLogger(CompanyDAO.class);
	@Autowired
	private DataSource dataSource;
	private JdbcTemplate template;

	/**		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

	 * At DAO initialisation, sets DataSource for template.
	 */
	@Autowired
	public void setDataSource(DataSource dataSource) {
		this.template = new JdbcTemplate(dataSource);
	}

	/**
	 * @param id
	 * @return Return one company
	 */
	public Company get(long id) {
		List<Company> list = template.query(SINGLE_QUERY_STMT,
				new Long[] { id }, new CompanyMapper());
		if (list.size() > 0) {
			return list.get(1);
		} else {
			return null;
		}
	}

	/**
	 * @param name
	 */
	public void save(String name) {
		template.update(INSERT_STMT, new Object[] { name });
	}

	/**
	 * @param id
	 * @param name
	 */
	public void update(long id, String name) {
		template.update(UPDATE_STMT, new Object[] { name, id });
	}

	public List<Company> getAll() {
		return template.query(QUERY_ALL, new CompanyMapper());
	}

	public void remove(long id) {
		template.update(DELETE_COMPANY, new Object[] { id });
	}

	public List<Company> getPage(Page page) {
		 return template.query(QUERY_PAGE, new Object[] {page.getPageNumber()*page.getSize(), page.getSize()}, new CompanyMapper());
	}
}