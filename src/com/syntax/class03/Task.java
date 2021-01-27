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

import java.util.concurrent.TimeUnit;

public class Task {

    WebDriver driver;
    public static String url = "http://hrmstest.syntaxtechs.net/humanresources/symfony/web/index.php/auth/login";

    @BeforeMethod (alwaysRun = true)
    public void openAndNavigate (){
        driver=new ChromeDriver();

        System.setProperty("webdriver.chrome.driver", "drivers/chromedriver.exe");
        driver.navigate().to(url);
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
    }

    @AfterMethod (alwaysRun = true)
    public void closeBrowser() {
        driver.quit();
    }

    @Test (dependsOnMethods = "getData")
    public void loginAndValidate(String username, String password, String name) throws InterruptedException {
        driver.findElement(By.id("txtUsername")).sendKeys(username);
        driver.findElement(By.id("txtPassword")).sendKeys(password);
        Thread.sleep(3000);
        driver.findElement(By.id("btnLogin")).click();
        Thread.sleep(3000);
        // validating that we are logged in
        WebElement welcomeMessage = driver.findElement(By.cssSelector("a#welcome"));
        Thread.sleep(3000);
        Assert.assertEquals(welcomeMessage, "Welcome message is not displayed");

    }

//    @DataProvider
//    public void getData (){
//        Object [][] names = {
//                {"Admin", "Hum@nhrm123", "John"},
//                {"MariaP", "Syntax123!", "Maria"},
//                {"JohnM", "Syntax123!!", "John"},
//                {"JaneDole", "Syntax123!!!", "Jane"},
//                {"dinara", "Syntax123!!!!", "Dinara"}
//        };
//
//    }



}
