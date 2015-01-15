package test.excilys.computerdatabase.service;

import java.util.List;

import com.excilys.computerdatabase.model.Company;
import com.excilys.computerdatabase.service.CompanyDBServiceInterface;

public class CompanyDBServiceMock implements CompanyDBServiceInterface{

	@Override
	public List<Company> getList(int currentCompanyPageIndex, int pageSize) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Company get(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void save(String name) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(int id, String name) {
		// TODO Auto-generated method stub
		
	}

}
