package com.oracle.aconex.base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

public class BaseTest {
	
	private static final int IMPLICIT_WAIT = 10;
	private static final int PAGE_TIME_OUT = 100;
	private WebDriver driver;
	public static Properties prop;
	

    @BeforeClass
    public void beforeClass() {
    	
    	System.setProperty("headless", "false"); 
        String headless = System.getProperty("headless");
        System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver.exe");
        
        //initiate a webdriver
        if("true".equals(headless)) {
            ChromeOptions chromeOptions = new ChromeOptions();
            chromeOptions.addArguments("--headless");
            driver = new ChromeDriver(chromeOptions);
        } else {
            driver = new ChromeDriver();
        }
        
        //loading properties from properties file
        try {
			prop = new Properties();
			FileInputStream configFile = new FileInputStream("src/test/resources/config.properties");
			prop.load(configFile);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
        driver.manage().window().maximize();
        driver.manage().timeouts().pageLoadTimeout(PAGE_TIME_OUT, TimeUnit.SECONDS);
        driver.manage().timeouts().implicitlyWait(IMPLICIT_WAIT, TimeUnit.SECONDS);
 
        driver.get(prop.getProperty("url"));
        
    }

    @AfterClass
    public void afterClass() {
        if(null != driver) {
            //close all windows
             driver.quit();
        }
    }

    /**
     * Method to get the webdirver
     * 
     * @return webdriver object
     */
    public WebDriver getDriver() {
        return driver;
    }

}
