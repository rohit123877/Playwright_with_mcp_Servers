package com.demowebshop.tests;

import com.demowebshop.driver.DriverFactory;
import com.demowebshop.driver.DriverManager;
import com.demowebshop.utils.ConfigReader;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

public class BaseTest {

    /**
     * Initializes the driver instance before each test method execution.
     * Checks for a browser parameter in the TestNG suite XML; falls back to config.properties if not provided.
     *
     * @param browser optional browser name parameter passed from testng.xml
     */
    @BeforeMethod
    @Parameters({"browser"})
    public void setUp(@Optional String browser) {
        if (browser == null) {
            browser = ConfigReader.getProperty("browser", "chrome");
        }
        boolean headless = Boolean.parseBoolean(ConfigReader.getProperty("headless", "false"));
        
        WebDriver driver = DriverFactory.createDriver(browser, headless);
        DriverManager.setDriver(driver);
        
        // Navigate to the test URL
        DriverManager.getDriver().get(ConfigReader.getProperty("url"));
    }

    /**
     * Cleans up the active WebDriver session after each test method.
     */
    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        DriverManager.quitDriver();
    }
}
