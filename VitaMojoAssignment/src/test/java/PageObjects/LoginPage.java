package PageObjects;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

import org.openqa.selenium.WebElement;

import org.openqa.selenium.support.FindBy;

import org.openqa.selenium.support.PageFactory;

public class LoginPage {

	WebDriver driver;

	@FindBy(xpath = "//li[@data-test='auth-tab-register']")
	public WebElement createAccountTab;

	@FindBy(xpath = "//li[@data-test='auth-tab-login']")
	public WebElement loginTab;

	@FindBy(id = "firstName")
	public WebElement nameInput;

	@FindBy(name = "email")
	public WebElement emailInput;

	@FindBy(name = "password")
	public WebElement passwordInput;

	@FindBy(xpath = "//button[@data-test='auth-button']")
	public WebElement loginButton;
	
	@FindBy(xpath="//div[@type='danger']")
	public WebElement errorMessage;
	
	@FindBy(xpath="//span[@data-test='email-validation-msg']")
	public WebElement errorEmail;
	
	@FindBy(xpath="//span[@data-test='password-validation-msg']")
	public WebElement errorPassword;
	
	public LoginPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public String getEmailError() {
		return errorEmail.getText();
	}
	
	public String getPasswordError() {
		return errorPassword.getText();
	}
	
	public String getErrorMessage() {
		return errorMessage.getText();
	}	

	public void setName(String name) {
		nameInput.sendKeys(name);
	}

	public void setEmail(String email) {
		emailInput.sendKeys(email);
	}

	public void setPassword(String password) {
		passwordInput.sendKeys(password);
	}

	public void clickCreateAccountTab() {
		createAccountTab.click();
	}

	public void clickLoginTab() {
		loginTab.click();
	}

	public void clickLogin() {
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", loginButton);
		loginButton.click();
	}

}
