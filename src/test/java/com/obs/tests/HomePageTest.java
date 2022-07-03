package com.obs.tests;

import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.obs.datahandler.PropertyDataHandler;
import com.obs.pages.HomePage;
import com.obs.pages.LoginPage;

public class HomePageTest extends BaseTest{
	
	LoginPage lpage;
	HomePage homePage;
	SoftAssert soft;
	
	@BeforeMethod
	public void setup() throws Exception {
		lpage = new LoginPage(driver);
		homePage = new HomePage(driver);
		PropertyDataHandler prop = new PropertyDataHandler();
		Properties allProp = prop.readPropertiesFile("config.properties");
		homePage = lpage.login(allProp.getProperty("username"), allProp.getProperty("password"));
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(50));
	}
	
	@Test(priority = 1, enabled = false)
	public void validateHomePage() throws Exception {
		soft = new SoftAssert();
		soft.assertTrue(homePage.isHambergerButtonDisplayed(), "Hamberger Button is not displayed");
		soft.assertTrue(homePage.isStockTabVisible(), "Stock/Inventory tab is not displayed");
		soft.assertAll();
	}
	
	@Test(priority = 2, enabled = false)
	public void verifySearchWithInvalidData() throws Exception { //bug found
		soft = new SoftAssert();
		homePage.performSearchOperation("zzz");
		soft.assertTrue(homePage.isSearchPageDisplayed(), "Search Page is not displayed");
		soft.assertFalse(homePage.checkSearchContent("zzz","invalid"), "Search with invalid data showing result");
		soft.assertAll();
	}
	
	@Test(priority = 3, enabled = false)    //--site defect=> pagination next is always enabled
	public void verifySearchWithValidData() throws Exception {
		soft = new SoftAssert();
		boolean isPaginationNextEnabled = false;
		boolean isFound = false;
		homePage.performSearchOperation("aaa");
		soft.assertTrue(homePage.isSearchPageDisplayed(), "Search Page is not displayed");
		do {
			System.out.println("==============================="+isPaginationNextEnabled);
			isPaginationNextEnabled = homePage.isPaginationNextEnabled();
			System.out.println("==============================="+isPaginationNextEnabled);
			isFound = homePage.checkSearchContent("aaa","valid");
			if(isFound) {
				System.out.println("=================founded");
				break;
			}
			System.out.println("inside do while");
			if(isPaginationNextEnabled) {
				homePage.clickOnPaginationNext();
			}
		}while(isPaginationNextEnabled);
		soft.assertTrue(isFound, "Search with valid data- not showing list with serach content");
	}
	
	@Test(priority = 4, enabled = true)
	public void verifyStockSublist() throws Exception { //if only executed this test all asertion passed, otherwise only first assertion is passed
		soft = new SoftAssert();
		homePage.clickOnStockTab();
		soft.assertTrue(homePage.isViewStockDisplayed(), "View Stock is not displayed");
		soft.assertTrue(homePage.isAddStockDisplayed(), "Add Stock is not displayed");
		soft.assertTrue(homePage.isSuppliersDisplayed(), "Supplier is not displayed");
		soft.assertTrue(homePage.isManufacturesDisplayed(), "Manufacturers is not displayed");
		soft.assertTrue(homePage.isModelsDisplayed(), "Models is not displayed");
		soft.assertAll();
	}
	
	@AfterMethod
	public void logout() {
		lpage.logout();
	}

}
