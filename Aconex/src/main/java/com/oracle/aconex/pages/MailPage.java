package com.oracle.aconex.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;
import com.oracle.aconex.base.BasePage;
/**
 * Page Object holding all elements after clicking Mail tab and 
 * various operations that can be performed on the elements
 * present
 * 
 * @author rakesh
 *
 */
public class MailPage extends BasePage {
	@FindBy(css="iframe#frameMain") //main frame
	WebElement mainFrame;
	
	@FindBy(xpath="//*[@id='Correspondence_correspondenceTypeID']")
	WebElement type;
	
	@FindBy(xpath="//table[@id='heroSection']//tr[1]//button//div[contains(text(),'Directory')]")
	WebElement toDirectoryBtn;
	
	@FindBy(css="#multiselect0")
	WebElement attribute;
	
	@FindBy(css="div#attributeBidi_PRIMARY_ATTRIBUTE div.uiBidi-left > select")
	WebElement atrribute1;
	
	@FindBy(css="div#attributeBidi_SECONDARY_ATTRIBUTE div.uiBidi-left > select")
	WebElement atrribute2;
		
	@FindBy(css="button#attributeBidi_PRIMARY_ATTRIBUTE_add")
	WebElement addItem1;

	@FindBy(css="button#attributeBidi_SECONDARY_ATTRIBUTE_add")
	WebElement addItem2;
	
	@FindBy(css="button#attributePanel-commit")
	WebElement ok;
	
	@FindBy(css="button#btnSend[title*='Send']")
	WebElement send;
	
	By subjectLocator = By.cssSelector("table#heroSection input[name*='Correspondence_subject']"); 
	By textLocator = By.xpath("//h1[contains(text(),'View Mail')]");
	
	public MailPage(WebDriver driver) {
		super(driver);
	}

	public MailPage selectMailType(String _type) {
		driver.switchTo().frame(mainFrame);
		Select mailType = new Select(type);
		mailType.selectByVisibleText(_type);
		return this;
	}

	public SearchDirectoryPage clickToDirectory() {
		new Actions(driver).moveToElement(toDirectoryBtn).perform();
		toDirectoryBtn.click();
		return new SearchDirectoryPage(driver);
	}
	
	public MailPage setMailSubject(String _subject) {
		waitForElementToBePresent(subjectLocator);
		driver.findElement(subjectLocator).sendKeys(_subject);
		return this;
	}

	public MailPage selectAttributes() {
		new Actions(driver).moveToElement(attribute).perform();
		attribute.click();
		selectAttribute1();
		selectAttribute2();

		ok.click();
		return this;
	}
	
	private void selectAttribute2() {
		Select selectAttribute2 = new Select(atrribute2);
		selectAttribute2.selectByIndex(1);
		addItem2.click();
		
	}

	private void selectAttribute1() {
		Select selectAttribute1 = new Select(atrribute1);
		selectAttribute1.selectByIndex(1);
		addItem1.click();
	}

	public boolean clickSendMailAndConfirm(String _viewText) {
		send.click();
		return waitForTextToBePresent(textLocator, _viewText);
	}

	public HomePage returnToHomePage() {
		return new HomePage(driver);
	}


	
	
	
	
	
}
