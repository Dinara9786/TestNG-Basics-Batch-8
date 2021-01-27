package com.syntax.class02;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.concurrent.TimeUnit;

public class SoftAssertionDemo2 {

    // as an Admin I should be able to login into HRMS
    // we need to verify that logo is displayed
    // user is successfully logged in

public static WebDriver driver;

@BeforeMethod
    public void openAndNavigate() {
    System.setProperty("webdriver.chrome.driver", "drivers/chromedriver.exe");
    driver = new ChromeDriver();
    driver.manage().window().maximize();
    driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

    driver.get("http://hrm.syntaxtechs.net/humanresources/symfony/web/index.php/auth/login");
}

@Test
    public void logoAndValidLogin (){
    // verifying logo is displayed
    WebElement element = driver.findElement(By.xpath("//div[@id = 'divLogo']/img"));

    // creating an object of soft assertion
    SoftAssert softAssert = new SoftAssert();
    softAssert.assertTrue(element.isDisplayed(), "Logo is not displayed");

    // entering valid credentials to login
    String username = "Admin";
    String password = "Hum@nhrm123";
    driver.findElement(By.id("txtUsername")).sendKeys(username);
    driver.findElement(By.id("txtPassword")).sendKeys(password);
    driver.findElement(By.id("btnLogin")).click();

    // validating that user logged in

    WebElement welcomeMessage = driver.findElement(By.cssSelector("a#welcome"));
    String welcomeMessageText = welcomeMessage.getText();
    System.out.println(welcomeMessageText);
    String actualMessageText = "Welcome " + username;
    softAssert.assertTrue(welcomeMessage.isDisplayed(), "Welcome Message is not displayed");
    softAssert.assertEquals(welcomeMessageText, actualMessageText, "The welcome message is not correct");
    System.out.println("End of the test case");
    softAssert.assertAll();

}
    @AfterMethod
    public void closeBrowser(){
        driver.close();;
    }
}
