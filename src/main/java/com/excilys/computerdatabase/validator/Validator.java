package com.excilys.computerdatabase.validator;

import java.util.Map;

import com.excilys.computerdatabase.exception.PersistenceException;
import com.excilys.computerdatabase.pagination.OrderBy;
import com.excilys.computerdatabase.pagination.Page;
import com.excilys.computerdatabase.pagination.Sort;

public class Validator {
	  private static final String REGEX_DELIMITER = "(\\.|-|\\/)";
	  private static final String REGEX_DATE_EN = "("
	      + "((\\d{4})" + REGEX_DELIMITER + "(0[13578]|10|12)" + REGEX_DELIMITER + "(0[1-9]|[12][0-9]|3[01]))"
	      + "|((\\d{4})" + REGEX_DELIMITER + "(0[469]|11)" + REGEX_DELIMITER + "([0][1-9]|[12][0-9]|30))"
	      + "|((\\d{4})" + REGEX_DELIMITER + "(02)" + REGEX_DELIMITER  + "(0[1-9]|1[0-9]|2[0-8]))"
	      + "|(([02468][048]00)" + REGEX_DELIMITER + "(02)" + REGEX_DELIMITER + "(29))"
	      + "|(([13579][26]00)" + REGEX_DELIMITER + "(02)" + REGEX_DELIMITER + "(29))"
	      + "|(([0-9][0-9][0][48])" + REGEX_DELIMITER + "(02)" + REGEX_DELIMITER + "(29))"
	      + "|(([0-9][0-9][2468][048])" + REGEX_DELIMITER + "(02)" + REGEX_DELIMITER + "(29))"
	      + "|(([0-9][0-9][13579][26])" + REGEX_DELIMITER + "(02)" + REGEX_DELIMITER + "(29))"
	      + ")";
	  private static final String REGEX_DATE_FR = "("
	      + "((0[1-9]|[12][0-9]|3[01])" + REGEX_DELIMITER + "(0[13578]|10|12)" + REGEX_DELIMITER + "(\\d{4}))"
	      + "|(([0][1-9]|[12][0-9]|30)" + REGEX_DELIMITER + "(0[469]|11)" + REGEX_DELIMITER + "(\\d{4}))"
	      + "|((0[1-9]|1[0-9]|2[0-8])" + REGEX_DELIMITER + "(02)" + REGEX_DELIMITER + "(\\d{4}))"
	      + "|((29)" + REGEX_DELIMITER + "(02)" + REGEX_DELIMITER + "([02468][048]00))"
	      + "|((29)" + REGEX_DELIMITER + "(02)" + REGEX_DELIMITER + "([13579][26]00))"
	      + "|((29)" + REGEX_DELIMITER + "(02)" + REGEX_DELIMITER + "([0-9][0-9][0][48]))"
	      + "|((29)" + REGEX_DELIMITER + "(02)" + REGEX_DELIMITER + "([0-9][0-9][2468][048]))"
	      + "|((29)" + REGEX_DELIMITER + "(02)" + REGEX_DELIMITER + "([0-9][0-9][13579][26]))"
	      + ")";
	  private static final String REGEX_NUMBER = "[+-]?\\d{1,19}";
	  

	public static Boolean isValidNumber(final String input) {
		if (input == null) {
			return false;
		}
		if (input.matches(REGEX_NUMBER) || input==null) {
			return true;
		} else {
			System.out.println("invalid number" + input);
			return false;
		}
	}

	public static void main(String[] args){
		System.out.println(REGEX_DATE_EN);
	}
	public static Boolean isValidDate(final String input) {
		if (input.length() == 10 && input.matches(REGEX_DATE_EN) && Integer.parseInt(input.split("-")[0])>1970) {
			return true;
		} else {
			return false;
		}
	}

	public static Boolean isValidString(final String input) {
		if(input != null && input.trim().length()>0){
			return true;
		} else {
			return false;
		}
	}

	public static int validateInt(final String input) {
		if (input != null && input.matches(REGEX_NUMBER) && input.length() > 0) {
			return Integer.parseInt(input);
			}
		else {
			throw new PersistenceException();
		}
	}
	public static String validateString(final String input) {
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
