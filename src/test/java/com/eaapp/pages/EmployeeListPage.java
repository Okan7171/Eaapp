package com.eaapp.pages;

import com.eaapp.utilities.BrowserUtils;
import com.eaapp.utilities.Driver;
import com.github.javafaker.Faker;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

import java.util.List;

public class EmployeeListPage extends BasePage{
    Actions actions;
    Faker faker= new Faker();

    String expectedName;
    String expectedEmail;

    int size;


    @FindBy(xpath = "//a[text()='Employee List']")
    public WebElement employeeListTab;

    @FindBy (xpath = "//a[text()='Create New']")
    public WebElement createNewText;

    @FindBy (xpath = "//input[@name='Name']")
    public WebElement nameBox;

    @FindBy (xpath = "//input[@value='Create']")
    public WebElement createButton;

    @FindBy (xpath = "//input[@value='Delete']")
    public WebElement deleteButton;


    public void employeeListMethod(){
        actions=new Actions(Driver.get());
        employeeListTab.click();
        createNewText.click();

        expectedName= faker.name().firstName();
         expectedEmail= faker.internet().emailAddress();

        actions.click(nameBox)
                .sendKeys(expectedName+ Keys.TAB)
                .sendKeys("30000" + Keys.TAB)
                .sendKeys("2" + Keys.TAB)
                .sendKeys("1" + Keys.TAB)
                .sendKeys(expectedEmail +Keys.TAB+ Keys.ENTER).perform();

    }
    
    public void verifyNewEmployee(){
        List<WebElement> elements = Driver.get().findElements(By.xpath("//tbody/tr"));
        size = elements.size();
        List<WebElement> elements1 = Driver.get().findElements(By.xpath("(//tbody/tr)[" + size + "]//td"));
        Assert.assertEquals(expectedName, elements1.get(0).getText());
        Assert.assertEquals(expectedEmail, elements1.get(4).getText());

    }

    public void deleteNewEmployee(){
        WebElement element = Driver.get().findElement(By.xpath("(//tbody/tr)[" + size + "]//td//a[text()='Delete']"));
        BrowserUtils.clickWithJS(element);
        BrowserUtils.waitFor(1);
        deleteButton.click();

    }

    public void verifyDeleteEmployee(){
        List<WebElement> elements = Driver.get().findElements(By.xpath("//tbody/tr"));
        int size1 = elements.size();
        Assert.assertEquals(size1+1, size);
    }

}
