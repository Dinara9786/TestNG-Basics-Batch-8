package com.syntax.class01;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class LoginTest {

    WebDriver driver;

    @BeforeMethod(alwaysRun = true)
    public void openAndNavigate() {

        System.setProperty("webdriver.chrome.driver", "drivers/chromedriver.exe");

        driver = new ChromeDriver();

        //driver.manage().window().maximize();

        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

        driver.get("http://hrmstest.syntaxtechs.net/humanresources/symfony/web/index.php/auth/login");

    }

    @Test(priority = 0, groups ="smoke")
    public void validAdminLogin() {
        driver.findElement(By.id("txtUsername")).sendKeys("Admin");
        driver.findElement(By.id("txtPassword")).sendKeys("Hum@nhrm123");
        driver.findElement(By.id("btnLogin")).click();

        WebElement welcomeMessage = driver.findElement(By.cssSelector("a#welcome"));
        if (welcomeMessage.isDisplayed()) {
            System.out.println("The test PASS");
        } else {
            System.out.println("The test FAIL");
        }
    }

    @Test(priority=1, groups = "smoke")
    public void titleValidation() {

        String actualTitle = driver.getTitle();
        String expectedTitle = "Human Management System";
        if (expectedTitle.equals(actualTitle)) {
            System.out.println("The title is valid. The Test Pass");
        } else {
            System.out.println("The title is not matched. The Test Failed");
        }

    }

    @Test(groups = "smoke", priority = 2, enabled = true) // we can specify more than one, as requires
    public void invalidCredentials(){

        driver.findElement(By.id("txtUsername")).sendKeys("Admin");
        driver.findElement(By.id("txtPassword")).sendKeys("Hum@nhrm1123");
        driver.findElement(By.id("btnLogin")).click();

        WebElement invalidCredentials = driver.findElement(By.xpath("//span[@id = 'spanMessage']"));
        String invalidCredentialsText = invalidCredentials.getText();
        System.out.println(invalidCredentialsText);
        String expectedErrorText = "Invalid credentials";

        if (invalidCredentialsText.equals(expectedErrorText)) {
            boolean isDisplayed = invalidCredentials.isDisplayed();
            if (isDisplayed) {
                System.out.println("The test PASS. The text is MATCHING and displayed => " + isDisplayed);
            }
        }else {
            System.out.println("The test PASS. The text is NOT displayed");
        }
    }

    @Test(priority = 3, groups = "smoke")

    public void syntaxLogo() {
        WebElement logo = driver.findElement(By.xpath("//div[@id = 'divLogo']"));
        boolean isLogoPresent =logo.isDisplayed();

        if (isLogoPresent) {
            System.out.println("Syntax Logo is displayed => " + isLogoPresent + " Test PASS");
        } else {
            System.out.println("Syntax Logo is displayed => " + isLogoPresent + " Test FAIL");
        }
    }

    @AfterMethod
    public void closeBrowser() {
        driver.quit();
    }
}



