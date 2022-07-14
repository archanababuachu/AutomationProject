package com.obs.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import com.obs.actions.SendKeysActionHelper;
import com.obs.actions.UtilityActionHelper;
import com.obs.actions.ValidationActionHelper;
import com.obs.datahandler.ExcelDataHandler;

public class StockModelsPage {

	public StockModelsPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	WebDriver driver;
	ValidationActionHelper vahObj = new ValidationActionHelper();
	UtilityActionHelper uahObj = new UtilityActionHelper();
	SendKeysActionHelper skahObj = new SendKeysActionHelper();
	ExcelDataHandler excelObj; 
	
	@FindBy(xpath = "//section/h1[text()='Models']")
	private WebElement heading;
	
	@FindBy(xpath = "//button[contains(.,'Add Model')]")
	private WebElement addModelRedirectBtn;
	
	@FindBy(xpath = "/html/body/div[2]/div/div[6]/div/div/div[2]/div/div/form/div[1]/div/span/span[1]/span/ul/li/input")
	private WebElement modelName;
	@FindBy(xpath = "//input[@id='manufacturer_name']")
	private WebElement modelManufacturer;
	
	@FindBy(xpath = "//button[@id=\"submit\"]")
	private WebElement addModelBtn;
	
	@FindBy(xpath = "(//div[@id='toast-container/div/div)[2][contains(.,'Model')]")
	private WebElement addModelSuccess;
	
	@FindBy(xpath = "//div[@id='modelmodal']/div/div/div[1]/button")
	private WebElement addModelCloseButton;
	
	@FindBy(xpath = "//div[@id='model_footer']/button[1]")
	private WebElement addModelGoBackButton;
	
	@FindBy(xpath = "(//table[@id='dynamic-table']//tbody//tr//td//a[@id='modify_model']//i[@class='fas fa-edit'])[1]")
	private WebElement editModelIcon;
	
	@FindBy(xpath = "/html/body/div[12]/div/div/div[2]/div/form/input")
	WebElement editModelName;
	
	@FindBy(xpath = "/html/body/div[12]/div/div/div[3]/button[2]")
	WebElement editModelOkBtn;
	
	@FindBy(xpath = "(//table[@id='dynamic-table']//tbody//tr//td)[3]//a[@id='delete']//i[@class='fas fa-trash']")
	WebElement deleteModelIcon;
	
	@FindBy(xpath = "//input[@type='search']")
	WebElement modelSearchField;
	
	@FindBy(xpath = "//tbody/tr/td")
	private WebElement modelNameSearchReslt;
	
	@FindBy(xpath = "(//tbody/tr/td)[2]")
	private WebElement modelManuSearchReslt;
	
		
	String addModelHeadingXpath = "//div//div/h4[contains(.,'Add Model')]";
	String editModelNameField = "/html/body/div[12]/div/div/div[2]/div/form/input";
	
	public boolean isHeadingDisplayed() throws Exception {
		return vahObj.isElementVisible(driver, heading);
	}

	public boolean isAddModelButtonDisplayed() throws Exception {
		return vahObj.isElementVisible(driver, addModelRedirectBtn);
	}

	public void clickOnAddModelRedirectBtn() throws Exception {
		addModelRedirectBtn.click();
		uahObj.waitUntilElementVisible(driver, addModelHeadingXpath);
	}

	public void sendModelName(String name) throws Exception {
		modelName.click();
		skahObj.clearTextAndSendKeys(driver, modelName, name);
	}

	public void sendModelManufacturer(String manu) throws Exception {
		modelName.click();
		skahObj.clearTextAndSendKeys(driver, modelManufacturer, manu);
	}

	public boolean clickOnAddModelBtn() throws Exception {
		addModelBtn.click();
		return uahObj.waitUntilElementClickableAndCheckTextContains(driver, "toast-container", "Model");
		
	}
	public void clickOnAddModelCloseBtn() {
		addModelCloseButton.click();
	}

	public boolean isAddModelCloseBtnDisplayed() throws Exception {
		uahObj.waitUntilElementVisible(driver, "//button[@id='submit']");
		String style = addModelCloseButton.getAttribute("style");
		if(style.equals("display: block;")) {
			return true;
		}
		else {
			return false;
		}
	}
	public void clickOnAddModelGoBackButton() {
		addModelGoBackButton.click();
	}

	public void clickOnEditModelIcon() throws Exception {
		editModelIcon.click();
		uahObj.waitUntilElementVisible(driver, editModelNameField);
	}

	public void sendModelNameDirect(String text) throws Exception {
		skahObj.clearTextAndSendKeys(driver, editModelName, text);
	}

	public boolean clickOnEditModelOkBtn() throws Exception {
		editModelOkBtn.click();
		return uahObj.waitUntilElementClickableAndCheckTextContains(driver, "toast-container", "edited");
	}

	public boolean clickOnDeleteModelIcon() throws Exception {
		deleteModelIcon.click();
		uahObj.waitUntilElementInvisible(driver, "//div[@id='loadingmessage']");
		return uahObj.waitUntilElementClickableAndCheckTextContains(driver, "toast-container", "deleted");
	}

	
	public boolean verifySearchData(String string) {
		modelSearchField.click();
		modelSearchField.sendKeys(string);
		uahObj.waitUntilElementInvisible(driver, "//div[@id='loadingmessage']");
		String name = modelNameSearchReslt.getText();
		String manu = modelManuSearchReslt.getText(); 
		if(name.equals(string) || name.equals(string)) {
			return true;
		}else {
			return false;
		}
		
	}

	

	
	
}
