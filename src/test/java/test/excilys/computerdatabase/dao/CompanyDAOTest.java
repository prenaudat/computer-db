package test.excilys.computerdatabase.dao;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.excilys.computerdatabase.dao.ConnectionManager;
import com.excilys.computerdatabase.model.Company;

public class CompanyDAOTest {
	CompanyDAOMock mockCompanyDAO;
	List<Company> list = new ArrayList<Company>();
	ConnectionManager connectionManager = ConnectionManager.getInstance();

	@Before
	public void initialize() {
		mockCompanyDAO = new CompanyDAOMock();
		list.add(new Company(1L, "Apple Inc."));
		list.add(new Company(2L, "Thinking Machines"));

		Connection connection = null;
		Statement statement = null;
		connection = connectionManager.getConnection();
		try {
			statement = connection.createStatement();
		statement.execute("drop table if exists computer;");
		statement.execute("drop table if exists company;");

		statement
				.execute("create table company (id bigint not null auto_increment, name varchar(255), "
						+ "constraint pk_company primary key (id));");
		statement
				.execute("create table computer (id bigint not null auto_increment,name varchar(255), "
						+ "introduced timestamp NULL, discontinued timestamp NULL,"
						+ "company_id bigint default NULL,"
						+ "constraint pk_computer primary key (id));");
		statement
				.execute("alter table computer add constraint fk_computer_company_1 foreign key (company_id)"
						+ " references company (id) on delete restrict on update restrict;");
		statement
				.execute("create index ix_computer_company_1 on computer (company_id);");

		statement
				.execute("insert into company (id,name) values ( 1,'Apple Inc.');");
		statement
				.execute("insert into company (id,name) values ( 2,'Thinking Machines');");

		statement
				.execute("insert into computer (id,name,introduced,discontinued,company_id) values ( 1,'MacBook Pro 15.4 inch',null,null,1);");
		statement
				.execute("insert into computer (id,name,introduced,discontinued,company_id) values ( 2,'MacBook Pro','2006-01-10',null,1);");
		statement
				.execute("insert into computer (id,name,introduced,discontinued,company_id) values ( 3,'CM-2a',null,null,2);");
		statement
				.execute("insert into computer (id,name,introduced,discontinued,company_id) values ( 4,'CM-5','1991-01-01',null,2);");
		} catch (SQLException e2) {
			e2.printStackTrace();
		}
		try {
			statement.close();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		try {
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testGet() {
	    assertEquals(new Company(1L, "Apple Inc."), mockCompanyDAO.get(1L));
	    assertEquals(new Company(2L, "Thinking Machines"), mockCompanyDAO.get(2L));
	    assertNull(mockCompanyDAO.get(-1L));
	    assertNull(mockCompanyDAO.get(3L));
	}

	@Test
	public void testList() {
	    assertEquals(list, mockCompanyDAO.getPage(0));
	}

	@Test
	public void save() {
		int i1 = mockCompanyDAO.save("test");
		Company c1 = mockCompanyDAO.get(i1);
		assertEquals(c1.getName(), "test");
	}

	@Test
	public void update() {
		mockCompanyDAO.update(1, "test");
		Company c1 = mockCompanyDAO.get(1);
		assertEquals(c1.getName(), "test");
	}
}
