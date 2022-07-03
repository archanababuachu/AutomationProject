package com.obs.actions;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ValidationActionHelper {

	/**
	 * method to check weather the element is displayed
	 * @throws Exception 
	 */
	public boolean isElementVisible(WebDriver driver, WebElement element) throws Exception {
		boolean elementVisible = false;
		try {
			if(element.isDisplayed())
				elementVisible = true;
			return elementVisible;
		}catch(Exception e) {
			throw new Exception("checkElementDisplayed (ValidationActionHelper) : "+e.getMessage());
		}
	}
	/**
	 * method to check weather the element is enabled
	 * @throws Exception 
	 */
	public boolean isElementEnabled(WebDriver driver, WebElement element) throws Exception {
		boolean elementEnabled = false;
		try {
			if(element.isEnabled())
				elementEnabled = true;
			return elementEnabled;
		}catch(Exception e) {
			throw new Exception("isElementEnabled (ValidationActionHelper) : "+e.getMessage());
		}
	}
	/**
	 * method to check weather the element is selected
	 * @throws Exception 
	 */
	public boolean isElementSelected(WebDriver driver, WebElement element) throws Exception {
		boolean elementSelected = false;
		try {
			if(element.isEnabled())
				elementSelected = true;
			return elementSelected;
		}catch(Exception e) {
			throw new Exception("isElementEnabled (ValidationActionHelper) : "+e.getMessage());
		}
	}
}	
