package com.excilys.computerdatabase.validator;

import java.util.Map;

import com.excilys.computerdatabase.exception.PersistenceException;
import com.excilys.computerdatabase.pagination.OrderBy;
import com.excilys.computerdatabase.pagination.Page;
import com.excilys.computerdatabase.pagination.Sort;

public class Validator {
	private static final String NUMBER_REGEX = "^[0-9]*$";
	private static final String DATE_REGEX = "^((19|20)\\d\\d)-(0?[1-9]|1[012])-(0?[1-9]|[12][0-9]|3[01])$";
	private static final String DAT_REGEX = "(((0[1-9]|[12][0-9]|3[01])([/])(0[13578]|10|12)([/])(\\d{4}))|(([0][1-9]|[12][0-9]|30)([/])(0[469]|11)([/])(\\d{4}))|((0[1-9]|1[0-9]|2[0-8])([/])(02)([/])(\\d{4}))|((29)(\\.|-|\\/)(02)([/])([02468][048]00))|((29)([/])(02)([/])([13579][26]00))|((29)([/])(02)([/])([0-9][0-9][0][48]))|((29)([/])(02)([/])([0-9][0-9][2468][048]))|((29)([/])(02)([/])([0-9][0-9][13579][26])))";

	public static Boolean isValidNumber(final String input) {
		if (input == null) {
			return false;
		}
		if (input.matches(NUMBER_REGEX)) {
			return true;
		} else {
			System.out.println("invalid number");
			return false;
		}
	}

	public static Boolean isValidDate(final String input) {
		if (input.length() == 10 && input.matches(DATE_REGEX)) {
			return true;
		} else {
			System.out.println("invalid date");
			return false;
		}
	}

	public static Boolean isValidDat(final String input) {
		if (input.length() == 10 && input.matches(DAT_REGEX)) {
			return true;
		} else {
			return false;
		}
	}

	public static Boolean isValidString(final String input) {
		if (input.length() > 0 && input != null) {
			return true;
		} else {
			return false;
		}
	}

	public static int validateInt(final String input) {
		if (input != null) {
			if (input.matches(NUMBER_REGEX) && input.length() > 0) {
				return Integer.parseInt(input);
			} else {
				throw new PersistenceException();
			}
		} else {
			throw new PersistenceException();
		}
	}
	public static String validateSring(final String input) {
		if (input != null) {
			if(input.length()>0){
				return input;
			}
			else{
				throw new PersistenceException();
			}
		} else {
			throw new PersistenceException();
		}
	}

	public static Page validateParameterList(Map<String, String[]> paramList){
		Page page = new Page();
		if(paramList.containsKey("size")){
			page.setSize(Validator.validateInt(paramList.get("size")[0]));
		}		
		if(paramList.containsKey("page")){
			page.setPageNumber(Validator.validateInt(paramList.get("page")[0]));
		}		
		if(paramList.containsKey("sort")){
			page.setSort(Sort.valueOf(paramList.get("sort")[0].toUpperCase()));
		}
		if(paramList.containsKey("order")){
			page.setOrderBy(OrderBy.valueOf(paramList.get("order")[0].toUpperCase()));
		}
		if(paramList.containsKey("query")){
			page.setQuery(paramList.get("query")[0]);
		}
		else{
			page.setQuery("");
		}
		return page;
	}
}
