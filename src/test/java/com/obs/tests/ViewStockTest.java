package com.obs.tests;

import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.obs.datahandler.ExcelDataHandler;
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
	
	@Test(priority = 1, enabled = false, groups = {"SanityTest"})
	public void validateViewStockFields() throws Exception {
		soft = new SoftAssert();
		soft.assertTrue(viewStockPage.isHeadingDisplayed(), "Heading not displayed");
		soft.assertTrue(viewStockPage.isActionDropDownIconDisplayed(), "Action dropdown icon is not displayed");
		soft.assertTrue(viewStockPage.isSearchFieldDisplayed(), "Search Field is not displayed");
		soft.assertTrue(viewStockPage.isTableHeadingDisplayed(), "Table Heading is not displayed");
		soft.assertAll();
	}
	
	/*
	 * Verify while clicking on any inventory in the list, the details of the corresponding inventory is listed with barcode, and inventory
	 */
	@Test(priority = 2, enabled = true)
	public void verifyClickOnInventoryShowDetails() throws Exception {  //===========pending
		soft = new SoftAssert();
		soft.assertTrue(viewStockPage.clickOnInventoryList(), "Inventory details is not shown with barcode");
	}
	@Test(priority = 3, enabled = false)
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
	/*
	 * Verify the page is redirecting to corresponding pages as we click on each sublist(Add Product, Import Product)
	 */
	@Test(priority = 4, enabled = false)
	public void verifyAddAndImportProductRedirection() throws Exception {
		soft = new SoftAssert();
		soft.assertTrue(viewStockPage.isSublistDisplayed(), "SubList is not displayed");
		soft.assertTrue(viewStockPage.isAddProductPageDisplayed(), "AddProduct page is not displayed");
		subListDisplay();
		soft.assertTrue(viewStockPage.isImportProductPageDisplayed(), "Import page is not displayed");
		subListDisplay();
		soft.assertAll();
	}
	/*
	 * Verify while clicking Print,Export shows an error message to Select Products 
	 */
	@Test(priority = 5, enabled = false)
	public void verifyErrorOnClickOfPrintAndExport() throws Exception {
		soft = new SoftAssert();
		viewStockPage.clickOnActionDropDownIcon();
		viewStockPage.clickOnExportProduct();
		soft.assertTrue(viewStockPage.isExpotShowsErrorWithoutProduct(), "Expot to Excel did not show error msg without product");
		viewStockPage.goBack();
		viewStockPage.clickOnActionDropDownIcon();
		viewStockPage.clickOnExportToPdf();
		soft.assertTrue(viewStockPage.isExpotShowsErrorWithoutProduct(), "Expot to Pdf did not show error msg without product");
		soft.assertAll();
	}
	/*
	 * Verify While clicking on 'Delete Products' shows a confirm alert, and if confirmed check weather an error message is
	 * --- displayed to select product and the alert is dissmissed while clicking No
	 */
	@Test(priority = 6, enabled = false)  
	public void verifyDeleteProductShowsAlertAndErrorMsg() throws Exception {
		soft = new SoftAssert();
		viewStockPage.clickOnActionDropDownIcon();
		viewStockPage.clickOnDeleteProduct();
		viewStockPage.clickOnDeleteNo();
		soft.assertFalse(viewStockPage.isDeleteConfirmDisplayed(), "Delete Dismiss is not working");
		viewStockPage.clickOnDeleteProduct();
		viewStockPage.clickOnDeleteConfirm();
		soft.assertTrue(viewStockPage.isExpotShowsErrorWithoutProduct(), "Delete Product did not show error msg without product");
		soft.assertAll();
	}
	/**
	 * Verify search with invalid data shows empty result, and valid data shows data according to the search
	 * @throws Exception 
	 */
	@Test(priority = 7, enabled = false)
	public void inventorySearchValidAndInvalid() throws Exception {  
		soft = new SoftAssert();
		viewStockPage.clickOnSearchField();
		//valid search
		soft.assertTrue(viewStockPage.sendValuesToSearchField("my product","valid"),"Search element is not in Search result");
		viewStockPage.refreshBrowse();
		//invalid search
		soft.assertTrue(viewStockPage.sendValuesToSearchField("xyz","invalid"),"Search element is not in Search result");
		soft.assertAll();
	}
		
	/*
	 * Verify while clicking on the table entry Action drop down, the sublist isdisplayed
	 */
	@Test(priority = 8, enabled = false)
	public void verifyTableRowActionSublistDisplayed() throws Exception {
		soft = new SoftAssert();
		viewStockPage.clickOnTableFirstRowAction();
		soft.assertTrue(viewStockPage.isTableFirstRowActionListDisplayed(), "Table Row Action List is not displayed");
		soft.assertAll();
	}
	
	/*
	 * verify weather the sub list fields are displayed (Dupicate Product, Edit Product, Print BarCode/Label, Delete Product) while clicking on actio drop down iside table
	 */
	@Test(priority = 9, enabled = false)
	public void verifyTableActionSubList() throws Exception {
		soft = new SoftAssert();
		viewStockPage.clickOnTableFirstRowAction();
		soft.assertTrue(viewStockPage.isDulocateProductDisplayed(), "Duplicate Product is not displayed");
		soft.assertTrue(viewStockPage.isEditProductDisplayed(), "Edit Product is not displayed");
		soft.assertTrue(viewStockPage.isPrintBarcodeOrLabelDisplayed(), "Print Barcode/ Label is not displayed");
		soft.assertTrue(viewStockPage.isDeleteProductTableActionDisplayed(), "Delete Product is not displayed");
		soft.assertAll();
	}
	@AfterMethod
	public void logout() {
		lpage.logout();
	}
	
	public void subListDisplay() throws Exception {
		viewStockPage.goBack();
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(50));
		viewStockPage.clickOnActionDropDownIcon();
	}
}
