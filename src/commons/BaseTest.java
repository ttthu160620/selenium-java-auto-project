package commons;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTest {
	private WebDriver driver; 
	protected final Log log;
	
	protected BaseTest() {
		log = LogFactory.getLog(getClass());
	}
	
	public WebDriver getDriverInstance() {
		return driver;
	}
	
	protected WebDriver getBrowserDriver(String browserName) {
		if(browserName.equals("firefox")) {
			 WebDriverManager.firefoxdriver().setup();
			 driver = new FirefoxDriver();
		}
		
		else if (browserName.equals("edge")) {
			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver();
		}
		else if(browserName.equals("chrome")) {
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
		}
		else {
			throw new RuntimeException("Browser name invalid");
		}

		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.get(GlobalConstants.ADMIN_PAGE_URL);
		return driver;
	}
	
	protected int getRandomNumber() {
		Random ran = new Random();
		return ran.nextInt(999);
	}
	
	protected void sleepInSecond (long second) {
		try {
			Thread.sleep( second * 1000);
		}
		catch(InterruptedException e){
			e.printStackTrace();
		}
	}
}
