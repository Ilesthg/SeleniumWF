package utilities.GetData;

import ConfigFiles.FrameWorkConstants;
import base.TestDataParallel;
import exceptions.InvalidFilePathException;
import net.bytebuddy.implementation.bytecode.Throw;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.DataProvider;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.*;

public final class FromExcel {

    private FromExcel() {

    }

    public static List<HashMap<String, String>> returnListofHashMap(String nameOfSheet) {

        Map<String, List<String>> browserVersions = new HashMap<>();
        browserVersions.put("chrome", Arrays.asList("130.0.6723.69"));
       // browserVersions.put("edge", Arrays.asList("latest"));


        List<HashMap<String, String>> result = null;


        try ( FileInputStream fip = new FileInputStream(FrameWorkConstants.getRouteTestExcel());){

            XSSFWorkbook workbook = new XSSFWorkbook(fip);
            XSSFSheet sheet = workbook.getSheet(nameOfSheet);

            HashMap<String, String> hm;
            result = new ArrayList<>();

            int totalRows = sheet.getLastRowNum();
            int totalColumns = sheet.getRow(0).getLastCellNum();

            for (int i = 1; i <= totalRows; i++) {
                hm = new HashMap<>();
                for (int j = 0; j < totalColumns; j++) {
                    String key = sheet.getRow(0).getCell(j).toString();
                    String value = sheet.getRow(i).getCell(j).toString();
                    hm.put(key, value);
                }
                for (Map.Entry<String, List<String>> entry : browserVersions.entrySet()) {
                    String browser = entry.getKey();
                    for (String version : entry.getValue()) {
                        HashMap<String, String> testConfig = new HashMap<>(hm);
                        testConfig.put("Browser", browser);
                        testConfig.put("Version", version);
                        result.add(testConfig);
                    }
                }
            }




            // result.add(hm);

        } catch (IOException e) {
            throw new InvalidFilePathException(e);
        }

        return result;

    }
  /*  public static List<HashMap<String, String>> returnListofHashMap(String nameOfSheet) {

        List<HashMap<String, String>> result = null;


        try ( FileInputStream fip = new FileInputStream(FrameWorkConstants.getRouteTestExcel());){

            XSSFWorkbook workbook = new XSSFWorkbook(fip);
            XSSFSheet sheet = workbook.getSheet(nameOfSheet);

            HashMap<String, String> hm;
            result = new ArrayList<>();

            int totalRows = sheet.getLastRowNum();
            int totalColumns = sheet.getRow(0).getLastCellNum();

            for (int i = 1; i <= totalRows; i++) {
                hm = new HashMap<>();
                for (int j = 0; j < totalColumns; j++) {
                    String key = sheet.getRow(0).getCell(j).toString();
                    String value = sheet.getRow(i).getCell(j).toString();
                    hm.put(key, value);
                }
                result.add(hm);
            }
        } catch (IOException e) {
            throw new InvalidFilePathException(e);
        }

        return result;

    }*/

    public static Object[] returnExcelSheetInObject()  {
        Object[] data = null;
        try (  XSSFWorkbook workbook = new XSSFWorkbook(new File(FrameWorkConstants.getRouteTestExcel()));) {
            XSSFSheet sheet = workbook.getSheet("Data");

            int totalRows = sheet.getLastRowNum();

            int totalColumns = sheet.getRow(0).getLastCellNum();

            HashMap<Object, Object> hm;
           data = new Object[totalRows];//or create a list of hashmaps and convert to array

            for (int i = 1; i <= totalRows; i++) {

                hm = new HashMap<>(); //Create new Hashmap for eachdata row--//Bad Performance *--> MULTIPLE HashMaps are being created for each data row in this data 3 rows will be created, meaning 3 HM.
                for (int j = 0; j < totalColumns; j++) {
                    String key;
                    String value;


                    if (sheet.getRow(0).getCell(j) != null) {
                        key = sheet.getRow(0).getCell(j).getStringCellValue();
                    } else {
                        key = "";
                    }
                    if (sheet.getRow(i).getCell(j) != null) {
                        sheet.getRow(i).getCell(j).setCellType(CellType.STRING);
                        value = sheet.getRow(i).getCell(j).toString();
                    } else {
                        value = "";
                    }
                    hm.put(key, value);
                }
                data[i - 1] = hm;


            }

        }catch (IOException | InvalidFormatException e ){
        throw  new InvalidFilePathException(e);
        }
        return data;
    }
    @DataProvider(name = "dataInObject")
    public static Object[] returnExcelSheetInObject2() {
        List<HashMap<String, String>> result = null;


        try ( FileInputStream fip =new FileInputStream(FrameWorkConstants.getRouteTestExcel());){

            XSSFWorkbook workbook = new XSSFWorkbook(fip);
            XSSFSheet sheet = workbook.getSheet("Data");

            HashMap<String, String> hm;
            result = new ArrayList<>();

            int totalRows = sheet.getLastRowNum();
            int totalColumns = sheet.getRow(0).getLastCellNum();

            for (int i = 1; i <= totalRows; i++) {
                hm = new HashMap<>();
                for (int j = 0; j < totalColumns; j++) {
                    String key = sheet.getRow(0).getCell(j).toString();
                    String value = sheet.getRow(i).getCell(j).toString();
                    hm.put(key, value);
                }
                result.add(hm);
            }


        } catch (IOException e) {
           // e.printStackTrace();
            throw  new InvalidFilePathException(e);
        }


        return result.toArray();

    }


    @DataProvider(name = "ExcelData")//Traditional but not recommended if many fields(columns of data) used in tests
//This parameter user Java Reflections, means the method who calls this implementation, will be passed as SheetName
    public Object[][] readExcelandreturnTwoDimensionObject (Method m) throws IOException {
        File file = new File(System.getProperty("user.dir") + "/src/test/resources/testData/dataExcelSheet.xlsx");
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




























    @DataProvider(name = "dataNotScalable")
    public static Object[][] readExcel() throws IOException {
        XSSFWorkbook   XSSFworkbook = new XSSFWorkbook(System.getProperty("user.dir") + "/src/test/resources/testData/dataExcelSheet.xlsx");
        XSSFSheet   sheet = XSSFworkbook.getSheet("Sheet1");
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
        XSSFWorkbook   XSSFworkbook = new XSSFWorkbook(file);
        XSSFSheet   sheet = XSSFworkbook.getSheet("Sheet1");

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
