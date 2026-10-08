package com.demowebshop.utils;

import com.demowebshop.driver.DriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class WaitUtils {

    private static Duration getExplicitWaitTimeout() {
        String timeout = ConfigReader.getProperty("timeout.explicit", "15");
        return Duration.ofSeconds(Long.parseLong(timeout));
    }

    /**
     * Waits until the element located by By is visible on the screen.
     * @param locator the element locator
     * @return the visible WebElement
     */
    public static WebElement waitUntilVisible(By locator) {
        WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), getExplicitWaitTimeout());
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    /**
     * Waits until the element located by By is clickable.
     * @param locator the element locator
     * @return the clickable WebElement
     */
    public static WebElement waitUntilClickable(By locator) {
        WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), getExplicitWaitTimeout());
        return wait.until(ExpectedConditions.elementToBeClickable(locator));
    }

    /**
     * Waits until the element located by By is present in the DOM.
     * @param locator the element locator
     * @return the present WebElement
     */
    public static WebElement waitUntilPresence(By locator) {
        WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), getExplicitWaitTimeout());
        return wait.until(ExpectedConditions.presenceOfElementLocated(locator));
    }
}
