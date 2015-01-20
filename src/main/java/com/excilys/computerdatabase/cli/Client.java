package com.excilys.computerdatabase.cli;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.Scanner;
import com.excilys.computerdatabase.model.Company;
import com.excilys.computerdatabase.model.Computer;
import com.excilys.computerdatabase.service.impl.CompanyDBService;
import com.excilys.computerdatabase.service.impl.ComputerDBService;
import com.excilys.computerdatabase.validator.Validator;

/**
 * @author excilys
 *
 */
public class Client {
	//Database Services
	private ComputerDBService computerDBService;
	private CompanyDBService companyDBService;
	//Page index for Company and Computer Directory
	private int pageNumber=0;
	private int currentComputerPageIndex = 0;
	private int currentCompanyPageIndex = 0;
	private int pageSize = 10;
	private Boolean loop;
	private Scanner sc;
	private static final String FAIL = "You failed to select an available option, please try again";
	private static final String MAIN_MENU = "A) List computers \nB) List companies \nC) Detailed computer view \nD) Create a computer \nE) Update a computer \nF) Delete a computer \nG) Change page size";
	//Logger for this class

	/**
	 * Display page of Computers and calls Computer Menu
	 * 
	 */
	public void getComputerList() {
		Boolean innerLoop = true;
		while (innerLoop) {
			System.out.println(computerDBService.getPage(
					pageNumber));
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
			detailedLoop = !Validator.isValidNumber(id);
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
			deleteLoop = !Validator.isValidNumber(id);
		}
		computerDBService.remove(Long.parseLong(id));
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
				System.out.println(computerDBService.getPage(
						pageNumber));
				displayLoop = getComputerListMenu();
			}
			break;
		case "b":
			Boolean idValidation = true;
			String input = "";
			while (idValidation) {
				System.out.println("Id of computer to update?");
				input = sc.nextLine();
				idValidation = !Validator.isValidNumber(input);
			}
			Boolean nameLoop = true;
			Computer c = computerDBService.get(Long.parseLong(input));
			while (nameLoop) {
				System.out.println("Current name is : " + c.getName());
				System.out.println("New name?");
				String name = sc.nextLine();
				nameLoop = !Validator.isValidString(name);
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
				introducedLoop = !Validator.isValidDate(introduced);
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
				discontinuedLoop = !Validator.isValidDate(discontinued);
				if (!discontinuedLoop) {
					c.setDiscontinued(LocalDate.parse(discontinued));
				}
			}
			Boolean companyLoop = true;
			while (companyLoop) {
				System.out.println("new company ID?");
				String id = sc.nextLine();
				companyLoop = !Validator.isValidNumber(id);
				if (!companyLoop) {
					c.setCompany(new Company.CompanyBuilder().id(
							Long.parseLong(id)).build());
				}
			}
			computerDBService.update(c);

		}

	}

	
	/**
	 *Create computer
	 * 
	 */
	public void createComputer() {
		Computer.ComputerBuilder c = new Computer.ComputerBuilder();
		Boolean nameLoop = true;
		while (nameLoop) {
			System.out.println("New name?");
			String name = sc.nextLine();
			nameLoop = !Validator.isValidString(name);
			if (!nameLoop) {
				c.name(name);
			}
		}
		Boolean introducedLoop = true;
		while (introducedLoop) {
			System.out.println("Introduction year? Format : YYYY-MM-DD");
			String introduced = sc.nextLine();
			introducedLoop = !Validator.isValidDate(introduced);
			if (!introducedLoop) {
				c.introduced(LocalDate.parse(introduced));
			}
		}
		Boolean discontinuedLoop = true;
		while (discontinuedLoop) {
			System.out.println("Discontinuation year? Format : YYYY-MM-DD");
			String discontinued = sc.nextLine();
			discontinuedLoop = !Validator.isValidDate(discontinued);
			if (!discontinuedLoop) {
				c.discontinued(LocalDate.parse(discontinued));
			}
		}
		Boolean companyLoop = true;
		while (companyLoop) {
			System.out.println("Company ID?");
			String id = sc.nextLine();
			companyLoop = !Validator.isValidNumber(id);
			if (!companyLoop) {
				c.company(new Company.CompanyBuilder().id(Long.parseLong(id))
						.build());
			}
		}
		computerDBService.save(c.build());
	}


	/**
	 * Displays menu after a computer is displayed
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
	 * Displays menu after displaying computer List. User can change pages or return to previous menu
	 * @return
	 */
	private Boolean getComputerListMenu() {
		Boolean innerLoop = true;
		boolean outerLoop = true;
		while (innerLoop) {
			System.out.println("A) Return to previous menu\nB) Next page");
			if (currentComputerPageIndex > 0) {
				System.out.println("C) Previous page");
			}
			switch (sc.nextLine().toLowerCase()) {
			case "a":
				innerLoop = false;
				outerLoop = false;
				break;
			case "b":
				currentComputerPageIndex += pageSize;
				outerLoop = true;
				innerLoop = false;
				break;
			case "c":
				if (currentComputerPageIndex > pageSize) {
					currentComputerPageIndex -= pageSize;
					outerLoop = true;
					innerLoop = false;
				} else {
					currentComputerPageIndex = 0;
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
			System.out.println(companyDBService.getPage(
					pageNumber));
			innerLoop = getCompanyListMenu(currentCompanyPageIndex);
		}
	}

	// Returns menu of company List. User can either return to previous menu or
	// change page.
	private Boolean getCompanyListMenu(int index) {
		Boolean innerLoop = true;
		Boolean outerLoop = false;
		while (innerLoop) {
			System.out.println("A) Return to previous menu\nB) Next page");
			if (currentCompanyPageIndex > 0) {
				System.out.println("C) Previous page");
			}
			switch (sc.nextLine().toLowerCase()) {
			case "a":
				innerLoop = false;
				outerLoop = false;
				break;
			case "b":
				currentCompanyPageIndex += pageSize;
				innerLoop = false;
				outerLoop = true;
				break;
			case "c":
				if (currentCompanyPageIndex > pageSize) {
					currentCompanyPageIndex -= pageSize;
					innerLoop = false;
					outerLoop = true;
				} else {
					currentCompanyPageIndex = 0;
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
	 * Display page size and modify.
	 */
	public void changePageSize() {
		boolean pageSizeLoop = true;
		while (pageSizeLoop) {
			System.out.println("Current page size is " + pageSize
					+ ".\nChange page size to :");
			String input = sc.nextLine();
			pageSizeLoop = !Validator.isValidNumber(input);
			if (!pageSizeLoop) {
				pageSize = Integer.parseInt(input);
				System.out.printf("Page size changed to %d \n", pageSize);
			}
		}
	}

	
	/**
	 *Instantiate DBservices, associated counters 
	 */
	public void init() {
		computerDBService = new ComputerDBService();
		companyDBService = new CompanyDBService();
		sc = new Scanner(System.in);
		loop = true;
		mainMenu();
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
				loop = false;
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
	 * @param args  
	 */
	public static void main(final String[] args) {
		Client client = new Client();
		client.init();
		
	}

}
