package com.excilys.computerdatabase.core.test;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import com.excilys.computerdatabase.core.model.Company;

public class TestCompany {

	Company company;

	@Before
	public void initialize() {

		company = new Company.Builder().id(15).name("Test").build();

	}

	@Test
	public void testGetId() {

		long id = company.getId();

		assertEquals(15, id);

	}

	@Test
	public void testGetName() {

		String name = company.getName();

		assertEquals("Test", name);

	}

}