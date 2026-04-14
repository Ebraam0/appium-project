package Pages;

import org.openqa.selenium.By;

import Base.BasePage;
import io.appium.java_client.android.AndroidDriver;
import io.qameta.allure.Step;

public class WifiPage extends BasePage {

	public WifiPage(AndroidDriver driver) {
		super(driver);
	}

	// 🔹 Locators
	private By wifiCheckBox = By.id("android:id/checkbox");
	private By wifiSettingBtn = By.xpath("//android.widget.TextView[contains(@text, 'WiFi settings')]");
	private By wifiFiled = By.id("android:id/edit");
	private By clickonBtn = By.id("android:id/button1");
	private By wifiSettingMssg = By.id("android:id/alertTitle");

	// 🔹 Actions

	@Step("User clicks on WiFi checkbox")
	public void clickOnCheckBox() {
		click(wifiCheckBox, "Wifi Checkbox");
	}

	@Step("User clicks on WiFi settings")
	public void clickOnWifiSetting() {
		click(wifiSettingBtn, "WiFi Settings Button");
	}

	@Step("User enters WiFi name: {wifiName}")
	public void setWifiData(String wifiName) {
		setText(wifiFiled, wifiName, "WiFi Data Field");
	}

	@Step("User clicks OK button")
	public void clickOnOkButton() {
		click(clickonBtn, "OK Button");
	}

	@Step("Get WiFi settings title")
	public String getWifiSettingsTitle() {
		return getText(wifiSettingMssg, "WiFi Settings Title");
	}

}