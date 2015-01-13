package com.excilys.computerdatabase.service;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;

import com.excilys.computerdatabase.dao.ComputerDAO;
import com.excilys.computerdatabase.model.Computer;

/**
 * @author paulr_000
 *
 */
public class ComputerDBService {
	/**
	 * @param currentComputerPageIndex
	 * @param pageSize
	 * @return
	 * @throws SQLException
	 */
	public ArrayList<Computer> getComputerList(int currentComputerPageIndex, int pageSize) throws SQLException{
		return ComputerDAO.getInstance().getComputerList(
				currentComputerPageIndex, pageSize);
	}
	/**
	 * @param id
	 * @return
	 * @throws SQLException
	 */
	public Computer getComputerById(int id) throws SQLException{
		return ComputerDAO.getInstance().getComputerById(id);
	}
	/**
	 * @return
	 * @throws SQLException
	 */
	public int count() throws SQLException{
		return ComputerDAO.getInstance().count();
	}
	/**
	 * @param id
	 * @throws SQLException
	 */
	public void remove(int id) throws SQLException{
		ComputerDAO.getInstance().remove(id);
	}
	/**
	 * @param id
	 * @param name
	 * @param introducedDate
	 * @param discontinuedDate
	 * @param cid
	 * @throws SQLException 
	 */
	public void updateComputer(int id, String name,	Timestamp introducedDate, Timestamp discontinuedDate, int cid) throws SQLException{
		ComputerDAO.getInstance().updateComputer(id, name,
				introducedDate, discontinuedDate, cid);
	}
	public void createComputer(String name,	Timestamp introducedDate, Timestamp discontinuedDate, int cid) throws SQLException{
		ComputerDAO.getInstance().createComputer(name,	introducedDate, discontinuedDate, cid);

	}
}
