package managers;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

import enums.DriverType;

public class ConfigurationManager {

	private static Properties properties = new Properties();;
	private final String propertyFilePath = "./configs/Configuation.properties";

	private static ConfigurationManager configProperties = new ConfigurationManager();

	private ConfigurationManager() {
		BufferedReader reader;
		try {
			reader = new BufferedReader(new FileReader(propertyFilePath));
			try {
				properties.load(reader);
				reader.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			throw new RuntimeException("Configuration.properties not found at " + propertyFilePath);
		}
	}

	public static ConfigurationManager getInstance() {
		return configProperties;
	}

	public String getProperty(String key) {
		String driverPath = properties.getProperty(key);
		if (driverPath != null)
			return driverPath;
		else
			throw new RuntimeException(key + " not specified in the Configuration.properties file.");
	}

	public String getGeckoPath() {
		return properties.getProperty("geckoPath");
	}

	public Long getImplicitWaitTime() {
		return Long.parseLong(properties.getProperty("implicitWaitTime"));
	}

	public DriverType getBrowser() {
		String browserName = getProperty("browser");
		if (browserName == null || browserName.equals("firefox"))
			return DriverType.FIREFOX;
		else if (browserName.equalsIgnoreCase("chrome"))
			return DriverType.CHROME;
		else if (browserName.equals("iexplorer"))
			return DriverType.INTERNET_EXPLORER;
		else
			throw new RuntimeException(
					"Can not found " + browserName + " property.");
	}

}
