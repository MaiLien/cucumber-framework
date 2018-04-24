package managers;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

import enums.DriverType;

public class WebDriverManager {
	private WebDriver driver;
	private static DriverType driverType;

	public WebDriverManager() {
		driverType = ConfigurationManager.getInstance().getBrowser();
	}

	public WebDriver getDriver() {
		if (driver == null) {
			driver = createDriver();
		}
		return driver;
	}

	private WebDriver createDriver() {
		switch (driverType) {
		case FIREFOX:
			System.setProperty("webdriver.gecko.driver", ConfigurationManager.getInstance().getGeckoPath());
			driver = new FirefoxDriver();
			break;
		case CHROME:
			System.setProperty("webdriver.chrome.driver", "");
			driver = new ChromeDriver();
			break;
		case INTERNET_EXPLORER:
			driver = new InternetExplorerDriver();
			break;
		}

		driver.manage().timeouts().implicitlyWait(ConfigurationManager.getInstance().getImplicitWaitTime(), TimeUnit.SECONDS);
		return driver;
	}

	public void closeDriver() {
		driver.close();
		driver.quit();
	}

}