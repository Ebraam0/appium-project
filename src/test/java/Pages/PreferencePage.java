package Pages;

import org.openqa.selenium.By;

import Base.BasePage;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.qameta.allure.Step;

public class PreferencePage extends BasePage {

	public PreferencePage(AndroidDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}

	// 🔹 Locators
	private By preferenceDependenciesBtn = AppiumBy.accessibilityId("3. Preference dependencies");

	// 🔹 Actions
	@Step("User opens Preference dependencies")
	public void clickOnPreferenceDependencies() {

		click(preferenceDependenciesBtn, "Preference Dependency Button");
	}

}
