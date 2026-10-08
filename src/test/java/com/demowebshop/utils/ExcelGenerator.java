package com.demowebshop.utils;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class ExcelGenerator {

    public static void main(String[] args) {
        String filePath = "src/test/resources/testdata.xlsx";
        File file = new File(filePath);
        file.getParentFile().mkdirs();

        try (Workbook workbook = new XSSFWorkbook()) {
            // Sheet 1: LoginData
            Sheet loginSheet = workbook.createSheet("LoginData");
            String[][] loginData = {
                {"Email", "Password", "ExpectedResult"},
                {"invalid_user1@test.com", "pass123", "Fail"},
                {"invalid_user2@test.com", "wrongpass", "Fail"},
                {"empty_user@test.com", "", "Fail"}
            };

            for (int r = 0; r < loginData.length; r++) {
                Row row = loginSheet.createRow(r);
                for (int c = 0; c < loginData[r].length; c++) {
                    Cell cell = row.createCell(c);
                    cell.setCellValue(loginData[r][c]);
                }
            }

            // Sheet 2: SearchData
            Sheet searchSheet = workbook.createSheet("SearchData");
            String[][] searchData = {
                {"Keyword", "ExpectedCategory"},
                {"Fiction", "Books"},
                {"Computer", "Computers"},
                {"Jewelry", "Jewelry"}
            };

            for (int r = 0; r < searchData.length; r++) {
                Row row = searchSheet.createRow(r);
                for (int c = 0; c < searchData[r].length; c++) {
                    Cell cell = row.createCell(c);
                    cell.setCellValue(searchData[r][c]);
                }
            }

            try (FileOutputStream fos = new FileOutputStream(file)) {
                workbook.write(fos);
            }
            System.out.println("Excel test data file successfully created at: " + file.getAbsolutePath());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
