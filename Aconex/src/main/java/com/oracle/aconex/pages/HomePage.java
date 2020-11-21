package com.oracle.aconex.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import com.oracle.aconex.base.BasePage;
/**
 * Page Object holding all elements after loggin into application and 
 * various operations that can be performed on the elements
 * present
 * 
 * @author rakesh
 *
 */
public class HomePage extends BasePage {
	
	@FindBy(css="button#nav-bar-DOC")
	WebElement documentButton; 
	
	@FindBy(css="button#nav-bar-CORRESPONDENCE")
	WebElement mailButton;

	
	@FindBy(xpath="//*[@id=\"nav-bar-DOC-DOC-NEW\"]")
	WebElement uploadDoc;
	
	@FindBy(css="#nav-bar-DOC-DOC-SEARCH")
	WebElement docSearch;
	
	@FindBy(css="div#nav-bar-CORRESPONDENCE-CORRESPONDENCE-CREATEMAIL")
	WebElement blankMail;

	
	@FindBy(xpath="//div[@id='navContainer']//span[@class='nav-userDetails']")
	WebElement user;
	
	@FindBy(css="#logoff")
	WebElement logOut;

	public HomePage(WebDriver driver) {
		super(driver);
	}
	
	public HomePage clickDocumentTab() {
		driver.switchTo().parentFrame();
		waitForElementToBeVisible(documentButton);
		new Actions(driver).moveToElement(documentButton).perform();
		documentButton.click();
		return this;
	}
	
	public HomePage clickMailTab() {
		driver.switchTo().parentFrame();
		waitForElementToBeVisible(mailButton);
		new Actions(driver).moveToElement(mailButton).perform();
		mailButton.click();
		return this;
	}
	
	public DocumentPage clickUploadDocumentLink() {
		driver.switchTo().activeElement();
		uploadDoc.click();
		return new DocumentPage(driver);
	}
	
	public DocumentPage clickDoumentRegister() {
		driver.switchTo().activeElement();
		docSearch.click();
		return new DocumentPage(driver);
	}
	
	public MailPage clickBlankMail() {
		driver.switchTo().activeElement();
		blankMail.click();
		return new MailPage(driver);		
	}

	public void logout() {
		driver.switchTo().parentFrame();
		new Actions(driver).moveToElement(user).perform();
		user.click();
		logOut.click();
		
	}
	
	

}
