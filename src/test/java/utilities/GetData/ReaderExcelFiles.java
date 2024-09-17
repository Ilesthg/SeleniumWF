package utilities.GetData;


import ConfigFiles.FrameWorkConstants;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.DataProvider;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.HashMap;


public class ReaderExcelFiles {
    static String routeDir = System.getProperty("user.dir");
    static XSSFWorkbook XSSFworkbook;
    static XSSFSheet sheet;
    // static WebDriver driver;


    @DataProvider(name = "ExcelTest")
    public static Object[][] returnExcelDatainHashMap1() throws Exception {
        ReadExcelAndReturnHashMap readExcelAndReturnHashMap = new ReadExcelAndReturnHashMap();

        Object[][] testData = new Object[readExcelAndReturnHashMap.getTotalRows()][1];

        for (int i = 1; i <= readExcelAndReturnHashMap.getTotalRows(); i++) {
            HashMap<String, String> hm = readExcelAndReturnHashMap.returnExcelDatainHashMap(i);
            testData[i - 1][0] = hm;

        }
        return testData;

    }

    @DataProvider(name = "ExcelData")
//This parameter user Java Reflections, means the method who calls this implementation, will be passed as SheetName
    public Object[][] readExcelandreturnData(Method m) throws IOException {
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
        Object[][] testData = new Object[totalRows][totalColumns];
        for (int i = 1; i <= totalRows; i++) {
            for (int j = 0; j < totalColumns; j++) {
                testData[i - 1][j] = dataFormatter.formatCellValue(sheet.getRow(i).getCell(j));
                // System.out.println(testData[i - 1][j])
            }
        }
        return testData;
    }

    @DataProvider(name = "ExcelTest2")
    public Object[][] returnExcelDatainHashMap2() throws IOException {


        File testDataFile = new File(FrameWorkConstants.getRouteTestExcel());
        Workbook wb = WorkbookFactory.create(testDataFile);
        Sheet sh = wb.getSheet("Sheet1");


        //Itera en las columnas (en row 0, hay 2 columnas, iterara las n columnas del excel i= 0,1)
        int rows = sh.getLastRowNum();
        int columns = sh.getRow(0).getLastCellNum();
        System.out.println("Total Rows: " + rows + "Total Columns: " + columns);


        Object[][] testData = new Object[rows][1];
        for (int i = 1; i <= rows; i++) {
            //Bad Performance *--> MULTIPLE HashMaps are being created for each row
            HashMap<String, String> hm = new HashMap<>();
            for (int j = 0; j < sh.getRow(0).getLastCellNum(); j++) {
                String value;
                //Da VALOR DE  la ROW 1,con columna 0,1
                if (sh.getRow(i).getCell(j) != null) {
                    sh.getRow(i).getCell(j).setCellType(CellType.STRING);
                    value = sh.getRow(i).getCell(j).toString();
                } else {
                    value = "";
                }
                hm.put(sh.getRow(0).getCell(j).toString(), value);

            }
            testData[i - 1][0] = hm;
        }


        return testData;
    }

    @DataProvider(name = "DataHashMap")
    public Object[] returnExcelDatainHashMap3() throws IOException {
        FileInputStream file = new FileInputStream(FrameWorkConstants.getRouteTestExcel());
        XSSFWorkbook workbook = new XSSFWorkbook(file);
        XSSFSheet sheet = workbook.getSheet("Sheet1");


        //Itera en las columnas (en row 0, hay n columnas, iterara las n columnas del excel i= 0 and j= 0...n
        int rows = sheet.getLastRowNum();
        int totalColumns = sheet.getRow(0).getLastCellNum();
        System.out.println("Total Rows: " + rows + "Total Columns: " + totalColumns);


        Object[] testData = new Object[rows];
        HashMap<String, String> hm;

        for (int i = 1; i <= rows; i++) {
            //Bad Performance *--> MULTIPLE HashMaps are being created for each data row in this data 3 rows will be created, meaning 3 HM.

            hm = new HashMap<>();

            for (int j = 0; j < totalColumns; j++) {
                String key;
                String value;

                if (sheet.getRow(0).getCell(j) != null) { //Iterates in first row, all columns
                    key = sheet.getRow(0).getCell(j).getStringCellValue();
                } else {
                    key = "";
                }


                //Da VALOR DE  la ROW 1,con columna 0,1

                if (sheet.getRow(i).getCell(j) != null) {
                    sheet.getRow(i).getCell(j).setCellType(CellType.STRING);
                    value = sheet.getRow(i).getCell(j).toString();
                } else {
                    value = "";
                }
                hm.put(key, value);

               // System.out.println("iteration " + i + hm);

            }
            testData[i - 1] = hm;
        }


        return testData;
    }


    @DataProvider(name = "dataNotScalable")
    public static Object[][] readExcel() throws IOException {
        XSSFworkbook = new XSSFWorkbook(System.getProperty("user.dir") + "/src/test/resources/testData/dataExcelSheet.xlsx");
        sheet = XSSFworkbook.getSheet("Sheet1");
        DataFormatter dataFormatter = new DataFormatter();

        int totalRows = sheet.getLastRowNum();
        System.out.println(totalRows);


        Row row1 = sheet.getRow(1);
        int totalColumns = row1.getLastCellNum();
        System.out.println(totalColumns);

        Object testData[][] = new String[totalRows][totalColumns];

        for (int i = 1; i <= totalRows; i++) {
            Row row = sheet.getRow(i);
            String username = dataFormatter.formatCellValue(row.getCell(0));
            String password = dataFormatter.formatCellValue(row.getCell(1));
            testData[i - 1][0] = username;// ROW 1,2,3,4 with Column 0 and 1
            testData[i - 1][1] = password;//
            System.out.println(username + " " + password);

        }
        return testData;
    }


    public static String[][] readExcelwithDifferentCellsOnEachROW() throws IOException {
        // Initialize workbook and sheet
        String filePath = System.getProperty("user.dir") + "/src/test/resources/testData/dataExcelSheet.xlsx";
        FileInputStream file = new FileInputStream(filePath);
        XSSFworkbook = new XSSFWorkbook(file);
        sheet = XSSFworkbook.getSheet("Sheet1");

        // Get total rows
        int totalRows = sheet.getLastRowNum() + 1; // Including the first row
        System.out.println("Total Rows: " + totalRows);

        // Create DataFormatter to format cell values
        DataFormatter dataFormatter = new DataFormatter();

        // Determine the maximum number of columns
        int maxColumns = 0;
        for (int i = 0; i < totalRows; i++) {
            Row row = sheet.getRow(i);
            if (row != null && row.getLastCellNum() > maxColumns) {
                maxColumns = row.getLastCellNum();
            }
        }
        System.out.println("Max Columns: " + maxColumns);

        // Initialize array with dynamic column size
        String[][] testData = new String[totalRows][maxColumns];

        // Iterate through rows
        for (int i = 0; i < totalRows; i++) {
            Row row = sheet.getRow(i);
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

