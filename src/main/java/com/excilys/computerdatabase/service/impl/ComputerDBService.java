package com.excilys.computerdatabase.service.impl;

import java.sql.Connection;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import com.excilys.computerdatabase.dao.ConnectionManager;
import com.excilys.computerdatabase.dao.impl.CompanyDAO;
import com.excilys.computerdatabase.dao.impl.ComputerDAO;
import com.excilys.computerdatabase.exception.PersistenceException;
import com.excilys.computerdatabase.model.Computer;
import com.excilys.computerdatabase.pagination.Page;
import com.excilys.computerdatabase.service.ComputerDBServiceInterface;

/**
 * @author paulr_000
 *
 */
@Service("computerService")
public class ComputerDBService implements ComputerDBServiceInterface {
	@Autowired
	ComputerDAO computerDAO;
	@Autowired
	CompanyDAO companyDAO;

	ConnectionManager connectionmanager = ConnectionManager.getInstance();

	/**
	 * @param currentComputerPageIndex
	 * @param pageSize
	 * @return
	 * @throws SQLException
	 */
	@Transactional(readOnly=true)
	public Page getPage(int pageNumber) {
		return computerDAO.getPage(pageNumber);
	}

	/**
	 * @param id
	 * @return Computer corresponding to ID
	 * 
	 * @throws SQLException
	 */
	@Transactional(readOnly=true)
	public Computer get(final long id) {
		return computerDAO.get(id);
	}

	/**
	 * Return count of computerDAO
	 * 
	 * @param query : query for which results should be counted
	 * @return the count.
	 */
	@Override
	@Transactional(readOnly=true)
	public int getCount(String query) {
		return computerDAO.getCount(query);
	}
	
	/**
	 * Return page according to its criteria.
	 * 
	 * @param page
	 * @return
	 */
	@Transactional(readOnly=true)
	public Page getPage(Page page) {
		return computerDAO.getPage(page);
	}

	/**
	 * @param id
	 * @throws SQLException
	 */
	@Transactional(readOnly=false)
	public void remove(final long id) {
		computerDAO.remove(id);
	}

	/**
	 * @param id
	 * @param name
	 * @param introducedDate
	 * @param discontinuedDate
	 * @param cid
	 * @throws SQLException
	 */
	@Transactional(readOnly=false)
	public void update(final Computer computer) {
		computerDAO.update(computer);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.excilys.computerdatabase.service.ComputerDBServiceInterface#save(
	 * com.excilys.computerdatabase.model.Computer)
	 */
	@Transactional(readOnly=false)
	public void save(final Computer computer) {
		computerDAO.save(computer);
	}



	/*
	 * (non-Javadoc)
	 * 
	 * @see com.excilys.computerdatabase.service.ComputerDBServiceInterface#
	 * removeByCompany(long)
	 */
	@Transactional(readOnly=false)
	public void removeByCompany(long id) {
		Connection conn = connectionmanager.getConnection();
		try {
			computerDAO.removeByCompany(id);
			if(true){
				throw new RuntimeException();
			}
			companyDAO.remove(id);
			conn.commit();
		} catch (SQLException | PersistenceException e) {
			
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				throw new PersistenceException();
			}
		}
	}
}
