package com.javastaff.test.e2e.playwright;

import java.nio.file.Paths;
import java.util.Optional;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;

import io.cucumber.java.Before;

public class Setup {
    
    public static Page page;
    public static BrowserContext context;
    public static Browser browser;

    @Before
    public void setBrowser() throws Exception {
    	BrowserType browserType = null;
    	String browserTypeAsString=Optional.ofNullable(System.getProperty("test-browser")).orElse("chromium");
		switch (browserTypeAsString) {
		case "firefox":
			browserType = Playwright.create().firefox();
			break;
		case "chromium":
			browserType = Playwright.create().chromium();
			break;
		case "webkit":
			browserType = Playwright.create().webkit();
			break;
		default:
			browserType = Playwright.create().chromium();
			break;
		}
		if (browserType == null) {
			throw new IllegalArgumentException("Could not launch a browser for type " + browserTypeAsString);
		}
		browser = browserType.launch(new BrowserType.LaunchOptions().setHeadless(false).setSlowMo(300));
		var videoPath = Paths.get("videos/");

	    var contextOptions = new Browser.NewContextOptions()
	            .setRecordVideoDir(videoPath);
		context = browser.newContext(contextOptions);
		context.setDefaultTimeout(5000);
		page = context.newPage();
    }
}
