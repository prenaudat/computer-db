package com.excilys.computerdatabase.service;
import java.util.List;
import com.excilys.computerdatabase.model.Computer;
/**
 * @author excilys
 *
 */
public interface ComputerDBServiceInterface {
	public List<Computer> getList(final int currentComputerPageIndex,final int pageSize);
	public Computer get(final long id);
	public void remove(final long id);
	public void update(final Computer computer);
	public void save(final Computer computer);
}
