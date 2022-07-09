package com.obs.tests;

import java.time.Duration;
import java.util.Properties;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.obs.datahandler.PropertyDataHandler;
import com.obs.pages.AddProductPage;
import com.obs.pages.HomePage;
import com.obs.pages.LoginPage;

public class AddProductTest extends BaseTest{
	
	LoginPage lpage;
	HomePage homePage;
	AddProductPage addProductPage;
	SoftAssert soft;
	
	@BeforeMethod
	public void setup() throws Exception {
		lpage = new LoginPage(driver);
		homePage = new HomePage(driver);
		PropertyDataHandler prop = new PropertyDataHandler();
		Properties allProp = prop.readPropertiesFile("config.properties");
		homePage = lpage.login(allProp.getProperty("username"), allProp.getProperty("password"));
		addProductPage = homePage.gotoAddProductPage();
		
	}
	
	/*
	 * Verify all the fields are displayed in the Add Stock/Product page
	 */
	@Test(priority =1 , enabled = false, groups= {"SanityTest"})
	public void validateAddProductFileds() throws Exception {
		soft = new SoftAssert();
		soft.assertTrue(addProductPage.isProductTypeDisplayed(), "Product Type is not displayed");
		soft.assertTrue(addProductPage.isProductNameDisplayed(), "Product Name is not displayed");
		soft.assertTrue(addProductPage.isProductCodeDisplayed(), "Product Code is not displayed");
		soft.assertTrue(addProductPage.isGenerateProductCodeIconDisplayed(), "Generate Product Code Icon is not displayed");
		soft.assertTrue(addProductPage.isCategoryDisplayed(), "Category is not displayed");
		soft.assertTrue(addProductPage.isSubCategoryDisplayed(), "Sub Category is not displayed");
		soft.assertTrue(addProductPage.isModelDisplayed(), "Model is not displayed");
		soft.assertTrue(addProductPage.isAlertQuantityDisplayed(), "Alert Quantity not displayed");
		soft.assertTrue(addProductPage.isQuantityDisplayed(), "Quantity not displayed");
		soft.assertTrue(addProductPage.isProductDetailsDisplayed(), "Product Details is not displayed");
		soft.assertTrue(addProductPage.isSupplierDisplayed(), "Supplier is not displayed");
		soft.assertTrue(addProductPage.isProductUnitDisplayed(), "Product Unit is not displayed");
		soft.assertTrue(addProductPage.isProductCostDisplayed(), "Product Cost is not displayed");
		soft.assertTrue(addProductPage.isProductPriceDisplayed(), "Product Price is not displayed");
		soft.assertTrue(addProductPage.isAddProductBtnDisplayed(), "Add Product Btn is not displayed");
		soft.assertAll();
	}
	
	/*
	 * Verify error message is displayed while trying to Add Product with empty data
	 */
	@Test(priority =2 , enabled = true)
	public void verifyErrorMessage() throws Exception {
		soft = new SoftAssert();
		addProductPage.clickOnAddProduct();
		soft.assertTrue(addProductPage.isProductNameErrorDisplayed(), "Product Name Error is not displayed");
		soft.assertTrue(addProductPage.isProductCodeErrorDisplayed(), "Product Code Error is not displayed");
		soft.assertTrue(addProductPage.isCategoryErrorDisplayed(), "Category Error is not displayed");
		soft.assertTrue(addProductPage.isSupplierErrorDisplayed(), "Supplier Error is not displayed");
		soft.assertTrue(addProductPage.isProductUnitErrorDisplayed(), "Product Unit Error is not displayed");
		soft.assertAll();
	}
	
	/*
	 * Verify the producting is adding successfullt
	 */
	@Test(priority =3 , enabled = false)
	public void verifyAddProduct() throws Exception {  //=======bug : product code is not accepting any value while automating
		addProductPage.sendProductName("Hello");
		addProductPage.clickOnGenerateProductCodeIcon();
		addProductPage.selectCategory("category one name");
		addProductPage.selectModel("model2");
		addProductPage.sendAlertQuantity("30");
		addProductPage.sendQuantity("20");
		addProductPage.sendSupplier("zehra");
		addProductPage.sendProductUnit("56");
		addProductPage.sendProductPrice("890");
		addProductPage.clickOnAddProduct();
		Assert.assertTrue(addProductPage.waitUntilVisibility(), "Product did not added");
	}
	@AfterMethod
	public void logout() {
		lpage.logout();
	}

}
