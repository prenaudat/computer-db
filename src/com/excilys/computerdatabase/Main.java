package com.excilys.computerdatabase;
import com.excilys.computerdatabase.dao.CompanyDAO;
import com.excilys.computerdatabase.dao.ComputerDAO;
import com.excilys.computerdatabase.model.Computer;


public class Main {
	public static void main(String[] args ){
		System.out.println(CompanyDAO.getInstance().getCompanyById(1));
		System.out.println(ComputerDAO.getInstance().getComputerById(1));
		System.out.println(ComputerDAO.getInstance().getComputersByCompanyId(1));

	}

}