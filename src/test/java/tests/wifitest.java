package tests;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import Base.BaseTest;
import Pages.HomePage;
import Pages.PreferencePage;
import Pages.WifiPage;
import io.qameta.allure.Description;
import utils.ExcelUtils;

public class wifitest extends BaseTest {
	HomePage homePage;
	PreferencePage preferencePage;
	WifiPage wifiPage;

	protected Logger log = LogManager.getLogger(this.getClass());

	@DataProvider(name = "wifiData")
	public Object[][] getData() {

		Object[][] data = ExcelUtils.getTestData("src/test/resources/testdata/WifiData.xlsx", "Sheet1");

		if (data == null || data.length == 0) {
			throw new RuntimeException("No test data found in Excel!");
		}

		return data;
	}

	@Test(dataProvider = "wifiData")
	@Description("Verify user can open Preference dependencies")
	public void wifisettingName(String wifiName) {

		homePage = new HomePage(driver);
		preferencePage = new PreferencePage(driver);
		wifiPage = new WifiPage(driver);

		homePage.clickOnPreference();
		preferencePage.clickOnPreferenceDependencies();
		wifiPage.clickOnCheckBox();
		wifiPage.clickOnWifiSetting();

		String actualTitle = wifiPage.getWifiSettingsTitle();
		wifiPage.assertEquals(actualTitle, "WiFi settings", "WiFi Settings Title");
		log.info("Running test with WiFi Name: " + wifiName);
		wifiPage.setWifiData(wifiName); // From Excel Sheet
		wifiPage.clickOnOkButton();
	}
}
