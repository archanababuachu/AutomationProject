package com.obs.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.obs.actions.SendKeysActionHelper;
import com.obs.actions.UtilityActionHelper;
import com.obs.actions.ValidationActionHelper;

public class AddProductPage {

	public AddProductPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	WebDriver driver;
	ValidationActionHelper vahObj = new ValidationActionHelper();
	SendKeysActionHelper skahObj = new SendKeysActionHelper();
	UtilityActionHelper uahObj = new UtilityActionHelper();
	
	@FindBy(xpath = "//div/div/span")
	private WebElement productType;
	
	@FindBy(xpath = "//input[@id='name']")
	private WebElement productName;
	
	@FindBy(xpath = "(//form//div//div)[3]/div/input[@id='code']")
	private WebElement productCode;
	
	@FindBy(xpath = "//span[@id='random_num']")
	private WebElement generateProductCodeIcon;
	
	@FindBy(xpath = "//span[@id='select2-category-container']")
	private WebElement category;
	
	@FindBy(xpath = "//span[@id='select2-subcategory-container']")
	private WebElement subCategory;
	
	@FindBy(xpath = "//span[@id='select2-model-container']")
	private WebElement model;
	@FindBy(xpath = "//input[@id='alert_quantity']")
	private WebElement alertQuantity;
	
	@FindBy(xpath = "//input[@id='quantity']")
	private WebElement quantity;
	
	@FindBy(xpath = "//textarea[@id='details']")
	private WebElement productDetails;
	
	@FindBy(xpath = "//div[@id='content-wrapper']/section[2]/div/div[2]/div/div/form/div[2]/div[1]/span/span[1]/span/ul/li/input")
	private WebElement supplier;
	
	@FindBy(xpath = "//input[@id='unit']")
	private WebElement productUnit;
	
	@FindBy(xpath = "//input[@id='cost']")
	private WebElement productCost;
	@FindBy(xpath = "//input[@id='price']")
	private WebElement productPrice;
	@FindBy(xpath = "//input[@type='submit']")
	private WebElement addProductBtn;
	
	@FindBy(xpath = "//div[@id=\"content-wrapper\"]/section[2]/div/div[2]/div/div/form/div[1]/div[2]/small")
	private WebElement productNameError;
	@FindBy(xpath = "//div[@id=\"content-wrapper\"]/section[2]/div/div[2]/div/div/form/div[1]/div[3]/small")
	private WebElement productCodeError;
	@FindBy(xpath = "//div[@id=\"content-wrapper\"]/section[2]/div/div[2]/div/div/form/div[1]/div[4]/small")
	private WebElement categoryError;
	@FindBy(xpath = "//div[@id=\"content-wrapper\"]/section[2]/div/div[2]/div/div/form/div[2]/div[1]/small")
	private WebElement supplierError;
	@FindBy(xpath = "//div[@id=\"content-wrapper\"]/section[2]/div/div[2]/div/div/form/div[2]/div[2]/small")
	private WebElement productUnitError;
	
	@FindBy(xpath = "")
	private WebElement categorySelectValue;
	
	@FindBy(xpath = "//li[@id='select2-model-result-x3z0-6']")
	private WebElement modelSelectValue;
	
	@FindBy(xpath = "//li[@id=\"select2-supplier-result-lxdd-2\"]")
	private WebElement supplierSelectValue;
	
	@FindBy(xpath = "//div[@id='content-wrapper']/section[2]/div[contains(.,'product_added')]")
	private WebElement productAddSuccessMsg;
	
	@FindBy(xpath = "/html/body/span[2]/span/span[1]/input")
	private WebElement categoryInputField;
	
	@FindBy(xpath = "/html/body/span[2]/span/span[1]/input")
	private WebElement modelInputField;
	
	public boolean isProductTypeDisplayed() throws Exception {
		return vahObj.isElementVisible(driver, productType);
	}

	public void clickOnProductType(){
		productType.click();
	}

	public boolean isProductNameDisplayed() throws Exception {
		return vahObj.isElementVisible(driver, productName);
	}

	public boolean isProductCodeDisplayed() throws Exception {
		return vahObj.isElementVisible(driver, productCode);
	}
	public boolean isGenerateProductCodeIconDisplayed() throws Exception {
		return vahObj.isElementVisible(driver, generateProductCodeIcon);
	}

	public boolean isCategoryDisplayed() throws Exception {
		return vahObj .isElementVisible(driver, category);
	}

	public boolean isSubCategoryDisplayed() throws Exception {
		return vahObj.isElementVisible(driver, subCategory);
	}

	public boolean isModelDisplayed() throws Exception {
		return vahObj.isElementVisible(driver, model);
	}

	public boolean isAlertQuantityDisplayed() throws Exception {
		return vahObj.isElementVisible(driver, alertQuantity);
	}

	public boolean isQuantityDisplayed() throws Exception {
		return vahObj.isElementVisible(driver, quantity);
	}

	public boolean isProductDetailsDisplayed() throws Exception {
		return vahObj.isElementVisible(driver, productDetails);
	}

	public boolean isSupplierDisplayed() throws Exception {
		return vahObj.isElementVisible(driver, supplier);
	}

	public boolean isProductUnitDisplayed() throws Exception {
		return vahObj.isElementVisible(driver, productUnit);
	}

	public boolean isProductCostDisplayed() throws Exception {
		return vahObj.isElementVisible(driver, productCost);
	}

	public boolean isProductPriceDisplayed() throws Exception {
		return vahObj.isElementVisible(driver, productPrice);
	}

	public boolean isAddProductBtnDisplayed() throws Exception {
		return vahObj.isElementVisible(driver, addProductBtn);
	}

	public boolean isProductNameErrorDisplayed() throws Exception {
		return vahObj.isElementVisible(driver, productNameError);
	}

	public boolean isProductCodeErrorDisplayed() throws Exception {
		return vahObj.isElementVisible(driver, productCodeError);
	}

	public boolean isCategoryErrorDisplayed() throws Exception {
		return vahObj.isElementVisible(driver, categoryError);
	}

	public boolean isSupplierErrorDisplayed() throws Exception {
		return vahObj.isElementVisible(driver, supplierError);
	}

	public boolean isProductUnitErrorDisplayed() throws Exception {
		return vahObj.isElementVisible(driver, productUnitError);
	}

	public void clickOnAddProduct() {
		addProductBtn.click();
	}

	public void sendProductName(String string) throws Exception {
		skahObj.clearTextAndSendKeys(driver, productName, string);
	}

	public void clickOnGenerateProductCodeIcon() throws Exception {
		generateProductCodeIcon.click();
		productCode.click();
		//String value = productCode.getText();
		skahObj.clearTextAndSendKeys(driver, productCode, "565447678");
		
		Thread.sleep(3000);
	}

	public void selectCategory(String cat) throws Exception {
		category.click();
		skahObj.sendKeys(driver, categoryInputField, cat);
		uahObj.waitUntilElementVisible(driver, "//ul[@id='select2-category-results']/li");
		uahObj.hoverMouseAndClickByXpath(driver, "//ul[@id='select2-category-results']/li");
	}

	public void selectModel(String mod) throws Exception {
		model.click();
		skahObj.clearTextAndSendKeys(driver, modelInputField, mod);
		uahObj.hoverMouseAndClickByXpath(driver, "/html/body/span[2]/span/span[2]/ul/li");
	}

	public void sendAlertQuantity(String string) throws Exception {
		skahObj.clearTextAndSendKeys(driver, alertQuantity, string);	
	}

	public void sendQuantity(String string) throws Exception {
		skahObj.clearTextAndSendKeys(driver, quantity, string);
	}

	public void sendSupplier(String sup) throws Exception {
		supplier.click();
		skahObj.clearTextAndSendKeys(driver, supplier, sup);
		uahObj.hoverMouseAndClickByXpath(driver, "//ul[@id='select2-supplier-results']/li");
		
	}

	public void sendProductUnit(String string) throws Exception {
		skahObj.clearTextAndSendKeys(driver, productUnit, string);
	}

	public void sendProductPrice(String string) throws Exception {
		skahObj.clearTextAndSendKeys(driver, productPrice, string);
	}

	public boolean waitUntilVisibility() throws Exception {
		uahObj.waitUntilElementVisible(driver, "/html/body/div[2]/div/div[1]/section[2]/div");
		return vahObj.isElementVisible(driver, productAddSuccessMsg);
	}

		

}
