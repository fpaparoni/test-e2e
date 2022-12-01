package com.javastaff.test.e2e.selenium;

import io.cucumber.java.Before;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;

public class Setup {

    public static WebDriver driver;

    @Before
    public void setWebDriver() throws Exception {
    	if ("firefox".equals(System.getProperty("test-browser"))) {
    		System.setProperty("webdriver.gecko.driver","/home/federico/progetti/test/bdd/geckodriver-v0.32.0-linux64/geckodriver");
    		driver = new FirefoxDriver();
        } else {
        	System.setProperty("webdriver.chrome.driver","/home/federico/progetti/test/bdd/chromedriver_linux64/chromedriver");
    		ChromeOptions chromeOptions = new ChromeOptions();
    		chromeOptions.addArguments("['start-maximized']");
    		driver = new ChromeDriver(chromeOptions);
    	}
    	driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));
    }
}
