package com.oracle.aconex.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.oracle.aconex.base.BasePage;
/**
 * Page Object holding all elements in first page to Login to app and 
 * various operations that can be performed on the elements
 * present
 * 
 * @author rakesh
 *
 */
public class LoginPage extends BasePage {
	@FindBy(id="userName")
	WebElement username;
	
	@FindBy(id="password")
	WebElement password;
	
	@FindBy(id="login")
	WebElement login;
	
	public LoginPage(WebDriver driver) {
		super(driver);
	}
	
	public LoginPage setUsername(String _username) {
		username.sendKeys(_username);
		return this;
	}
	
	public LoginPage setPassword(String _password) {
		password.sendKeys(_password);
		return this;
	}
	
	public HomePage clickLogin() {
		login.submit();
		return new HomePage(driver);
	}
	
	
	

}
