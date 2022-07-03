package com.obs.actions;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class SendKeysActionHelper {

	/**
	 * method to set text to the input field
	 * @throws Exception 
	 */
	public void sendKeys(WebDriver driver, WebElement element, String inputText) throws Exception {
		try {
			element.sendKeys(inputText);
		}catch(Exception e) {
			throw new Exception("sendKeys (SendKeysActionHelper) "+e.getMessage());
		}
	}
	/**
	 * method to clear and set text to the input field
	 * @throws Exception 
	 */
	public void clearTextAndSendKeys(WebDriver driver, WebElement element, String inputText) throws Exception {
		try {
			element.clear();
			element.sendKeys(inputText);
		}catch(Exception e) {
			throw new Exception("clearTextAndSendKeys (SendKeysActionHelper) "+e.getMessage());
		}
	}
	/**
	 * method to set text to input field using js
	 * @throws Exception 
	 */
	public void sendKeysJsById(WebDriver driver, String elementId, String text) throws Exception {
		try { 
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("document.getElementById('"+elementId+"').value='"+text+"';");
		}catch(Exception e) {
			throw new Exception("sendKeysByJs (SendKeysActionHelper) "+e.getMessage());
		}
	}
}
