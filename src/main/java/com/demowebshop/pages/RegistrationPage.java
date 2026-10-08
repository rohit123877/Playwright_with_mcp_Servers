package com.demowebshop.pages;

import com.demowebshop.driver.DriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.stream.Collectors;

public class RegistrationPage extends BasePage {

    private final By firstNameField = By.id("FirstName");
    private final By lastNameField = By.id("LastName");
    private final By emailField = By.id("Email");
    private final By passwordField = By.id("Password");
    private final By confirmPasswordField = By.id("ConfirmPassword");
    private final By registerButton = By.id("register-button");
    private final By continueButton = By.cssSelector("input.register-continue-button, button.register-continue-button");
    private final By registrationResult = By.className("result");
    private final By registrationErrors = By.cssSelector(
            ".validation-summary-errors, .field-validation-error");

    public RegistrationPage register(String firstName, String lastName, String email, String password) {
        type(firstNameField, firstName);
        type(lastNameField, lastName);
        type(emailField, email);
        type(passwordField, password);
        type(confirmPasswordField, password);
        click(registerButton);
        return this;
    }

    public boolean isRegistrationCompleted() {
        return DriverManager.getDriver().findElements(registrationResult).stream()
                .anyMatch(element -> element.isDisplayed()
                        && element.getText().contains("Your registration completed"));
    }

    public String getRegistrationErrors() {
        List<WebElement> errors = DriverManager.getDriver().findElements(registrationErrors);
        return errors.stream()
                .filter(element -> element.isDisplayed())
                .map(element -> element.getText())
                .filter(text -> !text.isBlank())
                .collect(Collectors.joining(" "));
    }

    public void clickContinue() {
        click(continueButton);
    }
}
