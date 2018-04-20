package dataProvider;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class ConfigFileReader {

	private static Properties properties = new Properties();;
	private final String propertyFilePath = "./configs/Configuation.properties";

	private static ConfigFileReader configProperties = new ConfigFileReader();

	private ConfigFileReader() {
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

	public static ConfigFileReader getInstance() {
		return configProperties;
	}

	public Object getProperty(String key) {
		Object driverPath = properties.getProperty(key);
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

}
