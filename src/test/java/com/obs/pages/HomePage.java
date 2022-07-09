package com.obs.pages;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.obs.actions.SendKeysActionHelper;
import com.obs.actions.UtilityActionHelper;
import com.obs.actions.ValidationActionHelper;


public class HomePage {
	
	public HomePage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	WebDriver driver;
	ValidationActionHelper vahObj = new ValidationActionHelper();
	SendKeysActionHelper skaObj = new SendKeysActionHelper();
	UtilityActionHelper uahObj = new UtilityActionHelper();
	
		
	@FindBy(id = "sidebar_toggle")
	WebElement hambergerButton;
	@FindBy(xpath = "//a[contains(.,'Stock/Inventory')]")
	WebElement stockTab;
	
	@FindBy(xpath = " //input[@name='q' and @class='form-control']") 
	WebElement searchTab;
	
	@FindBy(id = "search-btn")
	WebElement searchBtn;
	
	@FindBy(xpath = "//h1[contains(.,'Order & Reparations')]") 
	WebElement searchPage;
	
	@FindBy(xpath = "//table[@id='dynamic-table']//tbody/tr") 
	List <WebElement> tableRowCount;
	
	@FindBy(xpath = "(//table[@id='dynamic-table']//tbody/tr)[1]/td") 
	List <WebElement> tableRowColumnCount;
	
	@FindBy(xpath = "//div[@id='dynamic-table_paginate']//ul/li/a[contains(.,'Next > ')]") 
	WebElement paginationNext;
	
	@FindBy(xpath="//a[text()='Next > ']") 
	private List<WebElement> nextBtn;
	
	@FindBy(xpath = "(//aside//section//ul/li)[5]/ul/li/a[contains(.,' View Stock')]") 
	WebElement viewStock;
	@FindBy(xpath = "(//aside//section//ul/li)[5]/ul/li/a[contains(.,' Add Stock/Product')]") 
	WebElement addStock;
	@FindBy(xpath = "(//aside//section//ul/li)[5]/ul/li/a[contains(.,' Suppliers')]") 
	WebElement suppliers;
	@FindBy(xpath = "(//aside//section//ul/li)[5]/ul/li/a[contains(.,' Manufacturers')]") 
	WebElement manufacuures;
	@FindBy(xpath = "(//aside//section//ul/li)[5]/ul/li/a[contains(.,' Models')]") 
	WebElement models;
	
	
	
	public boolean isHambergerButtonDisplayed() throws Exception {
		return vahObj.isElementVisible(driver, hambergerButton);
	}
	public boolean isStockTabVisible() throws Exception {
		return vahObj.isElementVisible(driver, stockTab);
	}
	public void performSearchOperation(String searchKey) throws Exception{  
		searchTab.click();
		skaObj.clearTextAndSendKeys(driver, searchTab, searchKey);
		searchBtn.click();
		uahObj.waitUntilElementVisible(driver, "//table[@id='dynamic-table']//tbody/tr");
	}
	public boolean checkSearchContent(String searchKey, String type) throws Exception {   
		boolean status = false;
		if(type.equals("invalid")) {
			if(vahObj.isElementPresentByXpath(driver, "//table[@id='dynamic-table']/tbody/tr/td[text()='No matching records found']")) {
				status = true;
			}
		}
		if(type.equals("valid")) {
			if(vahObj.isElementPresentByXpath(driver, "(//table[@id='dynamic-table']/tbody/tr/td)[3]/a[text()='"+searchKey+"']")) {
				status = true;
			}
		}
		return status;
	}
	
	public boolean isPaginationNextEnabled() throws Exception {
		return paginationNext.getAttribute("class").contains("disabled");
	}
	public void clickOnPaginationNext() {
		paginationNext.click();
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));
	}
	
	public boolean isSearchPageDisplayed() throws Exception {
		return vahObj.isElementVisible(driver, searchPage);
	}
	public void clickOnStockTab() {
		stockTab.click();
	}
	public boolean isViewStockDisplayed() throws Exception {
		return vahObj.isElementVisible(driver, viewStock);
	}
	public boolean isSuppliersDisplayed() throws Exception {
		return vahObj.isElementVisible(driver, suppliers);
	}
	public boolean isAddStockDisplayed() throws Exception {
		return vahObj.isElementVisible(driver, addStock);
	}
	public boolean isManufacturesDisplayed() throws Exception {
		return vahObj.isElementVisible(driver, manufacuures);
	}
	public boolean isModelsDisplayed() throws Exception {
		return vahObj.isElementVisible(driver, models);
	}
	
	public int getPaginationNextButtonSize() {
		return nextBtn.size();
	}
	public ViewStockPage gotoStockPage() throws Exception {
		stockTab.click();
		uahObj.waitUntilElementVisible(driver, "(//aside//section//ul/li)[5]/ul/li/a[contains(.,' View Stock')]");
		viewStock.click();
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));
		return new ViewStockPage(driver);
	}
	public AddProductPage gotoAddProductPage() throws Exception {
		stockTab.click();
		uahObj.waitUntilElementVisible(driver, "(//aside//section//ul/li)[5]/ul/li/a[contains(.,' Add Stock/Product')]");
		addStock.click();
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));
		return new AddProductPage(driver);
	}
	public StockModelsPage gotoStockModelsPage() throws Exception {
		stockTab.click();
		uahObj.waitUntilElementVisible(driver, "(//aside//section//ul/li)[5]/ul/li/a[contains(.,' Models')]");
		models.click();
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));
		return new StockModelsPage(driver);
	}
	public ManufacturersPage gotoManufacturersPage() throws Exception {
		stockTab.click();
		uahObj.waitUntilElementVisible(driver, "(//aside//section//ul/li)[5]/ul/li/a[contains(.,' Manufacturers')]");
		manufacuures.click();
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));
		return new ManufacturersPage(driver);
	}
}
