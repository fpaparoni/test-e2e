package com.javastaff.test.e2e.playwright;

import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;

import io.cucumber.java.After;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class RegisterSteps extends BaseStep {
	
	@Given("the registration form")
	public void the_registration_form() {
		page.navigate("http://localhost/");
		page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("Register")).click();
	}

	@When("I enter no fields")
	public void i_enter_no_fields() {
		System.out.println("Nothing...");
	}

	@When("I enter {string} as {string} field")
	public void i_enter_as_field(String value, String field) {
		FieldInfo fieldInfo = FieldInfo.getFieldInfo(field);
		page.getByRole(AriaRole.TEXTBOX).nth(fieldInfo.getPos()).click();
		page.getByRole(AriaRole.TEXTBOX).nth(fieldInfo.getPos()).fill(value);
	}

	@When("I submit the form to register")
	public void i_submit_the_form_to_register() {
		page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Register")).click();
	}

	@Then("I receive an error related to {string} field required")
	public void i_receive_an_error_related_to_field_required(String field) {
		FieldInfo fieldInfo = FieldInfo.getFieldInfo(field);
		page.waitForSelector("text="+fieldInfo.getLabel() + " is required");
	}

	@Then("I receive no error")
	public void i_receive_no_error() {
		page.waitForSelector("text=Registrationz");
	}
	
	@After
	public void close() {
		context.close();
		page.close();
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
				pos=0;
				label="First Name";
				break;
			case "Last Name":
				pos=1;
				label="Last Name";
				break;
			case "Username":
				pos=2;
				label="Username";
				break;
			case "Password":
				pos=3;
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

