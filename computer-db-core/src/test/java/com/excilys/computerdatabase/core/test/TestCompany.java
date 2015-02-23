package com.excilys.computerdatabase.core.test;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import com.excilys.computerdatabase.core.model.Company;

/**
 * Company Test
 * @author excilys
 *
 */
public class TestCompany {

	Company company;

	/**
	 * Build a company
	 */
	@Before
	public void initialize() {

		company = new Company.Builder().id(15l).name("Test").build();

	}

	/**
	 * Testing id getter
	 */
	@Test
	public void testGetId() {

		long id = company.getId();

		assertEquals(15, id);

	}

	/**
	 * Testing name getter
	 */
	@Test
	public void testGetName() {

		String name = company.getName();

		assertEquals("Test", name);

	}

}