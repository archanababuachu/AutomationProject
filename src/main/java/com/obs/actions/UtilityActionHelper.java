package com.obs.actions;

import java.io.File;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class UtilityActionHelper {

	/**
	 * method to return table row count
	 * @return
	 * @throws Exception 
	 */
	public int getTableRowCount(WebDriver driver, List <WebElement> element) throws Exception {
		try {
			return element.size();
		}catch(Exception e) {
			throw new Exception("getTableRowCount (UtilityActionHelper) : "+e.getMessage());
		}
	}
	/**
	 * method to return table column count
	 * @throws Exception 
	 */
	public int getTableColumnCount(WebDriver driver, List <WebElement> element) throws Exception {
		try {
			return element.size();
		}catch(Exception e) {
			throw new Exception("getTableColumnCount (UtilityActionHelper) : "+e.getMessage());
		}
	}
	/**
	 * method to get table content based on row and column index
	 * @throws Exception 
	 */
	public String getTableContentOnRowAndColumn(WebDriver driver, String xpath) throws Exception {
		try {
			return driver.findElement(By.xpath(xpath)).getText();
		}catch (Exception e) {
			throw new Exception("getTableContentOnRowAndColumn (UtilityActionHelper) : "+e.getMessage());
		}
	}
	/**
	 * method to get allContentsOfTable
	 * @throws Exception 
	 */
	public ArrayList getAllTableContent(WebDriver driver, int rowSize, int columnSize, String xpath) throws Exception {
		ArrayList cellcontents = new ArrayList();
		try {
			for(int i=1; i<=rowSize; i++) {
				for(int j=1; j<=columnSize; j++) {
					String content = driver.findElement(By.xpath("(("+xpath+"["+i+"])/td)["+j+"]")).getText();
					cellcontents.add(content);
				}
			}
		}catch (Exception e) {
			throw new Exception("getAllTableContent (UtilityActionHelper) : "+e.getMessage());
		}
		return cellcontents;
	}
	/**
	 * method to select drop down by visible text
	 * @throws Exception 
	 */
	public void selectDropDownByVisibleText(WebElement element, String visibleText) throws Exception {
		try {
			Select dropDown = new Select(element);
			dropDown.selectByVisibleText(visibleText);
		}catch(Exception e) {
			throw new Exception("selectDropDownByVisibleText (UtilityActionHelper) : "+e.getMessage());
		}
	}
	/**
	 * method to select drop down by index
	 * @throws Exception 
	 */
	public void selectDropDownByIndex(WebElement element, int index) throws Exception {
		try{
			Select dropDown = new Select(element);
			dropDown.selectByIndex(index);
		}catch (Exception e) {
			throw new Exception("selectDropDownByIndex (UtilityActionHelper) : "+e.getMessage());
		}
	}
	/**
	 * method to select drop down by value
	 * @throws Exception 
	 */
	public void selectDropDownByValue(WebElement element, String value) throws Exception {
		try{
			Select dropDown = new Select(element);
			dropDown.selectByValue(value);
		}catch (Exception e) {
			throw new Exception("selectDropDownByValue (UtilityActionHelper) : "+e.getMessage());
		}
	}
	/**
	 * method to take screenshot
	 * @throws Exception 
	 */
	public void capturePageScreenshot(WebDriver driver) throws Exception {
		try {
			File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			FileHandler.copy(src, new File("PageScreenshot.png"));
		}catch (Exception e) {
			throw new Exception("capturePageScreenshot (UtilityActionHelper) : "+e.getMessage());
		}
	}
	/**
	 * method to hover the mouse and click by xpath
	 * @throws Exception 
	 */
	public void hoverMouseAndClickByXpath(WebDriver driver, String xpath) throws Exception {
		try {
			Actions act = new Actions (driver);
			act.moveToElement(driver.findElement(By.xpath(xpath))).click().build().perform();
		}catch (Exception e) {
			throw new Exception("hoverMouseAndClickByXpath (UtilityActionHelper) : "+e.getMessage());
		}
	}
	/**
	 * method to drag and drop
	 * @throws Exception 
	 */
	public void dragAndDrop(WebDriver driver, WebElement source, WebElement target) throws Exception {
		try {
			Actions act = new Actions(driver);
			act.moveToElement(source).clickAndHold().moveToElement(target).release().build().perform();
		}catch (Exception e) {
			throw new Exception("dragAndDrop (UtilityActionHelper) : "+e.getMessage());
		}
	}
	/**
	 * method to drag to X and Y points
	 * @throws Exception 
	 */
	public void dragToXandY(WebDriver driver,WebElement source, int x, int y) throws Exception {
		try {
			Actions act = new Actions(driver);
			act.moveToElement(source).clickAndHold().moveByOffset(x, y).release().build().perform();
		}catch (Exception e) {
			throw new Exception("dragToXandY (UtilityActionHelper) : "+e.getMessage());
		}
	}
	/**
	 * method to wait until element visibility (by xpath)
	 * @throws Exception 
	 */
	public void waitUntilElementVisible(WebDriver driver, String xpath) throws Exception {
		try {
			WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(30));
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
		}catch (Exception e) {
			throw new Exception("waitUntilElementVisible (UtilityActionHelper) : "+e.getMessage());
		}
	}
	/**
	 * method to wait until an element is invisible
	 */
	public void waitUntilElementInvisible(WebDriver driver, String xpathValue) {
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(30));
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(xpathValue)));
	}
	/**
	 * wait until element clickable  (by By) and get the text of element and check it contains that text
	 * @throws Exception 
	 * @param driver
	 * @param searchtext
	 * @param is
	 * @return boolean
	 */
	public boolean waitUntilElementClickableAndCheckTextContains(WebDriver driver, String id,String text) throws Exception {
		boolean status;
		try {
			WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(30));
			String content = wait.until(ExpectedConditions.elementToBeClickable(By.id(id))).getText();
			if (content.contains(text)) {
			    status = true;
			 } else {
			    status = false;
			 }
			return status;
		}catch (Exception e) {
			throw new Exception("waitUntilElementClickable (UtilityActionHelper) : "+e.getMessage());
		}
	}
	/**
	 * scroll to an element until it is visible
	 * @throws Exception 
	 */
	public void scrollToAnElement(WebDriver driver, WebElement element) throws Exception {
		try {
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("arguments[0].scrollIntoView();", element);
		}catch (Exception e) {
			throw new Exception("scrollToAnElement (UtilityActionHelper) : "+e.getMessage());
		}
	}
	
}
