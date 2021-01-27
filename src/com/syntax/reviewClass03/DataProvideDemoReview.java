package com.syntax.reviewClass03;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class DataProvideDemoReview {

    public static WebDriver driver;

    @BeforeMethod(alwaysRun = true)
    public void openBrowser() {
        System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "/drivers/chromedriver.exe");
        driver = new ChromeDriver();
        driver.get("http://hrmstest.syntaxtechs.net/humanresources/symfony/web/index.php/auth/login");
        // driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
    }

    @AfterMethod(alwaysRun = true)
    public void closeBrowser() {
        driver.quit();
    }

    @Test (dataProvider = "getData", groups = "regression")
    public void multipleLogin(String username, String password, String name) throws InterruptedException {
        driver.findElement(By.id("txtUsername")).sendKeys(username);
        driver.findElement(By.id("txtPassword")).sendKeys(password);
        Thread.sleep(4000);
        driver.findElement(By.cssSelector("input#btnLogin")).click();
        Thread.sleep(3000);
        String welcomeText = driver.findElement(By.id("welcome")).getText();
        Assert.assertEquals(welcomeText, "Welcome " + name);


    }

    @DataProvider
    public Object [][] getData() {

        Object[][] data = {
                {"Admin", "Hum@nhrm123", "Admin"},
                {"MariaP", "Syntax123!", "Maria"},
                {"JohnM", "Syntax123!!", "John"}
        };
        return data;
    }

}
