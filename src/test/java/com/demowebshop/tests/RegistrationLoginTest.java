package com.demowebshop.tests;

import com.demowebshop.driver.DriverManager;
import com.demowebshop.pages.HomePage;
import com.demowebshop.pages.LoginPage;
import com.demowebshop.pages.RegistrationPage;
import com.demowebshop.utils.ConfigReader;
import com.demowebshop.utils.WaitUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.Test;

public class RegistrationLoginTest extends BaseTest {

    @Test(description = "Register a customer if needed, then verify login")
    public void testRegistrationAndLogin() {
        String email = requiredEnvironmentValue("DEMOWEBSHOP_EMAIL");
        String password = requiredEnvironmentValue("DEMOWEBSHOP_PASSWORD");
        String firstName = requiredEnvironmentValue("DEMOWEBSHOP_FIRST_NAME");
        String lastName = requiredEnvironmentValue("DEMOWEBSHOP_LAST_NAME");

        RegistrationPage registrationPage = new HomePage().clickRegisterLink();
        registrationPage.register(firstName, lastName, email, password);

        if (registrationPage.isRegistrationCompleted()) {
            registrationPage.clickContinue();
        } else {
            String errors = registrationPage.getRegistrationErrors();
            String normalizedErrors = errors.toLowerCase();
            Assert.assertTrue(
                    normalizedErrors.contains("email")
                            && (normalizedErrors.contains("already") || normalizedErrors.contains("exist")),
                    "Registration should complete or report that the email is already registered.");
        }

        HomePage homePage = new HomePage();
        if (homePage.isLoggedInAs(email)) {
            homePage.clickLogoutLink();
        }

        DriverManager.getDriver().get(ConfigReader.getProperty("url") + "login");
        new LoginPage().login(email, password);

        HomePage loggedInHomePage = new HomePage();
        Assert.assertTrue(loggedInHomePage.isLoggedInAs(email),
                "The account email should be displayed after login.");
        Assert.assertTrue(WaitUtils.waitUntilVisible(By.className("ico-logout")).isDisplayed(),
                "The logout link should be visible after login.");
        Assert.assertEquals(DriverManager.getDriver().getCurrentUrl(),
                ConfigReader.getProperty("url"),
                "Successful login should return to the home page.");
    }

    @Test(description = "Verify the documented invalid-password error")
    public void testInvalidPassword() {
        String email = requiredEnvironmentValue("DEMOWEBSHOP_EMAIL");

        DriverManager.getDriver().get(ConfigReader.getProperty("url") + "login");
        LoginPage loginPage = new LoginPage();
        loginPage.login(email, "WrongPassword123!");

        Assert.assertTrue(loginPage.isErrorMessageDisplayed(),
                "The invalid-login summary should be visible.");
        String error = loginPage.getErrorMessage();
        Assert.assertTrue(error.contains("Login was unsuccessful. Please correct the errors and try again."),
                "The invalid-login summary should contain the documented message.");
        Assert.assertTrue(error.contains("The credentials provided are incorrect"),
                "The invalid-login summary should explain that the credentials are incorrect.");
    }

    @Test(description = "Verify the documented invalid-email validation")
    public void testInvalidEmailFormat() {
        DriverManager.getDriver().get(ConfigReader.getProperty("url") + "login");
        new LoginPage().login("invalid-email-format", "SomePassword123!");

        WebElement fieldError = WaitUtils.waitUntilVisible(
                By.cssSelector("span.field-validation-error"));
        Assert.assertEquals(fieldError.getText().trim(), "Please enter a valid email address.");
    }

    private String requiredEnvironmentValue(String name) {
        String value = System.getenv(name);
        if (value == null || value.isBlank()) {
            throw new SkipException("Set the " + name + " environment variable to run this scenario.");
        }
        return value;
    }
}
