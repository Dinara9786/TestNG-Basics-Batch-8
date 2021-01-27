package com.syntax.class04;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;
import utils.CommonMethods;

public class LoginTestEnhanced extends CommonMethods {

    @Test(groups ="smoke")
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

    @Test(groups = "regression")
    public void titleValidation() {

        String actualTitle = driver.getTitle();
        String expectedTitle = "Human Management System";
        if (expectedTitle.equals(actualTitle)) {
            System.out.println("The title is valid. The Test Pass");
        } else {
            System.out.println("The title is not matched. The Test Failed");
        }

    }

}
