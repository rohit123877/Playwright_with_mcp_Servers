package com.demowebshop.utils;

import org.apache.poi.ss.usermodel.*;

import java.io.FileInputStream;
import java.io.IOException;

/**
 * Utility class for reading Excel test data using Apache POI library.
 */
public class ExcelUtils {

    /**
     * Reads test data from default Excel file specified in config.properties.
     * Assumes row 0 contains column headers.
     *
     * @param sheetName Name of the Excel sheet
     * @return 2D Object array suitable for TestNG DataProvider
     */
    public static Object[][] getTestData(String sheetName) {
        String defaultPath = ConfigReader.getProperty("excel.testdata.path", "src/test/resources/testdata.xlsx");
        return getTestData(defaultPath, sheetName);
    }

    /**
     * Reads test data from specified Excel file and sheet.
     * Assumes row 0 contains column headers.
     *
     * @param filePath Path to the Excel file (.xlsx or .xls)
     * @param sheetName Name of the sheet
     * @return 2D Object array containing row data
     */
    public static Object[][] getTestData(String filePath, String sheetName) {
        Workbook workbook = null;
        FileInputStream fis = null;
        Object[][] data = null;

        try {
            fis = new FileInputStream(filePath);
            workbook = WorkbookFactory.create(fis);
            Sheet sheet = workbook.getSheet(sheetName);

            if (sheet == null) {
                throw new RuntimeException("Sheet '" + sheetName + "' does not exist in file: " + filePath);
            }

            int totalRows = sheet.getLastRowNum(); // 0-based index of last row
            if (totalRows == 0) {
                return new Object[0][0];
            }

            Row headerRow = sheet.getRow(0);
            int totalCols = headerRow.getLastCellNum(); // Number of columns

            data = new Object[totalRows][totalCols];
            DataFormatter formatter = new DataFormatter();

            for (int i = 1; i <= totalRows; i++) {
                Row row = sheet.getRow(i);
                for (int j = 0; j < totalCols; j++) {
                    if (row == null) {
                        data[i - 1][j] = "";
                    } else {
                        Cell cell = row.getCell(j);
                        data[i - 1][j] = (cell == null) ? "" : formatter.formatCellValue(cell).trim();
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Error reading Excel file at: " + filePath, e);
        } finally {
            try {
                if (workbook != null) workbook.close();
                if (fis != null) fis.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return data;
    }

    /**
     * Gets total row count in a given sheet.
     *
     * @param filePath  Path to Excel file
     * @param sheetName Sheet name
     * @return Number of physical rows
     */
    public static int getRowCount(String filePath, String sheetName) {
        try (FileInputStream fis = new FileInputStream(filePath);
             Workbook workbook = WorkbookFactory.create(fis)) {
            Sheet sheet = workbook.getSheet(sheetName);
            return sheet != null ? sheet.getLastRowNum() + 1 : 0;
        } catch (IOException e) {
            e.printStackTrace();
            return 0;
        }
    }

    /**
     * Gets single cell value as string.
     *
     * @param filePath  Path to Excel file
     * @param sheetName Sheet name
     * @param rowNum    Row index (0-based)
     * @param colNum    Column index (0-based)
     * @return Cell string value
     */
    public static String getCellData(String filePath, String sheetName, int rowNum, int colNum) {
        try (FileInputStream fis = new FileInputStream(filePath);
             Workbook workbook = WorkbookFactory.create(fis)) {
            Sheet sheet = workbook.getSheet(sheetName);
            if (sheet == null) return "";
            Row row = sheet.getRow(rowNum);
            if (row == null) return "";
            Cell cell = row.getCell(colNum);
            if (cell == null) return "";
            DataFormatter formatter = new DataFormatter();
            return formatter.formatCellValue(cell).trim();
        } catch (IOException e) {
            e.printStackTrace();
            return "";
        }
    }
}
