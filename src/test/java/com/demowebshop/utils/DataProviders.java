package com.demowebshop.utils;

import org.testng.annotations.DataProvider;

/**
 * Repository for TestNG DataProviders fetching data dynamically from Excel files.
 */
public class DataProviders {

    /**
     * DataProvider for Login test cases reading from 'LoginData' sheet.
     */
    @DataProvider(name = "excelLoginData")
    public static Object[][] getLoginData() {
        return ExcelUtils.getTestData("LoginData");
    }

    /**
     * DataProvider for Search test cases reading from 'SearchData' sheet.
     */
    @DataProvider(name = "excelSearchData")
    public static Object[][] getSearchData() {
        return ExcelUtils.getTestData("SearchData");
    }
}
