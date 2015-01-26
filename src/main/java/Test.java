
public class Test {
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
	  public static void main(String[] args){
		  System.out.println(REGEX_DATE_FR);
		  String string1 = "1970-12-11";
		  String string2 = "12-12-1999";
		  System.out.println(string1.matches(REGEX_DATE_EN));
		  System.out.println(string2.matches(REGEX_DATE_EN));

	  }
}
