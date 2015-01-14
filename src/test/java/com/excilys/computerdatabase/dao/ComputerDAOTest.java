package com.excilys.computerdatabase.dao;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.excilys.computerdatabase.model.Company;
import com.excilys.computerdatabase.model.Computer;

public class ComputerDAOTest {
	private static ComputerDAO mockedComputerDAO;
	private static List<Computer> list;

	@Before
	public void setUp() throws Exception {
		mockedComputerDAO = mock(ComputerDAO.class);
		list = new ArrayList<Computer>();
		list.add(new Computer.ComputerBuilder()
				.id(1)
				.name("Frank")
				.introduced(LocalDateTime.now())
				.discontinued(null)
				.company(
						new Company.CompanyBuilder().name("Ocean").id(1)
								.build()).build());
		list.add(new Computer.ComputerBuilder()
		.id(1)
		.name("Hola")
		.introduced(LocalDateTime.now())
		.discontinued(null)
		.company(
				new Company.CompanyBuilder().name("cabron").id(2)
						.build()).build());
		when(mockedComputerDAO.get(1)).thenReturn(list.get(1));
		when(mockedComputerDAO.get(2)).thenReturn(list.get(2));
		when(mockedComputerDAO.getList(0,1)).thenReturn(list);
		

	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testGet() {
		// arrange
		// assert
		// assertEquals("Computer [id=1, name=Frank, introduced=null, discontinued=nullCompany [id=1, name=Cname]]",
		// computerDAO.get(1));
	}
}
