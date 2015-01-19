package test.excilys.computerdatabase.dao;


import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.excilys.computerdatabase.model.Company;

public class CompanyDAOTest {
	CompanyDAOMock mockCompanyDAO;
	@Before public void initialize(){
		mockCompanyDAO = new CompanyDAOMock();
	}

	@Test public void testGet(){
		Company c1 = mockCompanyDAO.get(1);
		Company c2 = mockCompanyDAO.get(1);
		assertEquals(c1, c2);
	}
	@Test public void testList(){
		List<Company> c1 = mockCompanyDAO.getPage(1);
		List<Company> c2 = mockCompanyDAO.getPage(1);
		assertEquals(c1, c2);
	}
	@Test public void save(){
		int i1 = mockCompanyDAO.save("test");
		Company c1 = mockCompanyDAO.get(i1);
		assertEquals(c1.getName(), "test");
	}
	@Test public void update(){
		mockCompanyDAO.update(1,"test");
		Company c1 = mockCompanyDAO.get(1);
		assertEquals(c1.getName(), "test");
	}
}
