package com.sample.utils;

import com.sample.configuration.Configuration;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

import static org.testng.Assert.fail;

public class WebDriverProvider {

    private static WebDriver driver;

    public static WebDriver getDriver() {
        Configuration config = new Configuration();
        if (config.getBrowser().equalsIgnoreCase("chrome")) {
            if (config.getPlatform().equalsIgnoreCase("mac")) {
                System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver/mac/chromedriver");
            } else {
                System.setProperty("webdriver.chrome.driver",
                        "src/test/resources/chromedriver/windows/chromedriver.exe");
            }
        } else {
            fail("Unsupported browser " + config.getBrowser());
        }
        if (driver == null) {
            driver = new ChromeDriver();
        }
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
        return driver;
    }

}
