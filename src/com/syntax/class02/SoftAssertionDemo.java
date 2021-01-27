package com.syntax.class02;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.json.JsonOutput;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.concurrent.TimeUnit;

public class SoftAssertionDemo {

    // US: As an admin I should be able to login into HRMS.
    // logo is displayed
    // user is successfully logged in

    WebDriver driver;

    @BeforeMethod(alwaysRun = true)
    public void openAndNavigate() {

        System.setProperty("webdriver.chrome.driver", "drivers/chromedriver.exe");

        driver = new ChromeDriver();

        //driver.manage().window().maximize();
        driver.navigate().to("http://hrmstest.syntaxtechs.net/humanresources/symfony/web/index.php/auth/login");
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

    }

    @AfterMethod
    public void closeBrowser() {
        driver.quit();
    }

    @Test (groups = "regression")
    public void logoAndValidLogin() {

        // verifying that logo is displayed

        WebElement element = driver.findElement(By.xpath("//div[@id = 'divLogo']"));
        SoftAssert softAsert = new SoftAssert();

        softAsert.assertTrue(element.isDisplayed(), "The Logo is NOT displayed.");

        // entering valid credentials to login
        String username = "Admin";
        driver.findElement(By.id("txtUsername")).sendKeys(username);
        driver.findElement(By.id("txtPassword")).sendKeys("Hum@nhrm123");
        driver.findElement(By.id("btnLogin")).click();

        // validating that we are logged in

        WebElement welcomeMessage = driver.findElement(By.cssSelector("a#welcome"));

        softAsert.assertTrue(welcomeMessage.isDisplayed(), "Welcome message is NOT displayed");
        softAsert.assertEquals(welcomeMessage.getText(), "Welcome " + username);
        System.out.println("End of the test case");
        softAsert.assertAll();// soft Assert show me everything
        // soft assert = collects all fails. And in the end we need to call method assertAll();

    }
}

