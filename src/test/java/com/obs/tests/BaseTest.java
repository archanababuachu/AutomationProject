package com.obs.tests;

import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import com.obs.actions.WebActionHelper;
import com.obs.datahandler.PropertyDataHandler;

public class BaseTest {

	public static WebDriver driver;
	
	@Parameters("browserType")
	@BeforeTest
	public void launch(String browserType) throws IOException {
		
		WebActionHelper wahObj = new WebActionHelper();
		PropertyDataHandler prop = new PropertyDataHandler();
		
		final String currentDir = System.getProperty("user.dir");
		final String filePath = currentDir + "/src/main/resources/" + "chromedriver.exe";
		
		driver = wahObj.initializeDriver(browserType, filePath);
		
		Properties allProp = prop.readPropertiesFile("config.properties");
		wahObj.launchURL(driver, allProp.getProperty("url"));
	}
	
	@AfterTest
	public void quit() throws InterruptedException {
		//driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3000));
		Thread.sleep(3000);
		driver.quit();
	}
}
