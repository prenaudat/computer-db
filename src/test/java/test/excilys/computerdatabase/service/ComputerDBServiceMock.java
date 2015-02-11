package test.excilys.computerdatabase.service;

import com.excilys.computerdatabase.model.Computer;
import com.excilys.computerdatabase.pagination.ComputerPage;
import com.excilys.computerdatabase.service.ComputerDBService;

import test.excilys.computerdatabase.dao.ComputerDAOMock;

public class ComputerDBServiceMock implements ComputerDBService{
	private ComputerDAOMock computerDAO;
	
	public ComputerDBServiceMock(ComputerDAOMock companyDAO) {
		this.computerDAO = companyDAO;
	}

	@Override
	public ComputerPage getPage(int pageNumber) {
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
	public int getCount(String query) {
		return computerDAO.getCount(query);
	}

	@Override
	public void removeByCompany(long id) {
		// TODO Auto-generated method stub
		
	}
}
