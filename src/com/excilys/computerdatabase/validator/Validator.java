package com.excilys.computerdatabase.validator;

public class Validator {
	private static final String NUMBER_REGEX = "^[0-9]*$";
	private static final String DATE_REGEX = "^((19|20)\\d\\d)-(0?[1-9]|1[012])-(0?[1-9]|[12][0-9]|3[01])$";
	private static final String DAT_REGEX = "(((0[1-9]|[12][0-9]|3[01])([/])(0[13578]|10|12)([/])(\\d{4}))|(([0][1-9]|[12][0-9]|30)([/])(0[469]|11)([/])(\\d{4}))|((0[1-9]|1[0-9]|2[0-8])([/])(02)([/])(\\d{4}))|((29)(\\.|-|\\/)(02)([/])([02468][048]00))|((29)([/])(02)([/])([13579][26]00))|((29)([/])(02)([/])([0-9][0-9][0][48]))|((29)([/])(02)([/])([0-9][0-9][2468][048]))|((29)([/])(02)([/])([0-9][0-9][13579][26])))";

	public static Boolean isValidNumber(final String input) {
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
		if (input.length() > 0) {
			return true;
		} else {
			System.out.println("invalid String");
			return false;
		}
	}
}
