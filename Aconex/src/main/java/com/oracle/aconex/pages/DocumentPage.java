package com.oracle.aconex.pages;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

import com.oracle.aconex.base.BasePage;
/**
 * Page Object holding all elements after clicking Document tab and 
 * various operations that can be performed on the elements
 * present
 * 
 * @author rakesh
 *
 */
public class DocumentPage extends BasePage {
	
	@FindBy(css="iframe#frameMain")	//main frame
	WebElement mainFrame;
	
	@FindBy(css="input#docno_auto_0_chk")
	WebElement autoDocNumber;
	
	@FindBy(css="input[title*='Document Revision Number/Letter']")
	WebElement revision;
	
	@FindBy(css="input[title*='Title']")
	WebElement title;
	
	@FindBy(css="#doctype_0")
	WebElement type;
	
	@FindBy(css="#docstatus_0")
	WebElement status;
	
	@FindBy(css="#discipline_0")
	WebElement discipline; 
	
	@FindBy(css="#revisiondate_0_da")
	WebElement revisionDate;
	
	@FindBy(css="button#btnUploadDocument.uiButton.primary")
	WebElement upload;
	
	@FindBy(css="ul#regSuccess-messages div b")
	WebElement upLoadSuccess;
	
	@FindBy(css="div.uiPanel-closeBox")
	WebElement close;
	
	@FindBy(css="#search-keywords-id")
	WebElement searchBox;
	
	@FindBy(xpath="//div[@class='searchActions-item']/button[contains(text(),'Search')]")
	WebElement srchBtn;
	
	WebElement docAvaiable;	
	WebElement docReturnedTable;

	public DocumentPage(WebDriver driver) {
		super(driver);
	}
	
	public DocumentPage selectAutoNumber() {
		driver.switchTo().frame(mainFrame);
		waitForElementToBeVisible(autoDocNumber);
		if(!autoDocNumber.isSelected())
			autoDocNumber.click();
		return this;
	}
	
	public DocumentPage setRevision(String _revision) {
		revision.sendKeys(_revision);
		return this;
	}

	public DocumentPage setTitle(String _title) {
		title.sendKeys("Title");
		return this;
	}
	
	public DocumentPage selectType(String _title) {
		Select docType = new Select(type);
		docType.selectByVisibleText("Sample");
		return this;
	}
	
	public DocumentPage selectStatus(String _status) {
		Select docStatus = new Select(status);
		docStatus.selectByVisibleText("Draft");
		return this;
	}
	
	public DocumentPage selectDiscipline(String _discipline) {
		Select docDiscipline = new Select(discipline);
		docDiscipline.selectByVisibleText("Civil");
		return this;
	}
	
	public DocumentPage setRevisonDate(String _revisondate) {
		revisionDate.sendKeys(_revisondate);
		return this;
	}
	
	public DocumentPage clickUpload() {
		upload.click();
		return this;
	}
	
	public HomePage closeSuccessfulUpload() {
		docNo = upLoadSuccess.getText();
		new Actions(driver).moveToElement(close).perform();
		close.click();	
		return new HomePage(driver);
	}

	public DocumentPage setDocumentNumberToSearch() {
		driver.switchTo().frame(mainFrame);
		searchBox.clear();
		searchBox.sendKeys(docNo);
		return this;
	}
	
	public DocumentPage clickSearchForDocument() {
		try {
			TimeUnit.SECONDS.sleep(10);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		srchBtn.click();
		return this;
	}
	
	public boolean validateIfDocPresent() {
		waitForElementToBePresent(By.xpath(String.format("//div[contains(text(),'%s')]",docNo)));
		docAvaiable = driver.findElement(By.xpath(String.format("//div[contains(text(),'%s')]",docNo)));
		if(docAvaiable != null)	
			return true;
		return false;	
	}
	

	public HomePage returnToHomePage() {
		return new HomePage(driver);
	}


	
	

}
