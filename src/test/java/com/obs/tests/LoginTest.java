package com.obs.tests;

import java.io.IOException;
import java.util.Properties;

import org.testng.Assert;
import org.testng.annotations.BeforeGroups;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.obs.actions.WebActionHelper;
import com.obs.datahandler.PropertyDataHandler;
import com.obs.pages.LoginPage;

public class LoginTest extends BaseTest{
	
	LoginPage lpage;
	SoftAssert soft;
	
	@BeforeMethod
	public void invokeLoginPage() {
		lpage = new LoginPage(driver);
	}
	
	@Parameters("browserType")
	@BeforeGroups(groups = "SanityTest")
	public void launchG(String browserType) throws IOException {
		
		WebActionHelper wahObj = new WebActionHelper();
		PropertyDataHandler prop = new PropertyDataHandler();
		
		final String currentDir = System.getProperty("user.dir");
		final String filePath = currentDir + "/src/main/resources/" + "chromedriver.exe";
		
		driver = wahObj.initializeDriver(browserType, filePath);
		
		Properties allProp = prop.readPropertiesFile("config.properties");
		wahObj.launchURL(driver, allProp.getProperty("url"));
		lpage = new LoginPage(driver);
	}
	/*
	 * Verify that the user is able to launch the URL
	 */
	@Test(priority = 0, enabled = true)
	public void verifyURLandTitle(){
		soft = new SoftAssert();
		soft.assertEquals(lpage.getURL(), "https://qalegend.com/mobile_service/panel/login", "Wrong URL Launched");
		soft.assertEquals(lpage.getTitle(), "Login", "Wrong title displayed");
		soft.assertAll();
	}
	
	/*
	 * Verify all fields are displayed on the login page (User Name, Password, Login Button, Remember Me checkbox, Forget Password Link, Back to home Page Link)
	 */
	@Test(priority = 1, enabled = true)
	public void validateLoginPageFields() throws Exception {
		soft = new SoftAssert();
		soft.assertTrue(lpage.isUserNameFieldDisplayed(), "User Name field is not displayed");
		soft.assertTrue(lpage.isPasswordFieldDisplayed(), "Password field is not displayed");
		soft.assertTrue(lpage.isLoginBtnDisplayed(), "Login Button is not displayed");
		soft.assertTrue(lpage.isBackToHomePageLinkDisplayed(), "Back to home page link is not displayed");
		soft.assertTrue(lpage.isForgetPwdLinkDisplayed(), "Forget password link is not displayed");
		soft.assertAll();
	}
	
	/*
	 * Verify that the forget password link redirects to forget password page and in that page Back to login link redirects to Login Page
	 */
	@Test(priority = 2, enabled = true)
	public void verifyForgetPwdAndBackToLogin() throws Exception {
		soft = new SoftAssert();
		soft.assertTrue(lpage.isForgetPasswordLinkRedirects(), "Forget Password Link Not Working");
		soft.assertTrue(lpage.isBackToLoginRedirects(), "Back to Login Link not workingd");
		soft.assertAll();
	}
	
	/*
	 * Verify that the user is able to login to the page
	 */
	@Test(priority = 3, dataProvider = "Credentials", enabled = true, groups = {"SanityTest"})
	public void verifyAllLogin(String uname, String pwd) throws Exception {
		
		soft = new SoftAssert();
		lpage.clickLogin(uname, pwd);
		if(uname.equals("")) 
			Assert.assertTrue(lpage.isUserNameFieldDisplayed(), "Empty login redirects to somewhere");
		if(uname.equals("admin"))
			Assert.assertTrue(lpage.isUserNameFieldDisplayed(), "Invalid uname and pwd check redirects to somewhere");
		if(uname.equals("admin@gmail"))
			Assert.assertTrue(lpage.isUserNameFieldDisplayed(), "Invalid uname and valid pwd check redirects to somewhere");
		if(pwd.equals("pass"))
			Assert.assertTrue(lpage.isUserNameFieldDisplayed(), "Valid uname and Invalid pwd check redirects to somewhere");
		if(pwd.equals("password") && uname.equals("admin@admin.com")) {
			Thread.sleep(2000);
			Assert.assertTrue(lpage.isLoginSuccessMsgDisplayed(), "Home Page is not displayed");
			lpage.logout();
			Assert.assertTrue(lpage.isUserNameFieldDisplayed(), "Valid uname and Invalid pwd check redirects to somewhere");
		}
		soft.assertAll();
	}

	
	@DataProvider(name = "Credentials")
	public Object[][] getData(){
		Object[][] data = new Object[5][2];
		data[0][0] = "";
		data[0][1] = "";
		data[1][0] = "admin";
		data[1][1] = "admin";
		data[2][0] = "admin@gmail";
		data[2][1] = "password";
		data[3][0] = "admin@admin.com";
		data[3][1] = "pass";
		data[4][0] = "admin@admin.com";
		data[4][1] = "password";
		return data;
	}
	
}
