package com.javastaff.test.e2e.playwright;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.Page;

import io.cucumber.java.After;
import io.cucumber.java.Scenario;

public class TearDown {

	protected Page page;
	protected BrowserContext context;
	protected Browser browser;

    public TearDown() {
        this.page = Setup.page;
        this.context = Setup.context;
        this.browser = Setup.browser;
    }

    @After
    public void quitDriver(Scenario scenario) throws IOException{
    	context.close();
		page.close();
        browser.close();
        Path path = page.video().path();
        if (scenario.isFailed()) {
            byte[] buffer = Files.readAllBytes(path);
            scenario.attach(buffer,"video/webm", scenario.getName()+".webm");
        } else {
        	Files.delete(path);
        }
    }
}
