package com.obs.actions;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Set;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;

import com.obs.datahandler.PropertyDataHandler;

import io.github.bonigarcia.wdm.WebDriverManager;

public class WebActionHelper {
	
	static WebDriver driver;

	
	/**
	 * method to intialize driver	
	 * @return 
	 */
	public WebDriver initializeDriver(String browserType, String filePath) {
		
		 
		try {
			switch (browserType) {
			case "chrome": 
				System.setProperty("webdriver.chrome.driver", filePath);
				driver = new ChromeDriver();
				break;
			case "edge":
				WebDriverManager.edgedriver().setup();
				driver = new EdgeDriver();

			default:
				break;
			}
		}catch (Exception e) {
			System.out.println("Launch Exception (WebActionHelper) : "+e.getMessage());
		}
		return driver;
	}
	/**
	 * method to launch the URL and maximize the window
	 */
	public void launchURL(WebDriver driver, String url) {
		try {
			driver.get(url);
			driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));
			driver.manage().window().maximize();
		}catch(Exception e) {
			System.out.println("Launch Exception (WebActionHelper) : "+e.getMessage());
		}
		
	}
	
	public static WebDriver getDriver() {
		return driver;
	}
	
	/**
	 * method to navigate to URL
	 */
	public void navigateToURL(WebDriver driver, String url) {
		try {
			if(driver != null) {
				driver.navigate().to(url);
			}else {
				System.out.println("=========================\nWeb Action Helper : navigateToURL() --> driver NULL");
			}
		}catch(Exception e) {
			System.out.println("navigateToURL Exceprion (WebActionHelper) :"+e.getMessage());
		}
	}
	/**
	 * method to navigate backward
	 */
	public void navigateBack(WebDriver driver) {
		try {
			driver.navigate().back();
		}catch(Exception e) {
			System.out.println("navigateBack Exception (WebActionHelper) : "+e.getMessage());
		}
	}
	/**
	 * method to navigate forward
	 */
	public void navigateForward(WebDriver driver) {
		try {
			driver.navigate().forward();
		}catch(Exception e) {
			System.out.println("navigateForward (WebActionHelper) : "+e.getMessage());
		}
	}
	/**
	 * method to refresh browser
	 */
	public void refreshBrowser(WebDriver driver) {
		try {
			driver.navigate().refresh();
		}catch(Exception e) {
			System.out.println("refreshBrowser (WebActionHelper) : "+e.getMessage());
		}
	}
	/**
	 * method to get the title of current site
	 */
	public String getSiteTitle(WebDriver driver) {
		String title = "";
		try {
			title = driver.getTitle();
		}catch(Exception e) {
			System.out.println("getSiteTitle (WebActionHelper) : "+e.getMessage());
		}
		return title;
	}
	/**
	 * method to get the URL of current site
	 */
	public String getSiteURL(WebDriver driver) {
		String url = "";
		try {
			url = driver.getCurrentUrl();
		}catch(Exception e) {
			System.out.println("getSiteURL (WebActionHelper) : "+e.getMessage());
		}
		return url;
	}
	/**
	 * method to get the page source of the current page of the website
	 */
	public String getSitePageSource(WebDriver driver) {
		String pageSource = "";
		try {
			pageSource = driver.getPageSource();
		}catch(Exception e) {
			System.out.println("getSitePageSource (WebActionHelper) : "+e.getMessage());
		}
		return pageSource;
	}
	/**
	 * method to accept alert box
	 */
	public void acceptAlert(WebDriver driver) {
		try {
			Alert alertBox = driver.switchTo().alert();
			alertBox.accept();
		}catch(Exception e) {
			System.out.println("acceptAlert (WebActionHelper) : "+e.getMessage());
		}
	}
	/**
	 * method to dismiss alert box
	 */
	public void dismissAlert(WebDriver driver) {
		try {
			Alert alertBox = driver.switchTo().alert();
			alertBox.dismiss();
		}catch(Exception e) {
			System.out.println("dismissAlert (WebActionHelper) : "+e.getMessage());
		}
	}
	/*
	 * method to get the content of alert box
	 */
	public String getAlertBoxContent(WebDriver driver) {
		String content = "";
		try {
			Alert alertBox = driver.switchTo().alert();
			content = alertBox.getText();
		}catch(Exception e) {
			System.out.println("getAlertBoxContent (WebActionHelper) : "+e);
		}
		return content;
	}
	/**
	 * method to switch to frame
	 * @throws Exception 
	 */
	public void switchToFrame(WebDriver driver, int index) throws Exception {
		try {
			driver.switchTo().frame(index);
		}catch(Exception e) {
			throw new Exception  ("switchToFrame(WebActionHelper) : "+e.getMessage());
		}
	}
	/**
	 * method to switch to parent frame
	 * @throws Exception 
	 */
	public void switchToParentFrame(WebDriver driver) throws Exception {
		try {
			driver.switchTo().parentFrame();
		}catch(Exception e) {
			throw new Exception("switchToParentFrame(WebActionHelper) : "+e.getMessage());
		}
	}
	/**
	 * method to count the total no of frames
	 * @throws Exception 
	 */
	public int totalFrameCount(WebDriver driver) throws Exception {
		try {
			List <WebElement> frames = driver.findElements(By.tagName("iframe"));
			return frames.size();
		}catch(Exception e) {
			throw new Exception("totalFrameCount(WebActionHelper) : "+e.getMessage());
		}
	}
	/**
	 * print the total number of browser instances
	 * @throws Exception 
	 */
	public int countTotalWindows(WebDriver driver) throws Exception {
		try {
			Set <String> windows = driver.getWindowHandles();
			return windows.size();
		}catch (Exception e) {
			throw new Exception("countTotalWindows(WebActionHelper) : "+e.getMessage());
		}
	}
	/**
	 * method to switch to a new browser instance and return parent browser
	 * @throws Exception 
	 */
	public String switchToNewelyOpenedWindowAndReturnParentWindow(WebDriver driver) throws Exception {
		try {
			String parentWindow = driver.getWindowHandle();
			ArrayList <String> windows = new ArrayList();
			windows.addAll(driver.getWindowHandles());
			int count = windows.size();
			String newWindow = windows.get(count-1);
			driver.switchTo().window(newWindow);
			return parentWindow;
		}catch (Exception e) {
			throw new Exception("switchToNewelyOpenedWindow(WebActionHelper) : "+e.getMessage());
		}
	}
	/**
	 * method to switch to parentWindow
	 * @throws Exception 
	 */
	public void switchToParentWindow(WebDriver driver, String window) throws Exception {
		try {
			driver.switchTo().window(window);
		}catch(Exception e) {
			throw new Exception("switchToParentWindow(WebActionHelper) : "+e.getMessage());
		}
	}
	/**
	 * method to close the current browser instance
	 * @throws Exception 
	 */
	public void closeCurrentWindow(WebDriver driver) throws Exception {
		try {
			driver.close();
		}catch(Exception e) {
			throw new Exception("closeCurrentWindow(WebActionHelper) : "+e.getMessage());
		}
	}
}
