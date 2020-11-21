package com.oracle.aconex.tests;

import static org.testng.Assert.assertTrue;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.oracle.aconex.base.BaseTest;
import com.oracle.aconex.pages.HomePage;
import com.oracle.aconex.pages.LoginPage;
import com.oracle.aconex.pages.MailPage;
import com.oracle.aconex.pages.SearchDirectoryPage;
/**
 * Test Class containing mail related test cases
 * on Aconex Application.
 * 
 * @author rakesh
 */
public class AconexMailTests extends BaseTest {
	
	LoginPage loginPage;
	HomePage homePage;
	MailPage mailPage;
	SearchDirectoryPage srchDirPage;
	
	String _username,_password;
	
	//operations before every test methods
	@BeforeMethod
	public void setup() {
		loginPage = new LoginPage(getDriver());
		_username = prop.getProperty("mail.username");
		_password = prop.getProperty("mail.password");
	}
	
	//enabling objects to be available for garbage collection after every method
	@AfterMethod
	public void tearDown() {
		loginPage = null;
		homePage = null;
		mailPage = null;
		srchDirPage = null;
	}
	
	/**
	 * Method to test sending a mail and verifying
	 * if the mail was sent.Steps involved
	 * 1. Login to application
	 * 2. Click Mail menu and sub-menu to create a new mail
	 * 3. Enter all required details
	 * 4. Send and verify if mail was sent
	 */
    @Test
    public void testSuccessfulMail() {
		// all sample inputs required are below. this can be passed through @dataprovider 
		// if data driven framework considered
    	String mail_type = "Meeting Minutes";
    	String _groupNameInSearch = "Kenton";
    	String _familyNameInSearch = "Tillman";
    	String mailSubject = "Test";
    	String viewMailText = "View Mail";
    	
    	//Login Page interactions
    	homePage = loginPage.setUsername(_username)
    	.setPassword(_password)
    	.clickLogin();

    	//Main Page access after logging in
    	mailPage = homePage.clickMailTab()
    	.clickBlankMail();
    	// access to Mail Page and its operations
    	srchDirPage = mailPage.selectMailType(mail_type)
    			.clickToDirectory();
    	mailPage = srchDirPage.searchGlobalInDirectory()
    	.setGroupNameInSearch(_groupNameInSearch)
    	.setFamilyNameInSearch(_familyNameInSearch)
    	.clickSearchDirectory()
    	.selectRecipient(_groupNameInSearch, _familyNameInSearch)
    	.clickToBtn()
    	.clickOk();
    	mailPage.setMailSubject(mailSubject)
    	.selectAttributes();
 
    	boolean isMailSent = mailPage.clickSendMailAndConfirm(viewMailText);
    	
    	mailPage.returnToHomePage().logout();
    	//verify if mail was sent
    	assertTrue(isMailSent);

    }
}
