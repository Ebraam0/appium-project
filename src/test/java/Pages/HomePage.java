package Pages;

import org.openqa.selenium.By;

import Base.BasePage;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.qameta.allure.Step;

public class HomePage extends BasePage {

	public HomePage(AndroidDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}

	// 🔹 Locators
	private By preferenceBtn = AppiumBy.accessibilityId("Preference");
	private By preferenceBtn2 = By.xpath("//android.widget.TextView [contains(@text,'Preference')]");

	// 🔹 Actions
	@Step("User opens Preference screen")
	public void clickOnPreference() {
		click(preferenceBtn, "Preference Button");
	}

}
