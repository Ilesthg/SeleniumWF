package utilities.GetData;

import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.testng.annotations.DataProvider;

import java.io.File;
import java.util.HashMap;

public class ReadExcelAndReturnHashMap {
    private static String routeDir = System.getProperty("user.dir");
    private static String filePath;
    private static Workbook wb;
    private static File testDataFile;
    static Sheet sh = null;


    //Constructor
    public ReadExcelAndReturnHashMap() {
        try {
            filePath = routeDir + "/src/test/resources/testData/dataExcelSheet.xlsx";
            //open file - workbook
            testDataFile = new File(filePath);
            wb = WorkbookFactory.create(testDataFile);
            sh = wb.getSheet("Sheet1");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public HashMap<String, String> returnExcelDatainHashMap(int rowNumber) {
        HashMap<String, String> hm = new HashMap<>();
        //Itera en las columnas (en row 0, hay 2 columnas, iterara las n columnas del excel i= 0,1)
        int rows = sh.getLastRowNum();
        int columns = sh.getRow(0).getLastCellNum();
        System.out.println("Total Rows: " + rows + "Total Columns: " + columns);


        for (int j = 0; j < sh.getRow(0).getLastCellNum(); j++) {
            String value;
            //Da VALOR DE  la ROW 1,con columna 0,1
            if (sh.getRow(rowNumber).getCell(j) != null) {
                sh.getRow(rowNumber).getCell(j).setCellType(CellType.STRING);
                value = sh.getRow(rowNumber).getCell(j).toString();
            } else {
                value = "";
            }
            hm.put(sh.getRow(0).getCell(j).toString(), value);
        }
        return hm;
    }


    public int getTotalRows() {
        return sh.getLastRowNum();
    }


}
