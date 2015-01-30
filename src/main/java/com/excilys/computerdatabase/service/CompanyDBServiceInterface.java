package com.excilys.computerdatabase.service;

import java.util.List;

import com.excilys.computerdatabase.model.Company;
import com.excilys.computerdatabase.pagination.Page;

/**
 * @author paulr_000
 *
 */
public interface CompanyDBServiceInterface {

	public Company get(long id);

	public void save(String name);

	public void update(long id, String name);

	public void remove(long id);

	public List<Company> getPage(Page page);
}
