package test.excilys.computerdatabase.service;

import java.util.List;

import com.excilys.computerdatabase.model.Computer;
import com.excilys.computerdatabase.pagination.Page;
import com.excilys.computerdatabase.service.ComputerDBServiceInterface;

import test.excilys.computerdatabase.dao.CompanyDAOMock;
import test.excilys.computerdatabase.dao.ComputerDAOMock;

public class ComputerDBServiceMock implements ComputerDBServiceInterface{
	private ComputerDAOMock computerDAO;
	
	public ComputerDBServiceMock(ComputerDAOMock companyDAO) {
		this.computerDAO = companyDAO;
	}

	@Override
	public Page getPage(int pageNumber) {
		return computerDAO.getPage(pageNumber);
	}

	@Override
	public Computer get(long id) {
		return computerDAO.get(id);
	}

	@Override
	public void remove(long id) {
		computerDAO.remove(id);
	}

	@Override
	public void update(Computer computer) {
		computerDAO.update(computer);
	}

	@Override
	public void save(Computer computer) {
		computerDAO.save(computer);
	}

	@Override
	public int getCount() {
		return computerDAO.getCount();
	}
}
