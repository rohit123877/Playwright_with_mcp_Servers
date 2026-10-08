package com.demowebshop.tests;

import com.demowebshop.pages.HomePage;
import com.demowebshop.pages.SearchPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class SearchTest extends BaseTest {

    @Test(priority = 1, description = "Verify user can search for a product and add it to the cart")
    public void testSearchAndAddProductToCart() {
        HomePage homePage = new HomePage();
        Assert.assertTrue(homePage.isLogoVisible(), "Demo Web Shop homepage logo should be visible.");
        
        // Assert that cart starts empty
        Assert.assertEquals(homePage.getCartQuantityText(), "(0)", 
                "Shopping cart indicator should initially read (0).");

        // Search for standard book "Fiction"
        SearchPage searchPage = homePage.searchProduct("Fiction");
        Assert.assertTrue(searchPage.getSearchResultsCount() > 0, 
                "Search results should return at least 1 matching product.");
        Assert.assertTrue(searchPage.getFirstProductTitle().contains("Fiction"), 
                "First product title should contain keyword 'Fiction'. Actual: " + searchPage.getFirstProductTitle());

        // Add the book to the cart
        searchPage.clickAddFirstProductToCart();
        
        // Verify cart badge count is updated to (1)
        Assert.assertEquals(homePage.getCartQuantityText(), "(1)", 
                "Cart quantity indicator did not update to (1) after adding item.");
    }

    @Test(priority = 2, description = "Intentional failure to test failure screenshot capture in ExtentReports", enabled = false)
    public void testIntentionalFailure() {
        HomePage homePage = new HomePage();
        // Designed to fail by expecting cart quantity (99)
        Assert.assertEquals(homePage.getCartQuantityText(), "(99)", 
                "Intentional mismatch to trigger listener screenshot capture.");
    }
}
