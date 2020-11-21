package com.oracle.aconex.tests;

import static org.testng.Assert.assertTrue;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.oracle.aconex.base.BaseTest;
import com.oracle.aconex.pages.DocumentPage;
import com.oracle.aconex.pages.HomePage;
import com.oracle.aconex.pages.LoginPage;
/**
 * Test Class containing document related test cases
 *  on Aconex Application.
 * 
 * @author rakesh
 *
 */
public class AconexDocumentTests extends BaseTest {
	LoginPage loginPage;
	HomePage homePage;
	DocumentPage documentPage;
	
	String _user;
	String _pwd;

	// all tasks required before each method
	@BeforeMethod
	public void setUp() {
		loginPage = new LoginPage(getDriver());
		_user = prop.getProperty("doc.username");
		_pwd = prop.getProperty("doc.password");
		
	}
	
	//preparing objects to be garbage cleaned after each method
	@AfterMethod
	public void tearDown() {
		loginPage = null;
		homePage = null;
		documentPage = null;
	}
    
	/**
	 * Test method to test steps for uploading
	 * a new document. Steps include
	 * 1. Open login page
	 * 2. Click on document tab and select sub-menu to upload document
	 * 3. Enter in all required fields
	 * 4. Grab document number once uploaded
	 * 5. Click on document tab and select sub-menu to search document
	 * 6. Search for document number grabbed to verify if result generated
	 * 
	 */
	@Test
    public void testSuccessfulDocumentUpload() {
		// all sample inputs required are below. this can be passed through @dataprovider 
		// if data driven framework considered
		String _revision = "1";
		String _title = "Title";
		String _type = "Sample";
		String _status = "Draft";
		String _discipline = "Civil";
		boolean isSuccessful = false;
		
		//Login Page interactions
		homePage = loginPage.setUsername(_user)
		.setPassword(_pwd)
		.clickLogin();
		
		//Main Page access after logging in
		documentPage = homePage.clickDocumentTab()
		.clickUploadDocumentLink();
		
		//upload document related operations
		homePage = documentPage.selectAutoNumber()
		.setRevision(_revision)
		.setTitle(_title)
		.selectType(_type)
		.selectStatus(_status)
		.selectDiscipline(_discipline)
		.setRevisonDate(getPresentDate())
		.clickUpload()
		.closeSuccessfulUpload();
		
		//search document that was uploaded
		documentPage = homePage.clickDocumentTab()
		.clickDoumentRegister();
		isSuccessful = documentPage.setDocumentNumberToSearch()
		.clickSearchForDocument()
		.validateIfDocPresent();
		
		documentPage.returnToHomePage().logout();
		
		//verify if search was successful
		assertTrue(isSuccessful);
    }
	
	/**
	 * Method to get the current date
	 * 
	 * @return date in string format
	 */
	private String getPresentDate() {
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		Date today = Calendar.getInstance().getTime();
		return dateFormat.format(today);
	}

}
