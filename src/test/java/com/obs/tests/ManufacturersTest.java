package com.obs.tests;

import java.time.Duration;
import java.util.Properties;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.obs.datahandler.PropertyDataHandler;
import com.obs.pages.HomePage;
import com.obs.pages.LoginPage;
import com.obs.pages.ManufacturersPage;

public class ManufacturersTest extends BaseTest{
	
	LoginPage lpage;
	HomePage homePage;
	ManufacturersPage manufacturersPage;
	SoftAssert soft;

	@BeforeMethod
	public void setup() throws Exception {
		lpage = new LoginPage(driver);
		homePage = new HomePage(driver);
		PropertyDataHandler prop = new PropertyDataHandler();
		Properties allProp = prop.readPropertiesFile("config.properties");
		homePage = lpage.login(allProp.getProperty("username"), allProp.getProperty("password"));
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(50));
		manufacturersPage = homePage.gotoManufacturersPage();
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(50));
		
	}
	
	/*
	 * 
	 * Validate Manufacturers Page fields
	 */
	@Test(priority = 1, enabled = true)
	public void validateManufacturerPageFields() throws Exception {
		soft = new SoftAssert();
		soft.assertTrue(manufacturersPage.isManufacturerAddBtnLinkDisplayed(), "Manufacturer Add Link Button is not displayed");
		soft.assertTrue(manufacturersPage.isManufacturerHeadingDisplayed(), "Manufacturer Heading is not displayed");
		soft.assertAll();
	}
	
	/*
	 * verify edit manufacture working properly
	 */
	@Test(priority = 2, enabled = false)
	public void verifyEditManufacturer() throws Exception {
		soft = new SoftAssert();
		manufacturersPage.editManufacturerLinkBtn();
		soft.assertTrue(manufacturersPage.editManufacturer("Edited Content"), "Manufacturer not edited");
		soft.assertAll();
	}
	
	/*
	 * Verify delete manufacture delete the manufacture
	 */
	@Test(priority = 3, enabled = false)
	public void verifyDeleteManufacturer() throws Exception {
		soft = new SoftAssert();
		soft.assertTrue(manufacturersPage.deleteManufacturer(), "Manufacturer not deleted");
		soft.assertAll();
	}
	
	/*
	 * Verify Search Manufacturer shows correct result , for valid ->valid result, and for invalid-> empty result
	 */
	@Test(priority = 4, enabled = false)
	public void verifySearchManufacturer() throws Exception{
		soft = new SoftAssert();
		manufacturersPage.clickOnSearchField();
		soft.assertTrue(manufacturersPage.searchManufacturer("second","valid"), "Valid Search is not accurate");
		soft.assertTrue(manufacturersPage.searchManufacturer("qwerty","invalid"), "Inalid Search is not accurate");
		soft.assertAll();
	}
	@AfterMethod
	public void logout() {
		lpage.logout();
	}

}
