package com.javastaff.test.e2e;

import io.cucumber.junit.CucumberOptions;
import io.cucumber.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(features = {"src/test/resources/bdd/register.feature"},
         plugin = {"pretty",
        "json:target/cucumber_reports/register_selenium.json",
        "html:target/cucumber_reports/register_selenium.html"},
        glue = {"com.javastaff.test.e2e.selenium"})
public class RegisterSeleniumTest {
}