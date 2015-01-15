package test.excilys.computerdatabase.service;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;

import test.excilys.computerdatabase.dao.CompanyDAOMock;

import com.excilys.computerdatabase.dao.impl.CompanyDAO;
import com.excilys.computerdatabase.model.Company;
import com.excilys.computerdatabase.service.impl.CompanyDBService;

/**
 * Test class for CompanyDBService.Tests get, getList, save and update
 * @author excilys
 *
 */
public class CompanyDBServiceTest {
	private CompanyDBServiceMock companyDBService;
	private CompanyDAOMock companyDAO;

	@Before
	public void setup() {
		companyDAO = mock(CompanyDAOMock.class);
		companyDBService = new CompanyDBServiceMock(companyDAO);
	}

	@Test
	public void testGet() {
		 when(companyDAO.get(1)).thenReturn(new Company.CompanyBuilder().id(1).name("Apple inc.").build()); 
		 companyDBService.get(1);
		 verify(companyDAO).get(1);
	}
	@Test
	public void testList() {
		 when(companyDAO.getList(1,2)).thenReturn(Arrays.asList(new Company.CompanyBuilder().id(1).name("Apple inc.").build(),new Company.CompanyBuilder().id(2).name("Thinking Machines").build())); 
		 companyDBService.get(1);
		 verify(companyDAO).get(1);
	}
	
	@Test
	public void save() {
		 companyDBService.save("name");
		 verify(companyDAO).save("name");
	}
	@Test
	public void update() {
		 companyDBService.update(1,"name");
		 verify(companyDAO).update(1,"name");
	}
}
