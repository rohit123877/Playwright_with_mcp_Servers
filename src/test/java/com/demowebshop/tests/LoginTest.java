package com.demowebshop.tests;

import com.demowebshop.pages.HomePage;
import com.demowebshop.pages.LoginPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginTest extends BaseTest {

    @Test(priority = 1, description = "Verify login fails with invalid credentials")
    public void testInvalidLogin() {
        HomePage homePage = new HomePage();
        LoginPage loginPage = homePage.clickLoginLink();
        loginPage.login("wrong_email_format_12345@testing.com", "wrong_password");
        
        Assert.assertTrue(loginPage.isErrorMessageDisplayed(), 
                "Login validation summary block should be visible.");
        Assert.assertTrue(loginPage.getErrorMessage().contains("Login was unsuccessful"), 
                "Expected error message block mismatch. Actual message: " + loginPage.getErrorMessage());
    }

    @Test(priority = 2, description = "Verify login fails with empty input parameters")
    public void testEmptyLogin() {
        HomePage homePage = new HomePage();
        LoginPage loginPage = homePage.clickLoginLink();
        loginPage.login("", "");
        
        Assert.assertTrue(loginPage.isErrorMessageDisplayed(), 
                "Login validation summary block should be visible.");
        Assert.assertTrue(loginPage.getErrorMessage().contains("Login was unsuccessful"), 
                "Expected error message block mismatch. Actual message: " + loginPage.getErrorMessage());
    }
}
