package Base;

import java.net.URL;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import io.appium.java_client.android.AndroidDriver;

public class BaseTest {

	public AndroidDriver driver;
	private static final Logger log = LogManager.getLogger(BaseTest.class);

	@BeforeMethod
	public void setup() throws Exception {

		DesiredCapabilities caps = new DesiredCapabilities();

		caps.setCapability("platformName", "Android");
		caps.setCapability("appium:deviceName", "A920");
		caps.setCapability("appium:udid", "0822211910");
		caps.setCapability("appium:automationName", "UiAutomator2");
		caps.setCapability("appium:appPackage", "io.appium.android.apis");
		caps.setCapability("appium:appActivity", ".ApiDemos");

		driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), caps);

		log.info("App Launched!");
	}

	@AfterMethod
	public void tearDown(ITestResult result) {

		try {
			if (driver != null) {
				driver.quit();
				log.info("Driver Closed");
			}
		} catch (Exception e) {
			log.warn("Driver already closed");
		}
	}
}