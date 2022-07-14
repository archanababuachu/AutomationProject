package com.obs.tests;

import java.time.Duration;
import java.util.Properties;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.obs.datahandler.ExcelDataHandler;
import com.obs.datahandler.PropertyDataHandler;
import com.obs.pages.HomePage;
import com.obs.pages.LoginPage;
import com.obs.pages.StockModelsPage;

public class StockModelsTest extends BaseTest{

	LoginPage lpage;
	HomePage homePage;
	StockModelsPage stockModelPage;
	SoftAssert soft;
	ExcelDataHandler excelObj;
	
	@BeforeMethod
	public void setup() throws Exception {
		lpage = new LoginPage(driver);
		homePage = new HomePage(driver);
		PropertyDataHandler prop = new PropertyDataHandler();
		Properties allProp = prop.readPropertiesFile("config.properties");
		homePage = lpage.login(allProp.getProperty("username"), allProp.getProperty("password"));
		stockModelPage = homePage.gotoStockModelsPage();
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(50));
	}
	
	/*
	 * Verify all the fields are displayed in the Models Page(heading, Add Model Btn
	 */
	@Test(priority = 1, enabled = true, groups = {"SanityTest"})
	public void VerifyFieldsAreDisplayed() throws Exception {
		soft = new SoftAssert();
		soft.assertTrue(stockModelPage.isHeadingDisplayed(), "Heading is not displayed");
		soft.assertTrue(stockModelPage.isAddModelButtonDisplayed(), "Heading is not displayed");
		soft.assertAll();
	}
	
	/*
	 * Verify the model is adding successfully
	 */
	@Test(priority = 2, enabled = false)
	public void addModel() throws Exception {
		soft = new SoftAssert();
		stockModelPage.clickOnAddModelRedirectBtn();
		excelObj = new ExcelDataHandler("ProjectExcel.xlsx", "Model");
		String name = excelObj.getCellDataString(1, 0);
		String manu = excelObj.getCellDataString(1, 1);
		stockModelPage.sendModelName(name);
		stockModelPage.sendModelManufacturer(manu);
		soft.assertTrue(stockModelPage.clickOnAddModelBtn(), " Model not added");
		soft.assertAll();
	}
	
	/*
	 * Verify that the add model close button closes the add model section
	 */
	@Test(priority = 3, enabled = false)
	public void verifyAddModelCloseButton() throws Exception {
		soft = new SoftAssert();
		stockModelPage.clickOnAddModelRedirectBtn();
		stockModelPage.clickOnAddModelCloseBtn();
		soft.assertFalse(stockModelPage.isAddModelCloseBtnDisplayed(), "Add Model page is not closed");
		soft.assertAll();
	}
	
	/*
	 *Verify while clicking on 'Go Back' button it closes the 'Add Model' page
	 */
	@Test(priority = 4, enabled =false)
	public void verifyAddModelGoBackButton() throws Exception {
		soft = new SoftAssert();
		stockModelPage.clickOnAddModelRedirectBtn();
		stockModelPage.clickOnAddModelGoBackButton();
		soft.assertFalse(stockModelPage.isAddModelCloseBtnDisplayed(), "Add Model page is not closed");
		soft.assertAll();
	}
	
	/*
	 * Verify edit model , edit the model properly
	 */
	@Test(priority = 5, enabled = false)
	public void editModel() throws Exception {
		soft = new SoftAssert();
		stockModelPage.clickOnEditModelIcon();
		stockModelPage.sendModelNameDirect("Edit Data");
		soft.assertTrue(stockModelPage.clickOnEditModelOkBtn(), "Model Not edited");
		soft.assertAll();
	}
	
	/*
	 * Verify delete model , deletes the model
	 */
	@Test(priority = 6, enabled = false)           
	public void verifyDeleteModel() throws Exception {
		soft = new SoftAssert();
		soft.assertTrue(stockModelPage.clickOnDeleteModelIcon(), "Model not deleted");
		soft.assertAll();
	}
	/*
	 * Verify search model
	 */
	@Test(priority = 7, enabled = false)
	public void verifyModelSearch() {
		soft = new SoftAssert();
		soft.assertTrue(stockModelPage.verifySearchData("model1"), "Search result is not accurate");
		
	}
	
	@AfterMethod
	public void logout() {
		lpage.logout();
	}
}
