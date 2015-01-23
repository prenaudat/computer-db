package com.excilys.computerdatabase.service.impl;

import java.sql.Connection;
import java.sql.SQLException;

import com.excilys.computerdatabase.dao.ConnectionManager;
import com.excilys.computerdatabase.dao.impl.ComputerDAO;
import com.excilys.computerdatabase.exception.PersistenceException;
import com.excilys.computerdatabase.model.Computer;
import com.excilys.computerdatabase.pagination.Page;
import com.excilys.computerdatabase.service.ComputerDBServiceInterface;

/**
 * @author paulr_000
 *
 */
public class ComputerDBService implements ComputerDBServiceInterface {
	ComputerDAO computerDAO = ComputerDAO.getInstance();
	ConnectionManager connectionmanager = ConnectionManager.getInstance();

	/**
	 * @param currentComputerPageIndex
	 * @param pageSize
	 * @return
	 * @throws SQLException
	 */
	public Page getPage(int pageNumber) {
		return computerDAO.getPage(pageNumber);
	}

	/**
	 * @param id
	 * @return
	 * @throws SQLException
	 */
	public Computer get(final long id) {
		return computerDAO.get(id);
	}

	/**
	 * @param id
	 * @throws SQLException
	 */
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
	public void update(final Computer computer) {
		computerDAO.update(computer);
	}

	public void save(final Computer computer) {
		computerDAO.save(computer);
	}

	/**
	 * @param query
	 * @return
	 */
	@Override
	public int getCount(String query) {
		return computerDAO.getCount(query);
	}

	/**
	 * @param page
	 * @return
	 */
	public Page getPage(Page page) {
		return computerDAO.getPage(page);
	}

	public void deleteByCompany(long id) {
		Connection conn = connectionmanager.getConnection();
		System.out.println("deleting company "+id);
		try {
			conn.setAutoCommit(false);
			computerDAO.removeByCompany(id, conn);
			conn.commit();
			conn.close();
		} catch (SQLException e) {
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e.printStackTrace();
			}
		}
	}
}
