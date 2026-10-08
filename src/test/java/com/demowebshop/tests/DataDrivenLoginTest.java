package com.demowebshop.tests;

import com.demowebshop.pages.HomePage;
import com.demowebshop.pages.LoginPage;
import com.demowebshop.utils.DataProviders;
import org.testng.Assert;
import org.testng.annotations.Test;

public class DataDrivenLoginTest extends BaseTest {

    @Test(
        dataProvider = "excelLoginData", 
        dataProviderClass = DataProviders.class,
        priority = 1,
        description = "Verify login attempts using Excel data driven input"
    )
    public void testLoginWithExcelData(String email, String password, String expectedResult) {
        HomePage homePage = new HomePage();
        LoginPage loginPage = homePage.clickLoginLink();
        
        loginPage.login(email, password);

        if ("Fail".equalsIgnoreCase(expectedResult)) {
            Assert.assertTrue(loginPage.isErrorMessageDisplayed(),
                    "Validation error summary should be visible for invalid email: " + email);
            Assert.assertTrue(loginPage.getErrorMessage().contains("Login was unsuccessful"),
                    "Error message mismatch for email: " + email + ". Actual message: " + loginPage.getErrorMessage());
        }
    }
}
