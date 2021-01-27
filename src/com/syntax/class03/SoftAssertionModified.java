package com.syntax.class03;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;

import java.util.concurrent.TimeUnit;

public class SoftAssertionModified {

    WebDriver driver;

    @BeforeSuite // before the suite -- > based on xml file
    public void beforeSuite() {
        System.out.println("I am BEFORE SUITE annotation");
    }

    @AfterSuite (alwaysRun = true)// before the test
    public void afterSuite() {
        System.out.println("I am AFTER SUITE annotation");
    }

    @BeforeTest (alwaysRun = true) //before the test -- > based on xml file
    public void beforeTest() {
        System.out.println("I am BEFORE TEST annotation");
    }

    @AfterTest(alwaysRun = true)
    public void afterTest() {
        System.out.println("I am AFTER TEST annotation");
    }

    @BeforeClass(alwaysRun = true)
    public void beforeClass(){
        System.out.println("I am BEFORE CLASS annotation");
    }

    @AfterClass(alwaysRun = true)
    public void afterClass(){
        System.out.println("I am AFTER CLASS annotation");
    }


    @BeforeMethod (alwaysRun = true) // @enforce execution of the pre-condition regardless to which group @Test belongs to
    public void openAndNavigate() {

        System.setProperty("webdriver.chrome.driver", "drivers/chromedriver.exe");

        driver = new ChromeDriver();

        //driver.manage().window().maximize();
        driver.navigate().to("http://hrmstest.syntaxtechs.net/humanresources/symfony/web/index.php/auth/login");
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

    }

    @AfterMethod(alwaysRun = true)
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
        softAsert.assertEquals(welcomeMessage.getText(), "Welcome " + username, "Welcome text is not matching");
        System.out.println("End of the test case");
        softAsert.assertAll();// soft Assert show me everything
        // soft assert = collects all fails. And in the end we need to call method assertAll();

    }

    @Test(groups = {"smoke", "sprint2"})
    public void simpleTest(){
        System.out.println("Hello from Simple Test");
    }
}

