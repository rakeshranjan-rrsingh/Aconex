package com.oracle.aconex.base;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
/**
 * Class holding all operations that all page needs to perform 
 * and is common across all pages
 * 
 * @author rakesh
 *
 */
public class BasePage {
	
	private static final int TIMEOUT = 10;
	private static final int POLLING = 60;
	public static String docNo;

	protected WebDriver driver;
	private WebDriverWait wait;

	public BasePage(WebDriver driver) {
		this.driver = driver;
	    wait = new WebDriverWait(driver, TIMEOUT, POLLING);
	    PageFactory.initElements(new AjaxElementLocatorFactory(driver, TIMEOUT), this);
	}
	
	//wait till an element is visible
	protected void waitForElementToBeVisible(WebElement element) {
		wait.until(ExpectedConditions.visibilityOf(element));
	}
	
	//wait till an element is present
	protected void waitForElementToBePresent(By locator) {
		wait.until(ExpectedConditions.presenceOfElementLocated(locator));
	}
	
	//wait if the text looking for found to be present
	protected boolean waitForTextToBePresent(By locator, String txt) {
		return wait.until(ExpectedConditions.textToBePresentInElementLocated(locator, txt)).booleanValue();
	}


}
