package testsuite;

import browserfactory.BaseTest;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class LoginTest extends BaseTest {
    String baseUrl = "http://the-internet.herokuapp.com/login";

    @Before
    public void setUp(){
        openBrowser(baseUrl);
    }
    public void clickOnElement(By by){
        WebElement loginLink = driver.findElement(by);
        loginLink.click();
    }
    @Test
    public void UserShouldLoginSuccessfullyWithValidCredentials(){
        //Find the username Element and enter username in username field
        driver.findElement(By.id("username")).sendKeys("tomsmith");
        //Find the password element and enter the password in password field
        driver.findElement(By.name("password")).sendKeys("SuperSecretPassword!");
        //Click on login button
        driver.findElement(By.xpath("//i[@class='fa fa-2x fa-sign-in']")).click();
        //Verify text "Secure Area"
        String expectedMessage = "Secure Area";
        WebElement actualTextMessageElement = driver.findElement(By.xpath("//h2[text()= ' Secure Area']"));
        String actualMessage = actualTextMessageElement.getText();
        Assert.assertEquals(expectedMessage, actualMessage);
    }
    @Test
    public void verifyTheUsernameErrorMessage(){
        //Find the username Element and enter username in username field
        driver.findElement(By.id("username")).sendKeys("tomsmith1");
        //Find the password element and enter the password in password field
        driver.findElement(By.name("password")).sendKeys("SuperSecretPassword!");
        //Click on login button
        driver.findElement(By.xpath("//i[@class='fa fa-2x fa-sign-in']")).click();
        //Verify the error message "your username is invalid!"
        String expectedMessage = "Your username is invalid!\n" + "×";
        WebElement actaulTextMessageElement = driver.findElement(By.xpath("//div[@class='flash error']"));
        String actualMessage = actaulTextMessageElement.getText();
        Assert.assertEquals(expectedMessage, actualMessage);
    }
    @Test
    public void verifyThePasswordErrorMessage(){
        //Find the username Element and enter username in username field
        driver.findElement(By.id("username")).sendKeys("tomsmith");
        //Find the password element and enter the password in password field
        driver.findElement(By.name("password")).sendKeys("SuperSecretPassword");
        //Click on login button
        driver.findElement(By.xpath("//i[@class='fa fa-2x fa-sign-in']")).click();
        //Verify the error message "your password is invalid
        String expectedMessage = "Your password is invalid!\n" + "×";
        WebElement actaulTextMessageElement = driver.findElement(By.xpath("//div[@class='flash error']"));
        String actualMessage = actaulTextMessageElement.getText();
        Assert.assertEquals(expectedMessage, actualMessage);
    }
    @After
    public void tearDown(){
        closeBrowser();
    }
}
