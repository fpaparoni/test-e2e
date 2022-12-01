package com.javastaff.test.e2e.selenium;

import org.openqa.selenium.WebDriver;

public class BaseStep {

    protected WebDriver driver;
    protected Wait wait;

    public BaseStep() {
        this.driver = Setup.driver;
        this.wait = new Wait(this.driver);
    }
}