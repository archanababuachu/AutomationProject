package com.obs.pages;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.obs.actions.ValidationActionHelper;
import com.obs.actions.WebActionHelper;


public class ViewStockPage {

	public ViewStockPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	WebDriver driver;
	ValidationActionHelper vahObj = new ValidationActionHelper();
	WebActionHelper wahObj = new WebActionHelper();
	
	@FindBy(xpath = "//h1[contains(.,'Inventory')]")
	WebElement heading;
	@FindBy(xpath = "//div//div/li")
	WebElement actionDropDownIcon;
	@FindBy(xpath = "//label//input[@type='search']")
	WebElement searchField;  
	@FindBy(xpath = "//table/thead")
	WebElement tableHeading;  
	@FindBy(xpath = "//div/li/ul")
	WebElement actionIconSubList;
	@FindBy(xpath = "//div/li//ul/li/a[contains(.,' Add Product')]")
	WebElement addProduct;
	@FindBy(xpath = "//div/li//ul/li/a[contains(.,' Print Barcode/Label ')]")
	WebElement printBarcode;
	@FindBy(xpath = "//div/li//ul/li/a[contains(.,' Export to Excel file  ')]")
	WebElement exportToExcel;
	@FindBy(xpath = "//div/li//ul/li/a[contains(.,' Export to PDF file  ')]")
	WebElement exportToPdf;
	@FindBy(xpath = "//div/li//ul/li/a[contains(.,'Import Products ')]")
	WebElement importProducts;
	@FindBy(xpath = "//div/li//ul/li/a[contains(.,'Delete Products ')]")
	WebElement deleteProduct;
	@FindBy(xpath = "//h1[contains(.,'Add Product')]")
	WebElement addProductHeading;
	@FindBy(xpath = "//h1[contains(.,'Import Csv')]")
	WebElement importPageHeading;
	
	public boolean isHeadingDisplayed() throws Exception {
		return vahObj.isElementVisible(driver, heading);
	}
	public boolean isActionDropDownIconDisplayed() throws Exception {
		return vahObj.isElementVisible(driver, actionDropDownIcon);
	}
	public boolean isSearchFieldDisplayed() throws Exception {
		return vahObj.isElementVisible(driver, searchField);
	}
	public boolean isTableHeadingDisplayed() throws Exception {
		return vahObj.isElementVisible(driver, tableHeading);
	}
	public boolean isSublistDisplayed() throws Exception{
		actionDropDownIcon.click();
		return vahObj.isElementVisible(driver, actionIconSubList);
	}
	public boolean isAddProductDisplayed() throws Exception {
		return vahObj.isElementVisible(driver, addProduct);
	}
	public boolean isPrintBarcodeDisplayed() throws Exception {
		return vahObj.isElementVisible(driver, printBarcode);
	}
	public boolean isExportToExcelDisplayed() throws Exception {
		return vahObj.isElementVisible(driver, exportToExcel);
	}
	public boolean isExportToPdfDisplayed() throws Exception {
		return vahObj.isElementVisible(driver, exportToPdf);
	}
	public boolean isImportProductsDisplayed() throws Exception {
		return vahObj.isElementVisible(driver, importProducts);
	}
	public boolean isDeleteProductDisplayed() throws Exception {
		return vahObj.isElementVisible(driver, deleteProduct);
	}
	public void goBack() {
		wahObj.navigateBack(driver);
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));
	}
	public void clickOnActionDropDownIcon() {
		actionDropDownIcon.click();
	}
	public boolean isAddProductPageDisplayed() throws Exception {
		addProduct.click();
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));
		return vahObj.isElementVisible(driver, addProductHeading);
	}
	public boolean isImportProductPageDisplayed() throws Exception {
		importProducts.click();
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));
		return vahObj.isElementVisible(driver, importPageHeading);
	}
}
