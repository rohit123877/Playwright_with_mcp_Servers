package com.demowebshop.pages;

import org.openqa.selenium.By;

public class HomePage extends BasePage {

    private final By loginLink = By.className("ico-login");
    private final By registerLink = By.className("ico-register");
    private final By searchField = By.id("small-searchterms");
    private final By searchButton = By.cssSelector("input.button-1.search-box-button");
    private final By cartLink = By.className("ico-cart");
    private final By cartQty = By.className("cart-qty");
    private final By logo = By.className("header-logo");

    /**
     * Navigates to the Login page by clicking the login link in the header.
     * @return a new LoginPage instance
     */
    public LoginPage clickLoginLink() {
        click(loginLink);
        return new LoginPage();
    }

    /**
     * Navigates to the registration page.
     * @return a new RegistrationPage instance
     */
    public RegistrationPage clickRegisterLink() {
        click(registerLink);
        return new RegistrationPage();
    }

    public boolean isLoggedInAs(String email) {
        return isDisplayed(By.cssSelector("a.account"))
                && email.equals(getText(By.cssSelector("a.account")));
    }

    public void clickLogoutLink() {
        click(By.className("ico-logout"));
    }

    /**
     * Searches for a product.
     * @param keyword search query term
     * @return SearchPage instance
     */
    public SearchPage searchProduct(String keyword) {
        type(searchField, keyword);
        click(searchButton);
        return new SearchPage();
    }

    /**
     * Gets the text displayed inside the shopping cart quantity indicator.
     * Generally formatted as "(0)", "(1)", etc.
     * @return quantity text
     */
    public String getCartQuantityText() {
        return getText(cartQty);
    }

    /**
     * Checks if the logo header is visible.
     * @return true if visible
     */
    public boolean isLogoVisible() {
        return isDisplayed(logo);
    }
}
