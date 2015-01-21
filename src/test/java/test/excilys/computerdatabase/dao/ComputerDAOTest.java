package test.excilys.computerdatabase.dao;

import static org.junit.Assert.assertEquals;

import java.sql.Timestamp;

import org.junit.Before;
import org.junit.Test;

import com.excilys.computerdatabase.model.Company;
import com.excilys.computerdatabase.model.Computer;
import com.excilys.computerdatabase.pagination.Page;

public class ComputerDAOTest {
	ComputerDAOMock mockComputerDAO;

	@Before
	public void initialize() {
		mockComputerDAO = new ComputerDAOMock();
	}

	@Test
	public void testGet() {
		Computer c1 = mockComputerDAO.get(1);
		Computer c2 = mockComputerDAO.get(1);
		assertEquals(c1, c2);
	}

	@Test
	public void testList() {
		Page c1 = mockComputerDAO.getPage(1);
		Page c2 = mockComputerDAO.getPage(1);
		assertEquals(c1.getList(), c2.getList());
	}

	@Test
	public void save() {
		Computer c = new Computer.Builder()
				.name("Test")
				.discontinued(
						Timestamp.valueOf("2001-12-11 00:00:00")
								.toLocalDateTime().toLocalDate())
				.introduced(
						Timestamp.valueOf("2000-12-11 00:00:00")
								.toLocalDateTime().toLocalDate())
				.company(
						new Company.CompanyBuilder().id(1).name("test").build())
				.build();
		int i1 = mockComputerDAO.save(c);
		c.setId(i1);
		Computer c1 = mockComputerDAO.get(i1);
		System.out.println(c);
		System.out.println(c1);
		assertEquals(c1, c);
	}

	@Test
	public void remove() {
		Computer c = new Computer.Builder()
				.name("Test")
				.discontinued(
						Timestamp.valueOf("2001-12-11 00:00:00")
								.toLocalDateTime().toLocalDate())
				.introduced(
						Timestamp.valueOf("2000-12-11 00:00:00")
								.toLocalDateTime().toLocalDate())
				.company(
						new Company.CompanyBuilder().id(1).name("test").build())
				.build();
		int i1 = mockComputerDAO.save(c);
		mockComputerDAO.remove(i1);
		assertEquals(
				mockComputerDAO.get(i1),
				new Computer.Builder().build());
	}
}
