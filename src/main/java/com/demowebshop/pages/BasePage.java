package com.demowebshop.pages;

import com.demowebshop.driver.DriverManager;
import com.demowebshop.utils.WaitUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

public class BasePage {

    protected BasePage() {
        PageFactory.initElements(DriverManager.getDriver(), this);
    }

    /**
     * Clicks on the element specified by the By locator after checking clickability.
     * @param locator By locator
     */
    protected void click(By locator) {
        WaitUtils.waitUntilClickable(locator).click();
    }

    /**
     * Clears and sends keys to the element specified by the By locator after checking visibility.
     * @param locator By locator
     * @param text text to type
     */
    protected void type(By locator, String text) {
        WebElement element = WaitUtils.waitUntilVisible(locator);
        element.clear();
        element.sendKeys(text);
    }

    /**
     * Gets the visible inner text of the element specified by the By locator.
     * @param locator By locator
     * @return the text
     */
    protected String getText(By locator) {
        return WaitUtils.waitUntilVisible(locator).getText();
    }

    /**
     * Checks if the element specified by the By locator is visible.
     * @param locator By locator
     * @return true if visible, false otherwise
     */
    protected boolean isDisplayed(By locator) {
        try {
            return WaitUtils.waitUntilVisible(locator).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
}
