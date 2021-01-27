package com.syntax.class01;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class Homework {

    WebDriver driver;

    @BeforeMethod(alwaysRun = true)
    public void openAndNavigate() {

        System.setProperty("webdriver.chrome.driver", "drivers/chromedriver.exe");

        driver = new ChromeDriver();

        //driver.manage().window().maximize();

        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

        driver.get("http://hrm.syntaxtechs.net/humanresources/symfony/web/index.php/auth/login");

    }

      @Test(priority = 1, enabled = true) // we can specify more than one, as requires
    public void invalidCredentials(){

        driver.findElement(By.id("txtUsername")).sendKeys("Admin");
        driver.findElement(By.id("txtPassword")).sendKeys("Hum@nhrm111");
        driver.findElement(By.id("btnLogin")).click();

        WebElement invalidCredentials = driver.findElement(By.xpath("//span[@id = 'spanMessage']"));
        String invalidCredentialsText = invalidCredentials.getText();
        System.out.println(invalidCredentialsText);
        String expectedErrorText = "Invalid credential";

//        if (invalidCredentialsText.equals(expectedErrorText)) {
//            boolean isDisplayed = invalidCredentials.isDisplayed();
//            if (isDisplayed) {
//                System.out.println("The test PASS. The text is MATCHING and displayed => " + isDisplayed);
//            }
//        }else {
//            System.out.println("The test PASS. The text is NOT displayed");
//        }

          System.out.println("My code before the assertion");
          Assert.assertEquals(invalidCredentialsText, expectedErrorText, "Not correct message is displayed. ");
          // Assert.assertEquals(invalidCredentialsText, expectedErrorText, "Not correct message is displayed");
          // we use Accert class to verify and for validation. And also, we can add our message in double quotes

          System.out.println("My code after the assertion");


    }

    @Test(priority = 0)

    public void syntaxLogo() {
        WebElement logo = driver.findElement(By.xpath("//div[@id = 'divLogo']"));
        boolean isLogoPresent =logo.isDisplayed();

//        if (isLogoPresent) {
//            System.out.println("Syntax Logo is displayed => " + isLogoPresent + " Test PASS");
//        } else {
//            System.out.println("Syntax Logo is displayed => " + isLogoPresent + " Test FAIL");
//        }

        Assert.assertTrue(isLogoPresent);
        // for boolean we use assertTrue or assertFalse
    }

    @AfterMethod
    public void closeBrowser() {
        driver.quit();
    }
}

