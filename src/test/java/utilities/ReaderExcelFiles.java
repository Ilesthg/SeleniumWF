package utilities;

import base.BaseTest;
import io.github.bonigarcia.wdm.WebDriverManager;
import locators.FaceLoginLocator;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Factory;
import org.testng.annotations.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Method;
import java.time.Duration;


public class ReaderExcelFiles extends BaseTest {
    static String routeDir = System.getProperty("user.dir");
    static XSSFWorkbook XSSFworkbook;
    static XSSFSheet XSSFsheet;
   // static WebDriver driver;

    @DataProvider(name = "data")
    public static String[][] readExcel() throws IOException, InterruptedException {
        XSSFworkbook = new XSSFWorkbook(System.getProperty("user.dir") + "/src/test/resources/testData/dataExcelSheet.xlsx");
        XSSFsheet = XSSFworkbook.getSheet("Sheet1");

        int totalRows = XSSFsheet.getLastRowNum();
        System.out.println(totalRows);


        DataFormatter dataFormatter = new DataFormatter();
        Row row1 = XSSFsheet.getRow(1);
        int totalColumns = row1.getLastCellNum();
        System.out.println(totalColumns);

        String testData[][] = new String[totalRows][totalColumns];

        for (int i = 1; i < totalRows; i++) {
            Row row = XSSFsheet.getRow(i);
            String username = dataFormatter.formatCellValue(row.getCell(0));
            String password = dataFormatter.formatCellValue(row.getCell(1));
            testData[i - 1][0] = username;
            testData[i - 1][1] = password;
            System.out.println(username + " " + password);

        }
        return testData;
    }


    @DataProvider(name = "ExcelData")
//This parameter user Java Reflections, means the method who calls this implementation, will be passed as SheetName
    public String[][] readExcelandreturnData(Method m) throws IOException {
        File file = new File(routeDir + "/src/test/resources/testData/dataExcelSheet.xlsx");
        FileInputStream fileInputStream = new FileInputStream(file);

        Workbook wb = WorkbookFactory.create(fileInputStream);
        Sheet sheet = wb.getSheet(m.getName());


        //From Sheet we can obtain the Total Number of Rows
        int totalRows = sheet.getLastRowNum();
        System.out.println("Total Rows: " + totalRows);
        //To get Total Number of Columns, we need to select one Row and see how many columns it has,
        // to do this from Sheet, you select one row with index
        Row row = sheet.getRow(1);
        int totalColumns = row.getPhysicalNumberOfCells();
        System.out.println("Total Columns: " + totalColumns);

        DataFormatter dataFormatter = new DataFormatter();
        String[][] testData = new String[totalRows][totalColumns];
        for (int i = 1; i <= totalRows; i++) {
            for (int j = 0; j < totalColumns; j++) {
                testData[i - 1][j] = dataFormatter.formatCellValue(sheet.getRow(i).getCell(j));
                // System.out.println(testData[i - 1][j]);


            }
        }
        return testData;
    }


    public static String[][] readExcelwithDifferentCellsoneachROW() throws IOException {
        // Initialize workbook and sheet
        String filePath = System.getProperty("user.dir") + "/src/test/resources/testData/dataExcelSheet.xlsx";
        FileInputStream file = new FileInputStream(filePath);
        XSSFworkbook = new XSSFWorkbook(file);
        XSSFsheet = XSSFworkbook.getSheet("Sheet1");

        // Get total rows
        int totalRows = XSSFsheet.getLastRowNum() + 1; // Including the first row
        System.out.println("Total Rows: " + totalRows);

        // Create DataFormatter to format cell values
        DataFormatter dataFormatter = new DataFormatter();

        // Determine the maximum number of columns
        int maxColumns = 0;
        for (int i = 0; i < totalRows; i++) {
            Row row = XSSFsheet.getRow(i);
            if (row != null && row.getLastCellNum() > maxColumns) {
                maxColumns = row.getLastCellNum();
            }
        }
        System.out.println("Max Columns: " + maxColumns);

        // Initialize array with dynamic column size
        String[][] testData = new String[totalRows][maxColumns];

        // Iterate through rows
        for (int i = 0; i < totalRows; i++) {
            Row row = XSSFsheet.getRow(i);
            if (row != null) {
                for (int j = 0; j < row.getLastCellNum(); j++) {
                    testData[i][j] = dataFormatter.formatCellValue(row.getCell(j));
                }
            }
        }

        // Close resources
        XSSFworkbook.close();
        file.close();

        // Return the array of test data
        return testData;
    }
}

