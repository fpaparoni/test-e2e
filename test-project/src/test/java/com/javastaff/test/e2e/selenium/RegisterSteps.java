package com.javastaff.test.e2e.selenium;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class RegisterSteps extends BaseStep{
	
	@Given("the registration form")
	public void the_registration_form() {
		driver.get("http://localhost");
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		ExpectedCondition<WebElement> condition = ExpectedConditions.elementToBeClickable(By.cssSelector(".btn-primary"));
		wait.until(condition);
		driver.findElement(By.linkText("Register")).click();
		ExpectedCondition<WebElement> condition2 = ExpectedConditions.elementToBeClickable(By.cssSelector(".btn-primary"));
		wait.until(condition2);
	}

	@When("I enter no fields")
	public void i_enter_no_fields() {
	    System.out.println("Nothing...");
	}
	
	@When("I enter {string} as {string} field")
	public void i_enter_as_field(String value, String field) {
		FieldInfo fieldInfo=FieldInfo.getFieldInfo(field);
		driver.findElement(By.cssSelector(".form-group:nth-child("+fieldInfo.getPos()+") > .form-control")).sendKeys(value);
	}

	@When("I submit the form to register")
	public void i_submit_the_form_to_register() {
		WebElement element=driver.findElement(By.cssSelector(".btn-primary"));
	    element.click();
	}

	@Then("I receive an error related to {string} field required")
	public void i_receive_an_error_related_to_field_required(String field) {
		FieldInfo fieldInfo=FieldInfo.getFieldInfo(field);
		
		assertThat(driver.findElement(By.cssSelector(".form-group:nth-child("+fieldInfo.getPos()+") > .invalid-feedback > div")).getText(), is(fieldInfo.getLabel()+" is required"));
		
		List<WebElement> elements = driver.findElements(By.cssSelector(".form-group:nth-child("+fieldInfo.getPos()+") > .invalid-feedback > div"));
	    assert(elements.size() > 0);
	}
	
	@Then("I receive no error")
	public void i_receive_no_error() {
		//Back to login page
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		ExpectedCondition<Boolean> condition = ExpectedConditions.textToBe(By.cssSelector(".card-header"),"Login");
		wait.until(condition);
		Wait wait2=new Wait(driver);
		wait2.forLoading(30);
		//Check positive outcome
		assertThat(driver.findElement(By.cssSelector("span")).getText(), is("Registration successful"));
	}
	
}

class FieldInfo {
	String label;
	int pos;
	
	public String getLabel() {
		return label;
	}
	public void setLabel(String label) {
		this.label = label;
	}
	public int getPos() {
		return pos;
	}
	public void setPos(int pos) {
		this.pos = pos;
	}
	
	public static FieldInfo getFieldInfo(String field) {
		int pos=0;
		String label="";
		switch (field) {
			case "First Name":
				pos=1;
				label="First Name";
				break;
			case "Last Name":
				pos=2;
				label="Last Name";
				break;
			case "Username":
				pos=3;
				label="Username";
				break;
			case "Password":
				pos=4;
				label="Password";
				break;
			default:
				pos=-1;
				break;
		} 
		FieldInfo fieldInfo=new FieldInfo();
		fieldInfo.setLabel(label);
		fieldInfo.setPos(pos);
		return fieldInfo;
	}
}

