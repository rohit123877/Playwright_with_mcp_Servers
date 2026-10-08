package com.demowebshop.driver;

import org.openqa.selenium.WebDriver;

public class DriverManager {
    private static final ThreadLocal<WebDriver> driverThreadLocal = new ThreadLocal<>();

    private DriverManager() {
        // Private constructor to prevent instantiation
    }

    /**
     * Retrieves the thread-safe WebDriver instance.
     * @return WebDriver instance
     */
    public static WebDriver getDriver() {
        return driverThreadLocal.get();
    }

    /**
     * Sets the ThreadLocal WebDriver instance.
     * @param driver the WebDriver instance
     */
    public static void setDriver(WebDriver driver) {
        driverThreadLocal.set(driver);
    }

    /**
     * Quits the active WebDriver and removes the instance from ThreadLocal storage.
     */
    public static void quitDriver() {
        if (driverThreadLocal.get() != null) {
            try {
                driverThreadLocal.get().quit();
            } finally {
                driverThreadLocal.remove();
            }
        }
    }
}
