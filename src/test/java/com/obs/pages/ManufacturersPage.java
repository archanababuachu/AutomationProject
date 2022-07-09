package com.obs.pages;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.obs.actions.SendKeysActionHelper;
import com.obs.actions.UtilityActionHelper;
import com.obs.actions.ValidationActionHelper;

public class ManufacturersPage {

	public ManufacturersPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	WebDriver driver;
	ValidationActionHelper vahObj = new ValidationActionHelper();
	UtilityActionHelper uahObj = new UtilityActionHelper();
	SendKeysActionHelper skahObj = new SendKeysActionHelper();
	
	@FindBy(xpath = "//button[contains(.,' Add ')]")
	private WebElement manuAddBtnLink;
	
	@FindBy(xpath = "//h1")
	private WebElement manuHeading;
	
	@FindBy(xpath = "(//table/tbody/tr/td)[2]/a[@id='modify_manufacturer']")
	private WebElement manuEditLink;
	
	@FindBy(xpath = "/html/body/div[12]/div/div/div[3]/button[2]")
	private WebElement manuEditOkBtn;
	
	@FindBy(xpath = "/html/body/div[12]/div/div/div[2]/div/form/input")
	WebElement editManuNameField;
	
	@FindBy(xpath = "((//table//tbody/tr/td)[2]/a)[2]")
	WebElement manuDeleteLink;
	
	@FindBy(xpath = "//div[@id='dynamic-table_filter']/label/input[@type='search']")
	WebElement manuSearch;
	
	@FindBy(xpath = "//table[@id='dynamic-table']/tbody/tr/td")
	private WebElement searchVaildCheck;
	
	@FindBy(xpath = "//table[@id='dynamic-table']/tbody/tr")
	private List <WebElement> searchInvaildCheck;
	
	String manuEditOkBtnXpath = "/html/body/div[12]/div/div/div[3]/button[2]";

	public boolean isManufacturerAddBtnLinkDisplayed() throws Exception {
		return vahObj.isElementVisible(driver, manuAddBtnLink);
		
	}

	public boolean isManufacturerHeadingDisplayed() throws Exception {
		return vahObj.isElementVisible(driver, manuHeading);
	}

	public void editManufacturerLinkBtn() throws Exception {
		manuEditLink.click();
		uahObj.waitUntilElementVisible(driver, manuEditOkBtnXpath);
	}

	public boolean editManufacturer(String string) throws Exception {
		skahObj.clearTextAndSendKeys(driver, editManuNameField, string);
		manuEditOkBtn.click();
		return uahObj.waitUntilElementClickableAndCheckTextContains(driver, "toast-container", "edited");
	}

	public boolean deleteManufacturer() throws Exception {
		manuDeleteLink.click();
		return uahObj.waitUntilElementClickableAndCheckTextContains(driver, "toast-container", "Deleted");
	}

	public void clickOnSearchField() {
		manuSearch.click();
		
	}

	public boolean searchManufacturer(String searchKey, String type) throws Exception {
		boolean status = false;
		skahObj.clearTextAndSendKeys(driver, manuSearch, searchKey);
		uahObj.waitUntilElementInvisible(driver, "//div[@id='loadingmessage']");
		if(type.equals("valid")) {
			String result = searchVaildCheck.getText();
			if(result.equals(searchKey)) {
				status = true;
			}
		}
		if(type.equals("invalid")) {
			int count = uahObj.getTableRowCount(driver, searchInvaildCheck);
			if(count == 1) {
				status = true;
			}
		}
		return status;
	}
}
