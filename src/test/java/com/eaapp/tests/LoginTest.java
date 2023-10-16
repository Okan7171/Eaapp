package com.eaapp.tests;

import com.eaapp.pages.EmployeeListPage;
import com.eaapp.pages.LoginPage;
import com.eaapp.utilities.ConfigurationReader;
import com.eaapp.utilities.Driver;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Test;

public class LoginTest extends TestBase{

    /*TEST Scenario Steps:

            1- Navigate to "http://eaapp.somee.com/"
            2- Login as admin
            3- Go to Employee List page
            4- Create a new employee
            5- Verify the new employee info in Employee List page
            6- Delete the new employee
            7- Verify the new employee deleted in Employee List page

    Admin Credentials:
    UserName	: admin
    Password   	: password
   * /

     */
    LoginPage loginPage;
    EmployeeListPage employeeListPage;

    @Test
    public void logTest() throws InterruptedException {
        loginPage= new LoginPage();
        employeeListPage= new EmployeeListPage();
        driver.get(ConfigurationReader.get("url"));
        loginPage.loginButton.click();
        Thread.sleep(1000);
        loginPage.loginMethod();
       Thread.sleep(1000);
       employeeListPage.employeeListMethod();
       Thread.sleep(1000);
      // employeeListPage.createButton.click();
        employeeListPage.verifyNewEmployee();
        Thread.sleep(1000);

        JavascriptExecutor jse = (JavascriptExecutor) Driver.get();
        jse.executeScript("window.scrollBy(0,1000)");

        employeeListPage.deleteNewEmployee();
        Thread.sleep(1000);
        employeeListPage.verifyDeleteEmployee();
    }
}
