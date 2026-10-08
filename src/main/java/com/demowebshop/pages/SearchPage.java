package com.demowebshop.pages;

import com.demowebshop.driver.DriverManager;
import com.demowebshop.utils.WaitUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

public class SearchPage extends BasePage {

    private final By productItem = By.className("product-item");
    private final By productTitleLink = By.cssSelector(".product-item .product-title a");
    private final By addToCartButton = By.cssSelector("input.button-2.product-box-add-to-cart-button");
    private final By barNotification = By.cssSelector("#bar-notification");

    /**
     * Gets the count of product items displayed in search results.
     * @return count of items
     */
    public int getSearchResultsCount() {
        try {
            WaitUtils.waitUntilPresence(productItem);
            List<WebElement> items = DriverManager.getDriver().findElements(productItem);
            return items.size();
        } catch (Exception e) {
            return 0;
        }
    }

    /**
     * Retrieves the title of the first product in the search results list.
     * @return product title
     */
    public String getFirstProductTitle() {
        return getText(productTitleLink);
    }

    /**
     * Adds the first product in the search results to the shopping cart.
     * Waits for the AJAX green notification bar to appear to verify addition success.
     */
    public void clickAddFirstProductToCart() {
        click(addToCartButton);
        // Wait for AJAX notification bar confirming standard addition
        WaitUtils.waitUntilVisible(barNotification);
    }
}
