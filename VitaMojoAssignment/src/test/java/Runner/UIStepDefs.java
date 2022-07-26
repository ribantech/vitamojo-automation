package Runner;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.time.Duration;
import java.util.Random;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import PageObjects.AccountPage;
import PageObjects.HomePage;
import PageObjects.LoginPage;

public class UIStepDefs {
	
	WebDriver driver;
	HomePage homePage;
	LoginPage loginPage;
	AccountPage accountPage;
	
    @Before("@ui")
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
 
        homePage = new HomePage(driver);
        loginPage = new LoginPage(driver);
        accountPage = new AccountPage(driver);
    }
    
	@Given("User navigates to the website {string}")
	public void user_navigates_to_the_website_https_fego_vmos_demo_com(String url) {
	    homePage.navigate(url);
	    new WebDriverWait(driver, Duration.ofSeconds(30)).until(ExpectedConditions.elementToBeClickable(homePage.loginButton));
	}
	
	@When("User navigates to login screen")
	public void user_navigate_to_login_page() {
	    homePage.clickLoginButton();
	    new WebDriverWait(driver, Duration.ofSeconds(30)).until(ExpectedConditions.elementToBeClickable(loginPage.loginTab));   
	}
	
	@When("User navigates to signup screen")
	public void user_navigate_to_signup_screen() {
	    homePage.clickLoginButton();
	    loginPage.clickCreateAccountTab();
	    
	}
	
	@When("Set valid email as {string}")
	public void setValidEmail(String email) {
		Random rand = new Random();	  
        int num = 1000+rand.nextInt(1000);
	    loginPage.setEmail(num+email);
	}
	
	@When("Set email as {string}")
	public void setEmail(String email) {
	    loginPage.setEmail(email);
	}
	
	@When("Set password as {string}")
	public void setPassword(String password) {
		loginPage.setPassword(password);
	}
	
	@When("Set name as {string}")
	public void setName(String name) {
		loginPage.setName(name);
	}	
	
	@Then("Login must be successful")
	public void login_must_be_successful() {
		loginPage.clickLogin();
		new WebDriverWait(driver, Duration.ofSeconds(30)).until(ExpectedConditions.visibilityOf(accountPage.profileLink));
		Assert.assertEquals(accountPage.getProfileName(), "Anirban Roy", "Profile name matches");
	}
	
	@Then("Signup must be successful")
	public void signup_must_be_successful() {
		loginPage.clickLogin();
		new WebDriverWait(driver, Duration.ofSeconds(30)).until(ExpectedConditions.visibilityOf(accountPage.profileLink));
		Assert.assertEquals(accountPage.getProfileName(), "name", "Profile name matches");
	}
	
	@Then("Login must not be successful with {string} and {string}")
	public void login_must_not_be_successful(String field, String message) {
		loginPage.clickLogin();

		switch(field) {
			case "email":
				new WebDriverWait(driver, Duration.ofSeconds(30)).until(ExpectedConditions.visibilityOf(loginPage.errorEmail));
				String emailMsg = loginPage.getEmailError();
				Assert.assertEquals(emailMsg, message, "Email error displays");
				break;
				
			case "password":
				new WebDriverWait(driver, Duration.ofSeconds(30)).until(ExpectedConditions.visibilityOf(loginPage.errorPassword));
				String passwordMsg = loginPage.getPasswordError();
				Assert.assertEquals(passwordMsg, message, "Password error displays");
				break;
				
			case "both":
				new WebDriverWait(driver, Duration.ofSeconds(30)).until(ExpectedConditions.visibilityOf(loginPage.errorEmail));
				new WebDriverWait(driver, Duration.ofSeconds(30)).until(ExpectedConditions.visibilityOf(loginPage.errorPassword));
				String emailMsg1 = loginPage.getEmailError();
				Assert.assertEquals(emailMsg1, message, "Email error displays");
				String passwordMsg1 = loginPage.getPasswordError();
				Assert.assertEquals(passwordMsg1, message, "Password error displays");
				break;
				
			case "error":
				new WebDriverWait(driver, Duration.ofSeconds(30)).until(ExpectedConditions.visibilityOf(loginPage.errorMessage));
				String errorMsg = loginPage.getErrorMessage();
				Assert.assertEquals(errorMsg, message, "Auth error displays");
				break;
		}
	
	}
	
	@Then("Signup must not be successful with {string} {string}")
	public void signup_must_not_be_successful(String field, String message) {
		loginPage.clickLogin();
		
		switch(field) {
			case "email":
				String emailMsg = loginPage.getEmailError();
				Assert.assertEquals(emailMsg, message, "Email error displays");
				break;
				
			case "password":
				String passwordMsg = loginPage.getPasswordError();
				Assert.assertEquals(passwordMsg, message, "Password error displays");
				break;
				
			case "both":
				String emailMsg1 = loginPage.getEmailError();
				Assert.assertEquals(emailMsg1, message, "Email error displays");
				String passwordMsg1 = loginPage.getPasswordError();
				Assert.assertEquals(passwordMsg1, message, "Password error displays");
				break;
				
			case "error":
				String errorMsg = loginPage.getPasswordError();
				Assert.assertEquals(errorMsg, message, "Password error displays");
				break;
		}
	
	}
	
	@After("@ui")
    public void teardown() {
        driver.quit();
    }

}
