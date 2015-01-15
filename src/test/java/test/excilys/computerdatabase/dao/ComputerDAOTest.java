package test.excilys.computerdatabase.dao;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.excilys.computerdatabase.dao.impl.ComputerDAO;
import com.excilys.computerdatabase.exception.PersistenceException;
import com.excilys.computerdatabase.model.Company;
import com.excilys.computerdatabase.model.Computer;
import com.excilys.computerdatabase.model.Computer;

public class ComputerDAOTest {
	ComputerDAOMock mockComputerDAO;
	@Before public void initialize(){
		mockComputerDAO = new ComputerDAOMock();
	}


	@Test public void testGet(){
		Computer c1 = mockComputerDAO.get(1);
		Computer c2 = mockComputerDAO.get(1);
		assertEquals(c1, c2);
	}
	@Test public void testList(){
		List<Computer> c1 = mockComputerDAO.getList(1,2);
		List<Computer> c2 = mockComputerDAO.getList(1,2);
		assertEquals(c1, c2);
	}
	@Test public void save(){
		Computer c = new Computer.ComputerBuilder().name("Test").discontinued(LocalDateTime.now()).introduced(LocalDateTime.now()).company(new Company.CompanyBuilder().id(1).build()).build();
		int i1 = mockComputerDAO.save(c);
		System.out.println(i1);
		Computer c1 = mockComputerDAO.get(i1);
		assertEquals(c1, c);
	}
	
}

