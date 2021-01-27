package com.syntax.testbase;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import utils.ConfigsReader;

import java.util.concurrent.TimeUnit;

public class BaseClass {

    public static WebDriver driver;
    public static void setUp(){
        ConfigsReader.readProperties("C:\\Users\\drysa\\IdeaProjects\\TestNGBasics\\src\\configs\\configuration.properties");
        switch (ConfigsReader.getPropertyValue("browser").toLowerCase()) {

            case "chrome":
                System.setProperty("webdriver.chrome.driver", "drivers/chromedriver.exe");
                driver = new ChromeDriver();
                break;

            case "firefox":
                System.setProperty("webdriver.gecko.driver", "drivers/geckodriver.exe");
                driver = new FirefoxDriver();
                break;

//            case "edge":
//                System.setProperty("webdriver.edge.driver", "user.dir" + "drivers.edgedriver.exe");
//                driver = new EdgeDriver();
//                break;

            default:
                throw new RuntimeException("Invalid browser");

        }
        driver.get(ConfigsReader.getPropertyValue("url"));
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

    }
    /**
     this method will navigate to a website by the given URL
     */
    public void  setUpWithSpecificURL(String url){
        System.setProperty("webdriver.chrome.driver", "/Users/martaostash/Documents/SeleniumDependa/Drivers/chromedriver-2");
        driver=new ChromeDriver();
        driver.get(url);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

    }


    public static void tearDown() {
        if (driver != null) {
            driver.quit();

        }
    }
}

