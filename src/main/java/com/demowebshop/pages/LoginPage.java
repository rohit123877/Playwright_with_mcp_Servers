package com.demowebshop.pages;

import org.openqa.selenium.By;

public class LoginPage extends BasePage {

    private final By emailField = By.id("Email");
    private final By passwordField = By.id("Password");
    private final By loginButton = By.cssSelector("input.button-1.login-button");
    private final By errorSummary = By.className("validation-summary-errors");

    /**
     * Enters email address.
     * @param email user email
     * @return current LoginPage instance
     */
    public LoginPage enterEmail(String email) {
        type(emailField, email);
        return this;
    }

    /**
     * Enters password.
     * @param password user password
     * @return current LoginPage instance
     */
    public LoginPage enterPassword(String password) {
        type(passwordField, password);
        return this;
    }

    /**
     * Clicks the login button.
     */
    public void clickLogin() {
        click(loginButton);
    }

    /**
     * Composite action to attempt login.
     * @param email user email
     * @param password user password
     */
    public void login(String email, String password) {
        enterEmail(email);
        enterPassword(password);
        clickLogin();
    }

    /**
     * Gets the validation summary error message block text.
     * @return validation error message text
     */
    public String getErrorMessage() {
        return getText(errorSummary);
    }

    /**
     * Checks if the validation summary block is displayed.
     * @return true if displayed, false otherwise
     */
    public boolean isErrorMessageDisplayed() {
        return isDisplayed(errorSummary);
    }
}
