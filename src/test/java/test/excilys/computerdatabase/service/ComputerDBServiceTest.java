package test.excilys.computerdatabase.service;

import static org.junit.Assert.fail;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import org.junit.Before;
import org.junit.Test;

import com.excilys.computerdatabase.model.Computer;

import test.excilys.computerdatabase.dao.ComputerDAOMock;

/**
 * Test class for Junit. tests get, List, save, update and delete services.
 * 
 * @author excilys
 *
 */
public class ComputerDBServiceTest {
	private ComputerDBServiceMock computerDBService;
	private ComputerDAOMock computerDAO;

	@Before
	public void setup() {
		computerDAO = mock(ComputerDAOMock.class);
		computerDBService = new ComputerDBServiceMock(computerDAO);
	}

	@Test
	public void testGet() {
		computerDBService.get(10);
		verify(computerDAO).get(10);
	}

	@Test
	public void testList() {
		computerDBService.getPage(1);
		verify(computerDAO).getPage(1);
	}

	@Test
	public void testSave() {
		computerDBService.save(new Computer.Builder().build());
		verify(computerDAO).save(new Computer.Builder().build());
	}

	@Test
	public void testUpdate() {
		computerDBService.update(new Computer.Builder().build());
		verify(computerDAO).update(new Computer.Builder().build());
	}

	@Test
	public void testRemove() {
		computerDBService.remove(0);
		verify(computerDAO).remove(0);
	}

}
