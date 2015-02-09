package com.excilys.computerdatabase.cli;

import java.time.LocalDate;
import java.util.Locale;
import java.util.Scanner;

import org.apache.commons.validator.GenericValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.excilys.computerdatabase.model.Company;
import com.excilys.computerdatabase.model.Computer;
import com.excilys.computerdatabase.pagination.Page;
import com.excilys.computerdatabase.service.impl.CompanyDBServiceImpl;
import com.excilys.computerdatabase.service.impl.ComputerDBServiceImpl;

/**
 * @author excilys
 *
 */
public class Client {
	// Database Services
	private ComputerDBServiceImpl computerDBService;
	private CompanyDBServiceImpl companyDBService;
	// Page index for Company and Computer Directory
	Page page;
	private Boolean loop;
	private Scanner sc;
	 
	private static final String FAIL = "You failed to select an available option, please try again";
	private static final String MAIN_MENU = "A) List computers \nB) List companies \nC) Detailed computer view \nD) Create a computer \nE) Update a computer \nF) Delete a computer \nG) Change page size \nH) Delete Company+Computers";

	// Logger for this class

	/**
	 * Display page of Computers and calls Computer Menu
	 * 
	 */
	public void getComputerList() {
		Boolean innerLoop = true;
		while (innerLoop) {
			System.out.println(computerDBService.getPage(page.getPageNumber()));
			innerLoop = getComputerListMenu();
		}
	}

	/**
	 * Display a computer identified by ID if it is valid.
	 * 
	 */
	public void getComputerById() {
		Boolean detailedLoop = true;
		while (detailedLoop) {
			System.out.println("Id of computer to visualize?");
			final String id = sc.nextLine();
			detailedLoop = GenericValidator.isLong(id);
			if (!detailedLoop) {
				System.out.println(computerDBService.get(Integer.parseInt(id)));
				detailedLoop = getComputerMenu(Integer.parseInt(id));
			}
		}
	}

	/**
	 * Query ID of computer to be deleted. If valid, delete computer.
	 * 
	 */
	public void deleteComputer() {
		Boolean deleteLoop = true;
		String id = "";
		while (deleteLoop) {
			System.out
					.println("Enter id of computer would you like to delete?");
			id = sc.nextLine();
			deleteLoop = GenericValidator.isLong(id);
		}
		computerDBService.remove(id);
	}

	/**
	 * Update a computer identified by its ID. Loop back every step if invalid.
	 * 
	 */
	public void updateComputer() {
		System.out.println("A) List computers \nB) Id of computer to update");
		switch (sc.nextLine().toLowerCase()) {
		case "a":
			Boolean displayLoop = true;
			while (displayLoop) {
				System.out.println(computerDBService.getPage(page
						.getPageNumber()));
				displayLoop = getComputerListMenu();
			}
			break;
		case "b":
			Boolean idValidation = true;
			String input = "";
			while (idValidation) {
				System.out.println("Id of computer to update?");
				input = sc.nextLine();
				idValidation = GenericValidator.isLong(input);
			}
			Boolean nameLoop = true;
			Computer c = computerDBService.get(Long.parseLong(input));
			while (nameLoop) {
				System.out.println("Current name is : " + c.getName());
				System.out.println("New name?");
				String name = sc.nextLine();
				nameLoop = !GenericValidator.minLength(name, 1);
				if (!nameLoop) {
					c.setName(name);
				}
			}
			Boolean introducedLoop = true;
			while (introducedLoop) {
				System.out.println("Current introduction date is : "
						+ c.getIntroduced());
				System.out
						.println("New introduction year? Format : YYYY-MM-DD");
				String introduced = sc.nextLine();
				introducedLoop = !GenericValidator.isDate(introduced,
						Locale.FRANCE);
				if (!introducedLoop) {
					c.setIntroduced(LocalDate.parse(introduced));
				}
			}
			Boolean discontinuedLoop = true;
			while (discontinuedLoop) {
				System.out.println("Current introduction date is : "
						+ c.getIntroduced());
				System.out
						.println("New introduction year? Format : YYYY-MM-DD");
				String discontinued = sc.nextLine();
				discontinuedLoop = !GenericValidator.isDate(discontinued,
						Locale.FRANCE);
				if (!discontinuedLoop) {
					c.setDiscontinued(LocalDate.parse(discontinued));
				}
			}
			Boolean companyLoop = true;
			while (companyLoop) {
				System.out.println("new company ID?");
				String id = sc.nextLine();
				companyLoop = !GenericValidator.isLong(id);
				if (!companyLoop) {
					c.setCompany(new Company.Builder().id(Long.parseLong(id))
							.build());
				}
			}
			computerDBService.update(c);

		}

	}

	/**
	 * Create computer
	 * 
	 */
	public void createComputer() {
		Computer.Builder c = new Computer.Builder();
		Boolean nameLoop = true;
		while (nameLoop) {
			System.out.println("New name?");
			String name = sc.nextLine();
			nameLoop = !GenericValidator.minLength(name, 1);
			if (!nameLoop) {
				c.name(name);
			}
		}
		Boolean introducedLoop = true;
		while (introducedLoop) {
			System.out.println("Introduction year? Format : YYYY-MM-DD");
			String introduced = sc.nextLine();
			introducedLoop = !GenericValidator
					.isDate(introduced, Locale.FRANCE);
			if (!introducedLoop) {
				c.introduced(LocalDate.parse(introduced));
			}
		}
		Boolean discontinuedLoop = true;
		while (discontinuedLoop) {
			System.out.println("Discontinuation year? Format : YYYY-MM-DD");
			String discontinued = sc.nextLine();
			discontinuedLoop = !GenericValidator.isDate(discontinued,
					Locale.FRANCE);
			if (!discontinuedLoop) {
				c.discontinued(LocalDate.parse(discontinued));
			}
		}
		Boolean companyLoop = true;
		while (companyLoop) {
			System.out.println("Company ID?");
			String id = sc.nextLine();
			companyLoop = !GenericValidator.isLong(id);
			if (!companyLoop) {
				c.company(new Company.Builder().id(Long.parseLong(id)).build());
			}
		}
		computerDBService.save(c.build());
	}

	/**
	 * Displays menu after a computer is displayed
	 * 
	 * @param index
	 * @return
	 */
	private Boolean getComputerMenu(int index) {
		Boolean innerLoop = true;
		Boolean outerLoop = false;
		while (innerLoop) {
			System.out
					.println("A) Return to previous menu \nB) New detailed computer view \nC) Edit");
			switch (sc.nextLine().toLowerCase()) {
			case "a":
				innerLoop = false;
				break;
			case "b":
				innerLoop = false;
				outerLoop = true;
				break;
			case "c":
				innerLoop = false;
				System.out.println("coming soon");
				break;
			default:
				fail();
				innerLoop = true;
				break;
			}
		}
		return outerLoop;
	}

	/**
	 * Displays menu after displaying computer List. User can change pages or
	 * return to previous menu
	 * 
	 * @return
	 */
	private Boolean getComputerListMenu() {
		Boolean innerLoop = true;
		boolean outerLoop = true;
		while (innerLoop) {
			System.out.println("A) Return to previous menu\nB) Next page");
			if (page.getPageNumber() > 0) {
				System.out.println("C) Previous page");
			}
			switch (sc.nextLine().toLowerCase()) {
			case "a":
				innerLoop = false;
				outerLoop = false;
				page.setPageNumber(0);
				break;
			case "b":
				page.setPageNumber(page.getPageNumber() + 1);
				outerLoop = true;
				innerLoop = false;
				break;
			case "c":
				if (page.getPageNumber() > page.getSize()) {
					page.setPageNumber(page.getPageNumber() - 1);
					outerLoop = true;
					innerLoop = false;
				} else {
					page.setPageNumber(0);
					outerLoop = true;
					innerLoop = false;
				}
				break;
			default:
				fail();
				outerLoop = true;
				innerLoop = true;
				break;
			}
		}
		return outerLoop;
	}

	/**
	 * Returns list of companies
	 * 
	 */
	public void getCompanyList() {
		Boolean innerLoop = true;
		while (innerLoop) {
			System.out.println(companyDBService.getPage(page));
			innerLoop = getCompanyListMenu(page.getPageNumber());
		}
	}

	// Returns menu of company List. User can either return to previous menu or
	// change page.
	private Boolean getCompanyListMenu(int index) {
		Boolean innerLoop = true;
		Boolean outerLoop = false;
		while (innerLoop) {
			System.out.println("A) Return to previous menu\nB) Next page");
			if (page.getPageNumber() > 0) {
				System.out.println("C) Previous page");
			}
			switch (sc.nextLine().toLowerCase()) {
			case "a":
				innerLoop = false;
				outerLoop = false;
				page.setPageNumber(0);
				break;
			case "b":
				page.setPageNumber(page.getPageNumber() + 1);
				innerLoop = false;
				outerLoop = true;
				break;
			case "c":
				if (page.getPageNumber() > page.getSize()) {
					page.setPageNumber(page.getPageNumber() - page.getSize());
					innerLoop = false;
					outerLoop = true;
				} else {
					page.setPageNumber(0);
					innerLoop = false;
					outerLoop = true;
				}
				break;
			default:
				fail();
				break;
			}
		}
		return outerLoop;
	}

	/**
	 * Display page size and modify.size
	 */
	public void changePageSize() {
		boolean pageSizeLoop = true;
		while (pageSizeLoop) {
			System.out.println("Current page size is " + page.getSize()
					+ ".\nChange page size to :");
			String input = sc.nextLine();
			pageSizeLoop = !GenericValidator.isInt(input);
			if (!pageSizeLoop) {
				page.setSize(Integer.parseInt(input));
				System.out.printf("Page size changed to %d \n", page.getSize());
			}
		}
	}

	/**
	 * Display page size and modify.
	 */
	public void deleteCompanyAndComputers() {
		boolean massDelete = true;
		while (massDelete) {
			System.out.println("Please select ID of company to delete ");
			String input = sc.nextLine();
			massDelete = !GenericValidator.isLong(input);
			if (!massDelete) {
				computerDBService.remove(input);
			}
		}
	}

	/**
	 * Instantiate DBservices, associated counters
	 */
	public void init() {
		final ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
				"context.xml");
		computerDBService = (ComputerDBServiceImpl) context
				.getBean("computerService");
		companyDBService = (CompanyDBServiceImpl) context
				.getBean("companyService");
		sc = new Scanner(System.in);
		loop = true;
		page = new Page();
		mainMenu();
		context.close();
	}

	/**
	 * Display main menu loop
	 */
	public void mainMenu() {
		while (loop) {
			System.out.println(MAIN_MENU);
			switch (sc.nextLine().toLowerCase()) {
			case "a":
				getComputerList();
				break;
			case "b":
				getCompanyList();
				break;
			case "c":
				getComputerById();
				break;
			case "d":
				createComputer();
				break;
			case "e":
				updateComputer();
				break;
			case "f":
				deleteComputer();
				break;
			case "g":
				changePageSize();
				break;
			case "h":
				deleteCompanyAndComputers();
				break;
			default:
				fail();
				break;
			}
		}
	}

	/**
	 * Display failure message
	 */
	public void fail() {
		System.out.println(FAIL);
	}

	/**
	 * Execution start point: Instantiate Client and start it.
	 * 
	 * @param args
	 */
	public static void main(final String[] args) {
		Client client = new Client();
		client.init();

	}

}
