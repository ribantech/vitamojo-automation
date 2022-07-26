package PageObjects;

import org.openqa.selenium.WebDriver;

import org.openqa.selenium.WebElement;

import org.openqa.selenium.support.FindBy;

import org.openqa.selenium.support.PageFactory;

public class HomePage {

    WebDriver driver;

    @FindBy(xpath="//a[@data-test='preorder.account.auth.login-link']")
    public WebElement loginButton;

    public HomePage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void clickLoginButton(){
            loginButton.click();
    }  
    
    public void navigate(String url){
        driver.get(url);
    }
 
}
