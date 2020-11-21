package com.oracle.aconex.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

import com.oracle.aconex.base.BasePage;
/**
 * Page Object holding all elements to search names in directory and 
 * various operations that can be performed on the elements
 * present
 * 
 * @author rakesh
 *
 */
public class SearchDirectoryPage extends BasePage {
	@FindBy(css="#ACONEX_list")
	WebElement globalTabinDirectorySearch;
	
	@FindBy(css="input#FIRST_NAME")
	WebElement groupNameInSearch;
	
	@FindBy(css="input#LAST_NAME")
	WebElement familyNameInSearch;
	
	@FindBy(xpath="//div[contains(text(),'Search')]")
	WebElement searchBtn;
	
	@FindBy(css="#resultTable")
	WebElement searchResult;
	
	@FindBy(xpath="//button//div[contains(text(),'To')]")
	WebElement toBtn;
	
	@FindBy(xpath="//td[contains(text(),'To:')]")
	WebElement selectedTo;
	
	@FindBy(xpath="//div[contains(text(),'OK')]")
	WebElement okBtn;

	public SearchDirectoryPage(WebDriver driver) {
		super(driver);
	}
	
	public SearchDirectoryPage searchGlobalInDirectory() {
		driver.switchTo().activeElement();
		new Actions(driver).moveToElement(globalTabinDirectorySearch).perform();
		globalTabinDirectorySearch.click();	
		return this;
	}

	public SearchDirectoryPage setGroupNameInSearch(String _groupNameInSearch) {
		groupNameInSearch.sendKeys(_groupNameInSearch);
		return this;
	}
	
	public SearchDirectoryPage setFamilyNameInSearch(String _familyNameInSearch) {
		familyNameInSearch.sendKeys(_familyNameInSearch);
		return this;
	}
	
	public SearchDirectoryPage clickSearchDirectory( ) {
		searchBtn.click();
		return this;
	}

	public SearchDirectoryPage selectRecipient(String firstname, String lastname) {
			WebElement check = driver.findElement(By.xpath(String.format("//a[contains(text(),'%s') and contains(text(),'%s')]//preceding::td/input[@type='checkbox']", firstname,lastname)));
			check.click();
			return this;
	}

	public SearchDirectoryPage clickToBtn() {
		toBtn.click();
		waitForElementToBeVisible(selectedTo);
		return this;
	}
	
	public MailPage clickOk() {
		okBtn.click();
		return new MailPage(driver);
	}
	

}
