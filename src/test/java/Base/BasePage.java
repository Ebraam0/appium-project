package Base;

import java.time.Duration;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import io.appium.java_client.android.AndroidDriver;
import io.qameta.allure.Attachment;
import io.qameta.allure.Step;

public class BasePage {

	protected AndroidDriver driver;
	protected WebDriverWait wait;
	protected Logger log = LogManager.getLogger(this.getClass());

	public BasePage(AndroidDriver driver) {
		this.driver = driver;
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	}

	// 🔥 Core Action Wrapper
	protected String performAction(String actionName, Runnable action) {

		try {
			log.info("Trying to " + actionName);

			action.run();

			log.info(actionName + " DONE");
			takeScreenshot();

			return "SUCCESS";

		} catch (Exception e) {
			log.error("Failed in: " + actionName + " - " + e.getMessage());
			takeScreenshot();

			return "FAILED";
		}
	}

	// 🔹 Click
	public String click(By locator, String elementName) {

		return performAction("Click on " + elementName, () -> {
			wait.until(ExpectedConditions.visibilityOfElementLocated(locator)).click();
		});
	}

	// 🔹 Set Text
	public String setText(By locator, String text, String elementName) {

		return performAction("Set text in " + elementName, () -> {
			wait.until(ExpectedConditions.visibilityOfElementLocated(locator)).sendKeys(text);
		});
	}

	// 🔹 Get Text
	public String getText(By locator, String elementName) {

		try {
			String text = wait.until(ExpectedConditions.visibilityOfElementLocated(locator)).getText();

			log.info("Text from " + elementName + " = " + text);

			takeScreenshot();

			return text;

		} catch (Exception e) {
			log.error("Failed to get text from " + elementName);
			takeScreenshot();
			return null;
		}
	}

	// 🔹 Assertions
	@Step("Verify Text → {elementName} | Expected: {expected} | Actual: {actual}")
	public void assertEquals(String actual, String expected, String elementName) {

		log.info("Verifying: " + elementName);
		log.info("Expected: " + expected + " | Actual: " + actual);

		try {
			Assert.assertEquals(actual, expected,
					elementName + " mismatch → Expected: " + expected + " | Actual: " + actual);

			log.info("Assertion PASSED for: " + elementName);

		} catch (AssertionError e) {

			log.error("Assertion FAILED for: " + elementName);
			takeScreenshot();

			throw e;
		}
	}

	// 🔹 Helpers
	public boolean isDisplayed(By locator, String elementName) {
		try {
			return wait.until(ExpectedConditions.visibilityOfElementLocated(locator)).isDisplayed();
		} catch (Exception e) {
			log.warn("Element not displayed: " + elementName);
			return false;
		}
	}

	public boolean isSelected(By locator, String elementName) {
		try {
			return driver.findElement(locator).isSelected();
		} catch (Exception e) {
			log.warn("Element not selected: " + elementName);
			return false;
		}
	}

	// 🔹 Scroll / Swipe
	public String scrollDown() {
		return performAction("Scroll Down", () -> {
			driver.executeScript("mobile: scroll", Map.of("direction", "down"));
		});
	}

	public String scrollUp() {
		return performAction("Scroll Up", () -> {
			driver.executeScript("mobile: scroll", Map.of("direction", "up"));
		});
	}

	public String swipeLeft() {
		return performAction("Swipe Left", () -> {
			driver.executeScript("mobile: swipe", Map.of("direction", "left"));
		});
	}

	public String swipeRight() {
		return performAction("Swipe Right", () -> {
			driver.executeScript("mobile: swipe", Map.of("direction", "right"));
		});
	}

	// 🔹 Screenshot
	@Attachment(value = "Screenshot", type = "image/png")
	public byte[] takeScreenshot() {

		try {
			if (driver == null) {
				log.warn("Driver is null, screenshot skipped");
				return new byte[0];
			}

			return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);

		} catch (Exception e) {
			log.warn("Screenshot failed (session might be dead): " + e.getMessage());
			return new byte[0];
		}
	}
}