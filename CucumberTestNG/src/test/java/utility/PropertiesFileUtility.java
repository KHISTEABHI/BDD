package utility;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertiesFileUtility {

	static String filePath = "./src/test/resources/propertiesFiles/app.properties";

	/**
	 * Reads data from a properties file.
	 *
	 * @param filePath Path to the properties file.
	 * @return Properties object containing the key-value pairs from the file.
	 * @throws IOException If an I/O error occurs.
	 */
	public static Properties readPropertiesFile(String filePath) throws IOException {
		FileInputStream fis = null;
		Properties property = null;
		try {
			fis = new FileInputStream(new File(filePath));
			property = new Properties();
			property.load(fis);
		}catch(IOException ex) {
			ex.printStackTrace();
		}finally {
			fis.close();
		}
		return property;
	}

	
	public static String getValue(String key) {
		String value = null;
		try {
			value = readPropertiesFile(filePath).getProperty(key);
		}catch (IOException e) {
			e.printStackTrace();
		}
		return value;
	}
}
