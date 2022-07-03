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
import com.obs.pages.ViewStockPage;

public class ViewStockTest extends BaseTest{
	
	LoginPage lpage;
	HomePage homePage;
	ViewStockPage viewStockPage;
	SoftAssert soft;

	@BeforeMethod
	public void setup() throws Exception {
		lpage = new LoginPage(driver);
		homePage = new HomePage(driver);
		PropertyDataHandler prop = new PropertyDataHandler();
		Properties allProp = prop.readPropertiesFile("config.properties");
		homePage = lpage.login(allProp.getProperty("username"), allProp.getProperty("password"));
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(50));
		viewStockPage = homePage.gotoStockPage();
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(50));
		
	}
	
	@Test(priority = 1, enabled = true)
	public void validateViewStockFields() throws Exception {
		soft = new SoftAssert();
		soft.assertTrue(viewStockPage.isHeadingDisplayed(), "Heading not displayed");
		soft.assertTrue(viewStockPage.isActionDropDownIconDisplayed(), "Action dropdown icon is not displayed");
		soft.assertTrue(viewStockPage.isSearchFieldDisplayed(), "Search Field is not displayed");
		soft.assertTrue(viewStockPage.isTableHeadingDisplayed(), "Table Heading is not displayed");
		soft.assertAll();
	}
	@Test(priority = 2, enabled = true)
	public void verifyDisplayAndValidationOfSubList() throws Exception {
		soft = new SoftAssert();
		soft.assertTrue(viewStockPage.isSublistDisplayed(), "SubList is not displayed");
		soft.assertTrue(viewStockPage.isAddProductDisplayed(), "Add Product is not displayed");
		soft.assertTrue(viewStockPage.isPrintBarcodeDisplayed(), "Print Barcode is not displayed");
		soft.assertTrue(viewStockPage.isExportToExcelDisplayed(), "Export to Excel is not displayed");
		soft.assertTrue(viewStockPage.isExportToPdfDisplayed(), "Export to Pdf is not displayed");
		soft.assertTrue(viewStockPage.isImportProductsDisplayed(), "Import Products is not displayed");
		soft.assertTrue(viewStockPage.isDeleteProductDisplayed(), "Delete Products is not displayed");
		soft.assertAll();
	}
	@Test(priority = 3, enabled = true)
	public void verifyAddAndImportProductRedirection() throws Exception {
		soft = new SoftAssert();
		soft.assertTrue(viewStockPage.isAddProductPageDisplayed(), "AddProduct page is not displayed");
		subListDisplay();
		soft.assertTrue(viewStockPage.isImportProductPageDisplayed(), "Import page is not displayed");
		subListDisplay();
		soft.assertAll();
	}
	@AfterMethod
	public void logout() {
		lpage.logout();
	}
	
	public void subListDisplay() {
		viewStockPage.goBack();
		viewStockPage.clickOnActionDropDownIcon();
	}
}
