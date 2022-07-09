package com.obs.pages;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.obs.actions.SendKeysActionHelper;
import com.obs.actions.UtilityActionHelper;
import com.obs.actions.ValidationActionHelper;
import com.obs.actions.WebActionHelper;

public class LoginPage {
	
	public LoginPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	WebDriver driver;
	WebActionHelper wahObj = new WebActionHelper();
	ValidationActionHelper vahObj = new ValidationActionHelper();
	SendKeysActionHelper skahObj = new SendKeysActionHelper();
	UtilityActionHelper uahObj = new UtilityActionHelper();
	
	@FindBy(id = "identity")
	WebElement uname;
	@FindBy(id = "password")
	WebElement password;
	@FindBy(xpath = "//input[@type='submit' and @name='submit']")
	WebElement loginBtn;
	@FindBy(xpath = "//div//label//div//ins[@class='iCheck-helper']")
	WebElement rememberMe;
	@FindBy(xpath = "//a[contains(.,'Forgot your password?')]")
	WebElement forgetPwdLink;
	@FindBy(xpath = "//a[contains(.,'Back to home page')]")
	WebElement backToHmePageLink;
	@FindBy(id = "infoMessage")
	WebElement emptyLoginMsg;
	@FindBy(xpath = "//div//p[contains(.,'Logged In Successfully')]")
	WebElement loginSuccessMsg;
	@FindBy(xpath = "//a[contains(.,'Back to login!')]")
	WebElement backToLoginLink;
	@FindBy(xpath = "(//div/div/header/nav/div/ul/li)[3]")
	WebElement userDetails;
	@FindBy(xpath = "//a[contains(.,'Sign out')]")
	WebElement signOutBtn;
	
	
	public String getURL() {
		return wahObj.getSiteURL(driver);
	}
	public String getTitle() {
		return wahObj.getSiteTitle(driver);
	}
	public boolean isUserNameFieldDisplayed() throws Exception {
		return vahObj.isElementPresentByXpath(driver, "//input[@id='identity']");
	}
	public boolean isPasswordFieldDisplayed() throws Exception {
		return vahObj.isElementVisible(driver, password);
	}
	public boolean isLoginBtnDisplayed() throws Exception {
		return vahObj.isElementVisible(driver, loginBtn);
	}
	public boolean isRememberMeCheckboxDisplayed() throws Exception {
		return vahObj.isElementVisible(driver, rememberMe);
	}
	public boolean isForgetPwdLinkDisplayed() throws Exception {
		return vahObj.isElementVisible(driver, forgetPwdLink);
	}
	public boolean isBackToHomePageLinkDisplayed() throws Exception {
		return vahObj.isElementVisible(driver, backToHmePageLink);
	}
	public boolean isEmptyLoginMsgDisplayed() throws Exception {
		return vahObj.isElementVisible(driver, emptyLoginMsg);
	}
	public boolean isLoginSuccessMsgDisplayed() throws Exception {
		return vahObj.isElementVisible(driver, loginSuccessMsg);
	}
	public boolean isForgetPasswordLinkRedirects() throws Exception {
		forgetPwdLink.click();
		return vahObj.isElementVisible(driver, backToLoginLink);
	}
	public boolean isBackToLoginRedirects() throws Exception {
		backToLoginLink.click();
		return vahObj.isElementVisible(driver, forgetPwdLink);
	}
	public void clickLogin(String username, String pwd) throws Exception {
		skahObj.clearTextAndSendKeys(driver, uname, username);
		skahObj.clearTextAndSendKeys(driver, password, pwd);
		loginBtn.click();
	}
	public void logout() {
		userDetails.click();
		signOutBtn.click();
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(40));
	}
	public HomePage login(String username, String pwd) throws Exception {
		skahObj.clearTextAndSendKeys(driver, uname, username);
		skahObj.clearTextAndSendKeys(driver, password, pwd);
		loginBtn.click();
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));
		return new HomePage(driver);
	}
	

}
