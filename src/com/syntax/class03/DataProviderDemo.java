package com.syntax.class03;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.sql.Driver;
import java.util.concurrent.TimeUnit;

public class DataProviderDemo {

    // access modifiers = public -> protected -> default -> private

    WebDriver driver;

    @BeforeMethod(alwaysRun = true)
    public void openAndNavigate() {

        System.setProperty("webdriver.chrome.driver", "drivers/chromedriver.exe");

        driver = new ChromeDriver();
        driver.navigate().to("http://hrmstest.syntaxtechs.net/humanresources/symfony/web/index.php/auth/login");
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

    }

    @AfterMethod(alwaysRun = true)
    public void closeBrowser() {
        driver.quit();
    }

    @Test(dataProvider = "loginData", groups = "regression")
    public void multipleLogin(String username, String password) throws InterruptedException {

        driver.findElement(By.id("txtUsername")).sendKeys(username);
        driver.findElement(By.id("txtPassword")).sendKeys(password);
        Thread.sleep(3000);
        driver.findElement(By.id("btnLogin")).click();
        Thread.sleep(3000);
        // validating that we are logged in
        WebElement welcomeMessage = driver.findElement(By.cssSelector("a#welcome"));
        Thread.sleep(3000);
        //Assert.assertEquals(welcomeMessage.isDisplayed(), "Welcome message is not displayed");

    }

    @DataProvider
    public Object[][] loginData() {
        Object[][] data = {
                {"Admin", "Hum@nhrm123"},
                {"MariaP", "Syntax123!"},
                {"JohnM", "Syntax123!!"}
        };
        return data;
    }

    }


