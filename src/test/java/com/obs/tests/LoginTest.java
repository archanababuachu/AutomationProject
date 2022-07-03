package com.obs.tests;

import java.time.Duration;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.obs.pages.LoginPage;

public class LoginTest extends BaseTest{
	
	LoginPage lpage;
	SoftAssert soft;
	
	@BeforeMethod
	public void invokeLoginPage() {
		lpage = new LoginPage(driver);
	}

	@Test(priority = 0, enabled = false)
	public void verifyURLandTitle(){
		soft = new SoftAssert();
		soft.assertEquals(lpage.getURL(), "https://qalegend.com/mobile_service/panel/login", "Wrong URL Launched");
		soft.assertEquals(lpage.getTitle(), "Login", "Wrong title displayed");
		soft.assertAll();
	}
	
	@Test(priority = 1)
	public void validateLoginPageFields() throws Exception {
		soft = new SoftAssert();
		soft.assertTrue(lpage.isUserNameFieldDisplayed(), "User Name field is not displayed");
		soft.assertTrue(lpage.isPasswordFieldDisplayed(), "Password field is not displayed");
		soft.assertTrue(lpage.isLoginBtnDisplayed(), "Login Button is not displayed");
		//soft.assertTrue(lpage.isRememberMeCheckboxDisplayed(), "Remember Me checkbox is not displayed");
		soft.assertTrue(lpage.isBackToHomePageLinkDisplayed(), "Back to home page link is not displayed");
		soft.assertTrue(lpage.isForgetPwdLinkDisplayed(), "Forget password link is not displayed");
		soft.assertAll();
	}
	
	@Test(priority = 2)
	public void verifyForgetPwdAndBackToLogin() throws Exception {
		soft = new SoftAssert();
		soft.assertTrue(lpage.isForgetPasswordLinkRedirects(), "Forget Password Link Not Working");
		soft.assertTrue(lpage.isBackToLoginRedirects(), "Back to Login Link not workingd");
		soft.assertAll();
	}
	
	@Test(priority = 3, dataProvider = "Credentials")
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
