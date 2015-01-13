package com.excilys.computerdatabase.cli;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.InputMismatchException;
import java.util.Scanner;

import com.excilys.computerdatabase.model.Computer;
import com.excilys.computerdatabase.service.CompanyDBService;
import com.excilys.computerdatabase.service.ComputerDBService;

/**
 * @author paulr_000
 *
 */
public class Client {
	private ComputerDBService computerDBService;
	private CompanyDBService companyDBService;
	private int pageSize = 10;
	private int currentComputerPageIndex = 0;
	private int currentCompanyPageIndex = 0;
	private int computerCount;
	private int companyCount;
	private Boolean loop;
	private Scanner sc;
	private static final String FAIL = "You failed to select an available option, please try again";
	private static final String MAIN_MENU = "A) List computers \nB) List companies \nC) Detailed computer view \nD) Create a computer \nE) Update a computer \nF) Delete a computer \nG) Change page size";

	/**
	 * @throws SQLException
	 */
	public void getComputerList() throws SQLException {
		Boolean innerLoop = true;
		while (innerLoop) {
			System.out.println(computerDBService.getComputerList(
					currentComputerPageIndex, pageSize));
			innerLoop = getComputerListMenu();
		}
	}

	/**
	 * @throws SQLException
	 */
	public void getComputerById() throws SQLException {
		Boolean innerLoop = true;
		while (innerLoop) {
			System.out.println("Id of computer to visualize?");
			try {
				final int id = sc.nextInt();
				sc.nextLine();
				System.out.println(computerDBService.getComputerById(id));
				innerLoop = getComputerMenu(id);
			} catch (InputMismatchException e) {
				System.out.println("Wrong kind of input!");
				sc.nextLine();
			}
		}
	}

	/**
	 * @param id
	 * @throws SQLException
	 */
	public void deleteComputer() throws SQLException {
		Boolean idValidation = true;
		int id=0;
		while (idValidation) {
			System.out
			.println("Enter id of computer would you like to delete?");
			System.out.println("Id of computer to update?");
			try {
				id = sc.nextInt();
				sc.nextLine();
				idValidation = false;
			} catch (InputMismatchException e) {
				System.out.println("Wrong kind of input!");
				sc.nextLine();
			}

			if (!(id <= 0 || id > computerDBService.count())) {
				System.out.println("Computer has been deleted.");
				computerDBService.remove(id);
			}else{
				System.out.println("Corresponding computer does not exist. Please enter valid ID.");
			}
		}
	}

	/**
	 * @throws SQLException
	 */
	public void updateComputer() throws SQLException {
		Boolean innerLoop = true;
		while (innerLoop) {
			System.out
			.println("A) List computers \nB) Id of computer to update");
			switch (sc.nextLine().toLowerCase()) {
			case "a":
				Boolean displayLoop = true;
				while (displayLoop) {
					System.out
					.println(computerDBService.getComputerList(
							currentComputerPageIndex, pageSize));
					displayLoop = getComputerListMenu();
				}
				break;
			case "b":
				Boolean innerInnerLoop = true;
				while (innerInnerLoop) {
					Boolean idValidation = true;
					int id = 0;
					String name = null;
					Timestamp introducedDate = null;
					Timestamp discontinuedDate = null;
					int cid = 0;
					while (idValidation) {
						System.out.println("Id of computer to update?");
						try {
							id = sc.nextInt();
							sc.nextLine();
							idValidation = false;
						} catch (InputMismatchException e) {
							System.out.println("Wrong kind of input!");
							sc.nextLine();
						}
					}
					if (!(id < 0 || id > computerDBService.count())) {
						Boolean creationLoop = true;
						Computer c = computerDBService.getComputerById(
								id);
						while (creationLoop) {
							System.out.println("Current name is : "
									+ c.getName());
							System.out.println("New name?");
							name = sc.nextLine();
							if (name.length() > 0) {
								creationLoop = false;
							} else {
								System.out
								.println("Please enter non null name");
							}

							creationLoop = true;
							while (creationLoop) {
								System.out
								.println("Current introduction date is : "
										+ c.getIntroduced());
								System.out
								.println("New introduction year? Format : YYYY-MM-DD");
								String introduced = sc.nextLine();
								try {
									introducedDate = Timestamp
											.valueOf(LocalDate
													.parse(introduced)
													.atStartOfDay());
									creationLoop = false;
								} catch (DateTimeParseException e) {
									System.out
									.println("Invalid date Format, please try again");
								}
							}
							creationLoop = true;
							while (creationLoop) {
								System.out
								.println("Current discontinuation date is : "
										+ c.getDiscontinued());
								System.out
								.println("New discontinuation year? Format : YYYY-MM-DD");
								String discontinued = sc.nextLine();
								try {
									discontinuedDate = Timestamp
											.valueOf(LocalDate.parse(
													discontinued)
													.atStartOfDay());
									creationLoop = false;
								} catch (DateTimeParseException e) {
									System.out
									.println("Invalid date Format, please try again");
								}
							}
							creationLoop = true;
							while (creationLoop) {
								System.out.println("Current CompanyId is "
										+ c.getCompanyId());
								System.out
								.println("A) Show company names B) Skip");
								switch (sc.nextLine().toLowerCase()) {
								case "a":
									getCompanyList();
									System.out
									.println("What is the company ID?");
									try {
										cid = sc.nextInt();
										sc.nextLine();
										creationLoop = false;
									} catch (InputMismatchException e) {
										System.out
										.println("Wrong kind of input!");
									}

									break;
								case "b":
									creationLoop = false;
									break;
								default:
									fail();
									break;
								}
							}

						}
						computerDBService.updateComputer(id, name,
								introducedDate, discontinuedDate, cid);
						innerInnerLoop = false;
						innerLoop = false;
					} else {
						System.out.println("Please select a valid ID");
					}
				}
				break;
			}
		}
	}

	/**
	 * @throws SQLException
	 */
	public void createComputer() throws SQLException {
		Boolean innerLoop = true;
		Timestamp discontinuedDate = null;
		Timestamp introducedDate = null;
		while (innerLoop) {
			System.out.println("What is the name of the computer?");
			String name = sc.nextLine();
			if (name.length() > 0) {
				innerLoop = false;
			}
			innerLoop = true;
			while (innerLoop) {
				System.out
				.println("What year was this computer introduced? Format : YYYY-MM-DD");
				String introduced = sc.nextLine();
				try {
					introducedDate = Timestamp.valueOf(LocalDate.parse(
							introduced).atStartOfDay());
					innerLoop = false;
				} catch (DateTimeParseException e) {
					System.out.println("Invalid date Format, please try again");
				}
			}
			innerLoop = true;
			while (innerLoop) {
				System.out
				.println("What year was this computer discontinued? Format : YYYY-MM-DD");
				String introduced = sc.nextLine();
				try {
					discontinuedDate = Timestamp.valueOf(LocalDate.parse(
							introduced).atStartOfDay());
					innerLoop = false;
				} catch (DateTimeParseException e) {
					System.out.println("Invalid date Format, please try again");
				}
			}
			innerLoop = true;
			int cid = 0;
			while (innerLoop) {
				System.out.println("What is the company id?");
				System.out.println("A) Show company names B) Skip");
				switch (sc.nextLine().toLowerCase()) {
				case "a":
					getCompanyList();
					System.out.println("What is the company ID?");
					try {
						cid = sc.nextInt();
						sc.nextLine();
						innerLoop = false;
					} catch (InputMismatchException e) {
						System.out.println("Wrong kind of input!");
					}

					break;
				case "b":
					innerLoop = false;
					break;
				default:
					fail();
					break;
				}
			}
			computerDBService.createComputer(name, introducedDate,discontinuedDate, cid);
		}
	}

	/**
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
	 * @return
	 * @throws SQLException
	 */
	private Boolean getComputerListMenu() throws SQLException {
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
	 * @throws SQLException
	 */
	public void getComputerCount() throws SQLException {
		System.out.println(computerDBService.count());
	}

	/**
	 * @throws SQLException
	 */
	public void getCompanyCount() throws SQLException {
		System.out.println(companyDBService.count());
	}

	/**
	 * @throws SQLException
	 */
	public void getCompanyList() throws SQLException {
		Boolean innerLoop = true;
		while (innerLoop) {
			System.out.println(companyDBService.getCompanyList(
					currentCompanyPageIndex, pageSize));
			innerLoop = getCompanyListMenu(currentCompanyPageIndex);
		}
	}

	/**
	 * @param index
	 * @return
	 * @throws SQLException
	 */
	private Boolean getCompanyListMenu(int index) throws SQLException {
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
	 * @throws SQLException
	 */
	public void changePageSize() throws SQLException {
		boolean innerLoop = true;
		while (innerLoop) {
			System.out.println("Current page size is " + pageSize
					+ ".\nChange page size to :");
			try {
				pageSize = sc.nextInt();
				sc.nextLine();
				System.out.println("Page size successfully changed to "
						+ pageSize);
				innerLoop = getPageSizeMenu();

			} catch (InputMismatchException e) {
				System.out.println("Wrong kind of input!");
				sc.nextLine();
				getPageSizeMenu();
			}
		}
	}

	/**
	 * @return
	 * @throws SQLException
	 */
	public Boolean getPageSizeMenu() throws SQLException {
		boolean innerLoop = true;
		boolean upperLoop = false;
		while (innerLoop) {
			System.out.println("A) Return to previous menu");
			System.out.println("B) Change page size");
			switch (sc.nextLine().toLowerCase()) {
			case "a":
				innerLoop = false;
				upperLoop = true;
				break;
			case "b":
				innerLoop = false;
				upperLoop = true;
				break;
			default:
				innerLoop = true;
				upperLoop = false;
			}
		}
		return upperLoop;
	}

	public void init() throws SQLException {
		computerDBService = new ComputerDBService();
		companyDBService= new CompanyDBService();
		computerCount = computerDBService.count();
		companyCount = companyDBService.count();
		System.out.println("Welcome to Computer-DB. There are currently "
				+ computerCount + " computers and " + companyCount
				+ " companies");
		sc = new Scanner(System.in);
		loop = true;
		mainMenu();
	}

	/**
	 * @throws SQLException
	 */
	public void mainMenu() throws SQLException {
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
	 * 
	 */
	public void fail() {
		System.out.println(FAIL);
	}

	/**
	 * @param args
	 * @throws SQLException
	 */
	public static void main(String[] args) throws SQLException {
		Client x = new Client();
		x.init();
	}

}
