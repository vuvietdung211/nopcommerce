package commons;

import java.util.concurrent.TimeUnit;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.testng.Assert;
import org.testng.Reporter;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTest {
	protected WebDriver driver;
	protected final Log log;
	protected BaseTest() {
		log = LogFactory.getLog(getClass());
	}

	protected WebDriver getBrowserDriver(String browserName) {
		BrowserList browserList =  BrowserList.valueOf(browserName.toUpperCase());
		switch (browserList) {
		case FIREFOX:
			FirefoxOptions ffOptions = new FirefoxOptions();
			ffOptions.addArguments("-private");
			driver = WebDriverManager.firefoxdriver().capabilities(ffOptions).create();
			break;

		case FIREFOX_HEADLESS:
			FirefoxOptions ffHeadOptions = new FirefoxOptions();
			ffHeadOptions.addArguments("-headless");
			ffHeadOptions.addArguments("-window-size=1920x1080");
			driver = WebDriverManager.firefoxdriver().capabilities(ffHeadOptions).create();
			break;

		case OPERA:
			driver = WebDriverManager.operadriver().create();
			break;

		case CHROME:
			WebDriverManager.chromedriver().create();
			driver = new ChromeDriver();
			break;

		case CHROME_HEADLESS:
			ChromeOptions chromeHeadOptions = new ChromeOptions();
			chromeHeadOptions.addArguments("--headless");
			chromeHeadOptions.addArguments("-window-size=1920x1080");
			driver = WebDriverManager.chromedriver().capabilities(chromeHeadOptions).create();
			break;

		case EDGE:
			driver = WebDriverManager.edgedriver().create();
			break;

		case SAFARI:
			driver = WebDriverManager.safaridriver().create();
			// driver = new SafariDriver();
			break;

		case IE:
			driver = WebDriverManager.iedriver().arch32().create();
			break;
		case COC_COC:
			WebDriverManager.chromedriver().driverVersion("107.0.5304.62").setup();
			ChromeOptions options = new ChromeOptions();
			options.setBinary("String Path");
			driver = new ChromeDriver(options);
			break;
		default:
			throw new RuntimeException("Browser name is invalid");
		}
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.get(GlobalConstants.DEV_USER_URL);
		return driver;
	}
	
	private boolean checkEquals(Object actual, Object expected) {
		boolean pass = true;
		try {
			Assert.assertEquals(actual, expected);
			log.info(" -------------------------- PASSED -------------------------- ");
		} catch (Throwable e) {
			pass = false;
			log.info(" -------------------------- FAILED -------------------------- ");
			/*
			 * VerificationFailures.getFailures().addFailureForTest(Reporter.
			 * getCurrentTestResult(), e); Reporter.getCurrentTestResult().setThrowable(e);
			 */
		}
		return pass;
	}

	protected boolean verifyEquals(Object actual, Object expected) {
		return checkEquals(actual, expected);
	}
}
