package com.obs.pages;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.obs.actions.SendKeysActionHelper;
import com.obs.actions.UtilityActionHelper;
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
	SendKeysActionHelper skahObj = new SendKeysActionHelper();
	UtilityActionHelper uahObj = new UtilityActionHelper();
	
	@FindBy(xpath = "//h1[contains(.,'Inventory')]")
	WebElement heading;
	@FindBy(xpath = "//div//div/li")
	WebElement actionDropDownIcon;
	
	@FindBy(xpath = "//label//input[@type='search']")
	private WebElement searchField; 
	
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
	@FindBy(xpath = "//div[@id='content-wrapper']//div[contains(.,'No product selected. Please select at least one product.')]")
	WebElement exportErrorWithoutProduct;
	@FindBy(xpath = "((//div[@id='content-wrapper']//section)[2]//form//div//div//li/ul/li)[7]/div/div[@class='popover-content']/button[@type='button']")
	WebElement deleteConfirm;
	@FindBy(xpath = "((//div[@id='content-wrapper']//section)[2]//form//div//div//li/ul/li)[7]/div/div[@class='popover-content']/button[@class='btn bpo-close']")
	WebElement deleteClose;
	@FindBy(xpath = "((//div[@id='content-wrapper']//section)[2]//form//div//div//li/ul/li)[7]/div")
	WebElement alertDiv;
	
	@FindBy(xpath = "//table//tbody/tr")
	private List <WebElement> searchTableRowCount;
	
	@FindBy(xpath = "(//table//tbody/tr)[1]/th")
	private List <WebElement> searchTableColCount;
	
	@FindBy(xpath = "/html/body/div[2]/div/div[1]/section[2]/form/div[1]/div[2]/div/div/div[2]/div/table/tbody/tr[1]/td[9]/div/div/button")
	private WebElement tableFirstRowAction;
	
	@FindBy(xpath = "((//table//tbody/tr)[1]/td)[9]/div/div/ul")
	private WebElement tableFirstRowActionList;
	
	@FindBy(xpath = "//tr[@id=\"1\"]/td[9]/div/div/ul/li[1]/a")
	private WebElement duplicateProductTableAction;
	
	@FindBy(xpath = "//tr[@id=\"1\"]/td[9]/div/div/ul/li[2]/a[contains(.,' Edit Product')]")
	private WebElement editProductTableAction;
	
	@FindBy(xpath = "//tr[@id=\"1\"]/td[9]/div/div/ul/li[3]/a[contains(.,' Print Barcode/Label')]")
	private WebElement printBarcodeOrLabelTableAction;
	
	@FindBy(xpath = "//tr[@id=\"1\"]/td[9]/div/div/ul/li[5]/a[contains(.,' Delete Product')]")
	private WebElement deleteProductTableAction;
	
	@FindBy(xpath = "(//tbody//tr/td)[4]")
	private WebElement validStockNameElement;
	
	@FindBy(xpath = "//table//tbody//tr/td")
	private WebElement invalidStockSearchResult;
	
	@FindBy(xpath = "(//table[@id='PRData']//tbody//tr/td)[4]")
	private WebElement inventoryClick;
	
	
	String deleteConfirmXpath = "((//div[@id='content-wrapper']//section)[2]//form//div//div//li/ul/li)[7]/div/div[@class='popover-content']/button[@type='button']";
	String inventoryDetailsDivQR = "((//div[@id='myModal']/div/div/div/div/div)[2]/div/div/div/table/tbody/tr/td)[2]/img";
	
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
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(50));
		return vahObj.isElementVisible(driver, addProductHeading);
	}
	public boolean isImportProductPageDisplayed() throws Exception {
		importProducts.click();
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));
		return vahObj.isElementVisible(driver, importPageHeading);
	}
	public void clickOnExportProduct() {
		exportToExcel.click();
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));
	}
	public boolean isExpotShowsErrorWithoutProduct() throws Exception {
		uahObj.waitUntilElementInvisible(driver, "//div[@id='loadingmessage']");
		return vahObj.isElementVisible(driver, exportErrorWithoutProduct);
	}
	public void clickOnExportToPdf() {
		exportToPdf.click();
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));
	}
	public void clickOnDeleteProduct() {
		deleteProduct.click();
	}
	public void clickOnDeleteConfirm() {
		deleteConfirm.click();
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));
	}
	public void clickOnDeleteNo() {
		deleteClose.click();
	}
	public boolean isDeleteConfirmDisplayed() throws Exception {
		uahObj.waitUntilElementInvisible(driver, deleteConfirmXpath);
		return vahObj.isElementPresentByXpath(driver, deleteConfirmXpath);
	}
	public void clickOnSearchField() {
		searchField.click();
	}
	public boolean sendValuesToSearchField(String value,String searchtype) throws Exception {
		boolean status = false;
		String content;
		skahObj.clearTextAndSendKeys(driver, searchField, value);
		uahObj.waitUntilElementInvisible(driver, "//div[@id='PRData_processing']");
		if(searchtype.equals("invalid")) {
			content = invalidStockSearchResult.getText();
			if(content.equals("No matching records found")) {
				status = true;
			}
		}
		if(searchtype.equals("valid")) {
			content = validStockNameElement.getText();
			System.out.println("===========content  : "+content);
			if(content.equals(value)) {
				status = true;
			}
			
		}
		return status;
		/*ArrayList cellcontents = new ArrayList();
		boolean isFound = false;
		int foundCountValid = 0;
		int foundCountInvalid = 0;
		int foundCount = 0;
		skahObj.clearTextAndSendKeys(driver, searchField, value);
		Thread.sleep(2000);
		int rowSize = uahObj.getTableRowCount(driver, searchTableRowCount);
		int colSize = uahObj.getTableColumnCount(driver, searchTableColCount);
		System.out.println("==============Roe "+rowSize+"  col "+colSize);
		cellcontents = uahObj.getAllTableContent(driver, rowSize, colSize, "//table//tbody/tr");
		if(cellcontents.size() == 0 && searchtype.equals("invalid")) {
			isFound = true;
			return isFound;
		}
		if(cellcontents.size() > 0 && searchtype.equals("invalid")) {
			isFound = false;
			return isFound;
		}
		Iterator iob = cellcontents.iterator();
		for(int i=1; i<rowSize; i++) {
			foundCount = 0;
			foundCountInvalid = 0;
			for(int j=1; j<colSize; j++) {
				if(iob.hasNext()) {
					String content = (String) iob.next();
					if(content.contains(value)) {
						foundCount++;
					}
				}
			}
			if(foundCount > 0 && searchtype.equals("valid")) {
				foundCountValid++;
			}
		}
		if(foundCountValid > 0 && searchtype.equals("valid")) {
			isFound = true;
		}
		return isFound;*/
	}
	public void refreshBrowse() {
		wahObj.refreshBrowser(driver);
		
	}
	public void clickOnTableFirstRowAction() throws Exception {
		uahObj.waitUntilElementVisible(driver, "/html/body/div[2]/div/div[1]/section[2]/form/div[1]/div[2]/div/div/div[2]/div/table/tbody/tr[1]/td[9]/div/div/button");
		tableFirstRowAction.click();
	}
	public boolean isTableFirstRowActionListDisplayed() throws Exception {
		return vahObj.isElementVisible(driver, tableFirstRowActionList);
	}
	public boolean isDulocateProductDisplayed() throws Exception {
		return vahObj.isElementVisible(driver, duplicateProductTableAction);
	}
	public boolean isEditProductDisplayed() throws Exception {
		return vahObj.isElementVisible(driver, editProductTableAction);
	}
	public boolean isPrintBarcodeOrLabelDisplayed() throws Exception {
		return  vahObj.isElementVisible(driver, printBarcodeOrLabelTableAction);
	}
	public boolean isDeleteProductTableActionDisplayed() throws Exception {
		return vahObj.isElementVisible(driver, deleteProductTableAction);
	}
	public boolean clickOnInventoryList() throws Exception {
		inventoryClick.click();
		uahObj.waitUntilElementVisible(driver, inventoryDetailsDivQR);
		boolean status = vahObj.isElementPresentByXpath(driver, inventoryDetailsDivQR);
		wahObj.refreshBrowser(driver);
		return status;
		
	}
	
}
