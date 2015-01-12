package com.excilys.computerdatabase;
import java.sql.Timestamp;
import java.time.Instant;

import com.excilys.computerdatabase.dao.CompanyDAO;
import com.excilys.computerdatabase.dao.ComputerDAO;
import com.excilys.computerdatabase.model.Computer;


public class Main {
	public static void main(String[] args ){
		System.out.println(CompanyDAO.getInstance().getCompanyList(0,10));
		CompanyDAO.getInstance().createCompany("name");
		CompanyDAO.getInstance().updateCompany(1,"name");
		System.out.println(CompanyDAO.getInstance().getCompanyById(1));
		Instant now = Instant.now();
		System.out.println(ComputerDAO.getInstance().getComputerById(1));
		ComputerDAO.getInstance().updateComputer(1, "Frank", new Timestamp(now.toEpochMilli()), new Timestamp(now.toEpochMilli()), 2);
		ComputerDAO.getInstance().createComputer("Frank", new Timestamp(now.toEpochMilli()), new Timestamp(now.toEpochMilli()), 2);
		
	}

}