package utilities;

import org.apache.poi.ss.usermodel.*;
import org.testng.annotations.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;


public class ReaderExcelFiles {
    static String routeDir = System.getProperty("user.dir");

    @Test
    public static void readExcel() throws IOException {
        //System.out.println(routeDir + "/src/test/resources/testData/dataExcelSheet.xlsx");

        File file = new File(routeDir + "/src/test/resources/testData/dataExcelSheet.xlsx");
        FileInputStream fileInputStream = new FileInputStream(file);

        Workbook wb = WorkbookFactory.create(fileInputStream);
        Sheet sheet = wb.getSheet("Sheet1");


        //From Sheet we can obtain the Total Number of Rows
        int totalRows = sheet.getPhysicalNumberOfRows();
        System.out.println(totalRows);
        //To get Total Number of Columns, we need to select one Row and see how many columns it has,
        // to do this from Sheet, you select one row with index
        Row row = sheet.getRow(1);
        int totalColumns = row.getPhysicalNumberOfCells();
        System.out.println(totalColumns);

/*
        for (int i = 1; i <= totalRows; i++) {
            String userName = sheet.getRow(i).getCell(0).getStringCellValue();
            System.out.println(userName);

            String password = sheet.getRow(i).getCell(1).getStringCellValue();
            System.out.println(password);


        }
*/


        DataFormatter dataFormatter = new DataFormatter();
        String testData[][] = new String[totalRows][totalColumns];
        for (int i = 1; i < totalRows; i++) {
            for (int j = 0; j < totalColumns; j++) {
                 testData[i - 1][j] = dataFormatter.formatCellValue(sheet.getRow(i).getCell(j));
                System.out.println(testData[i - 1][j] = dataFormatter.formatCellValue(sheet.getRow(i).getCell(j)));
                //String userName = dataFormatter.formatCellValue(sheet.getRow(i).getCell(0));

            }
        }


    }


    //  return new Object[][];
}

