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
		//WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(20));
		//wait.until(ExpectedConditions.visibilityOfElementLocated(searchPage));
	}
	public boolean checkSearchContent(String searchKey, String type) throws Exception {   
		boolean isFound = false;
		int isFoundCount = 0;
		int rowCount = uahObj.getTableRowCount(driver, tableRowCount);
		int columnCount = uahObj.getTableColumnCount(driver, tableRowColumnCount);
		ArrayList contents = new ArrayList();
		contents = uahObj.getAllTableContent(driver, rowCount-1, columnCount, "//table[@id='dynamic-table']//tbody/tr");
		if(contents.size() > 0 && type.equals("invalid")) {
			isFound = true;
		}
		if(contents.size() > 0 && type.equals("valid")) {
			Iterator iterateContents = contents.iterator();
			while(iterateContents.hasNext())
			{
				if(iterateContents.next().equals(searchKey)) {
					isFoundCount++;
				}
			}
			if(isFoundCount > 0) {
				isFound = true;
			}
		}
		return isFound;
	}
	public boolean isPaginationNextEnabled() throws Exception {
		return vahObj.isElementEnabled(driver, paginationNext);
	}
	public void clickOnPaginationNext() {
		paginationNext.click();
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
	
	public ViewStockPage gotoStockPage() throws Exception {
		stockTab.click();
		//wait
		viewStock.click();
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));
		return new ViewStockPage(driver);
	}
}
