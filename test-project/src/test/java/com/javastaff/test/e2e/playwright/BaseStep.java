package com.javastaff.test.e2e.playwright;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.Page;

public class BaseStep {
	protected Page page;
	protected BrowserContext context;
	protected Browser browser;

    public BaseStep() {
        this.page = Setup.page;
        this.context = Setup.context;
        this.browser = Setup.browser;
    }
}
