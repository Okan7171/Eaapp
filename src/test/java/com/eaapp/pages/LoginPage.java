package com.eaapp.pages;

import com.eaapp.utilities.ConfigurationReader;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage{

 @FindBy (id= "loginLink")
    public WebElement loginButton;

 @FindBy (xpath = "//input[@data-val-required='The UserName field is required.']")
    public WebElement userNameBox;

 @FindBy (xpath = "//input[@data-val-required='The Password field is required.']")
    public WebElement passWordBox;

 @FindBy (xpath = "//input[@type='submit']")
    public WebElement loginBox;




 public void loginMethod (){
     String userName= ConfigurationReader.get("userName");
     String passWord= ConfigurationReader.get("passWord");
     loginButton.click();
     userNameBox.sendKeys(userName);
     passWordBox.sendKeys(passWord);
     loginBox.click();
 }

}
