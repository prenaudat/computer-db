import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.Properties;

public class Test {

	public String getPropValues() throws IOException {
		 
		String result = "";
		Properties prop = new Properties();
		String propFileName = "mysql.properties";
 
		InputStream inputStream = getClass().getClassLoader().getResourceAsStream(propFileName);
 
		if (inputStream != null) {
			prop.load(inputStream);
		} else {
			throw new FileNotFoundException("property file '" + propFileName + "' not found in the classpath");
		} 
		// get the property value and print it out
		String user = prop.getProperty("USER");
		String company1 = prop.getProperty("PASS");
		String company2 = prop.getProperty("JDBC_DRIVER");
		String company3 = prop.getProperty("DB_URL");
 
		result = "Company List = " + company1 + ", " + company2 + ", " + company3;
		System.out.println(result + "\nProgram Ran on " + " by user=" + user);
		return result;
	}
	
	public static void main(String[] args) throws IOException{
		new Test().getPropValues();
	}


}
