package com.excilys.computerdatabase.console;

import java.util.Scanner;

import org.apache.commons.validator.GenericValidator;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import com.excilys.computerdatabase.binding.common.resources.PageResponse;
import com.excilys.computerdatabase.binding.dto.model.ComputerDTO;
import com.excilys.computerdatabase.restinterface.ICompanyRestService;
import com.excilys.computerdatabase.restinterface.IComputerRestService;

/**
 * Client for remote CRUD via REST API
 * @author excilys
 *
 */
public class Client {

	private IComputerRestService computerWS;
	private ICompanyRestService companyWS;
	private Boolean loop;
	private Scanner sc;
	private Pageable pageable;
	private static final String FAIL = "You failed to select an available option, please try again";
	private static final String MAIN_MENU = "A) List computerss \nB) List companies \nC) Detailed computer view \nD) Create a computer \nE) Update a computer \nF) Delete a computer \nG) Delete Company+Computers";

	/**
	 * Display page of Computers and calls Computer Menu
	 * 
	 */
	public void getComputerList() {
		Boolean innerLoop = true;
		while (innerLoop) {
			PageResponse<ComputerDTO> page = computerWS.getPage(pageable.getPageNumber());
			System.out.println(page.getContent());
			innerLoop = getComputerListMenu(page);
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
			if (detailedLoop) {
				System.out
						.println(computerWS.findOne(Long.parseLong(id)));
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
			deleteLoop = !GenericValidator.isLong(id);
		}
		computerWS.remove(Long.parseLong(id));
	}

	/**
	 * Update a computer identified by its ID. Loop back every step if invalid.
	 * 
	 */
	public void updateComputer() {
		Boolean idValidation = true;
		String input = "";
		while (idValidation) {
			System.out.println("Id of computer to update?");
			input = sc.nextLine();
			idValidation = !GenericValidator.isLong(input);
		}
		Boolean nameLoop = true;
		ComputerDTO c = computerWS.findOne(Long.parseLong(input));
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
			System.out.println("New introduction year? Format : YYYY-MM-DD");
			String introduced = sc.nextLine();
			introducedLoop = !(GenericValidator.isDate(introduced, "yyyy-mm-dd",
					true) || introduced != null);
			if (!introducedLoop) {
				c.setIntroduced(introduced);
			}
		}
		Boolean discontinuedLoop = true;
		while (discontinuedLoop) {
			System.out.println("Current discontinuation date is : "
					+ c.getDiscontinued());
			System.out.println("New discontinuation year? Format : YYYY-MM-DD");
			String discontinued = sc.nextLine();
			discontinuedLoop = !(GenericValidator.isDate(discontinued,
					"yyyy-mm-dd", true)|| discontinued != null) ;
			if (!discontinuedLoop) {
				c.setDiscontinued(discontinued);
			}
		}
		Boolean companyLoop = true;
		while (companyLoop) {
			System.out.println("new company ID?");
			String id = sc.nextLine();
			companyLoop = !GenericValidator.isLong(id);
			if (!companyLoop) {
				c.setCompanyId(Long.parseLong(id));
			}
		}
		computerWS.save(c);

	}

	/**
	 * Create computer
	 * 
	 */
	public void createComputer() {
		ComputerDTO.Builder c = new ComputerDTO.Builder();
		Boolean nameLoop = true;
		while (nameLoop) {
			System.out.println("New name?");
			String name = sc.nextLine();
			nameLoop = !GenericValidator.minLength(name, 1);
			if (!nameLoop) {
				c.withName(name);
			}
		}
		Boolean introducedLoop = true;
		while (introducedLoop) {
			System.out.println("Introduction year? Format : YYYY-MM-DD");
			String introduced = sc.nextLine();
			introducedLoop = !(GenericValidator.isDate(introduced, "yyyy-mm-dd",
					true) || introduced != null);
			if (!introducedLoop) {
				c.withIntroduced(introduced);
			}
		}
		Boolean discontinuedLoop = true;
		while (discontinuedLoop) {
			System.out.println("Discontinuation year? Format : YYYY-MM-DD");
			String discontinued = sc.nextLine();
			discontinuedLoop = !(GenericValidator.isDate(discontinued,
					"yyyy-mm-dd", true)|| discontinued != null);
			if (!discontinuedLoop) {
				c.withDiscontinued(discontinued);
			}
		}
		Boolean companyLoop = true;
		while (companyLoop) {
			System.out.println("Company ID?");
			String id = sc.nextLine();
			companyLoop = !GenericValidator.isLong(id);
			if (!companyLoop) {
				c.withCompanyId(Long.parseLong(id));
			}
		}
		computerWS.save(c.build());
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
					.println("A) Return to previous menu \nB) New detailed computer view");
			switch (sc.nextLine().toLowerCase()) {
			case "a":
				innerLoop = false;
				break;
			case "b":
				innerLoop = false;
				outerLoop = true;
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
	private Boolean getComputerListMenu(Page<ComputerDTO> page) {
		Boolean innerLoop = true;
		boolean outerLoop = true;
		while (innerLoop) {
			System.out.println("A) Return to previous menu");
			if (page.hasNext()) {
				System.out.println("B) Next page");
			}
			if (page.hasPrevious()) {
				System.out.println("C) Previous page");
			}
			switch (sc.nextLine().toLowerCase()) {
			case "a":
				innerLoop = false;
				outerLoop = false;
				break;
			case "b":
				pageable = page.nextPageable();
				outerLoop = true;
				innerLoop = false;
				break;
			case "c":
				pageable = page.previousPageable();
				outerLoop = true;
				innerLoop = false;
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
		companyWS.findAll().forEach(c->System.out.println(c));
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
				companyWS.remove(Long.parseLong(input));
			}
		}
	}

	/**
	 * Instantiate DBservices, associated counters
	 */
	public void init() {
		final ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
				"console-context.xml");
		computerWS = (IComputerRestService) context
				.getBean("computerRESTUtils");
		companyWS = (ICompanyRestService) context
				.getBean("companyRESTUtils");
		sc = new Scanner(System.in);
		loop = true;
		pageable = new PageRequest(0, 20);
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