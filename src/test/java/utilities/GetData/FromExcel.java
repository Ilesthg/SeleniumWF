package utilities.GetData;

import ConfigFiles.FrameWorkConstants;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

public final class FromExcel {

    private FromExcel(){

    }
    public List<HashMap<String,String>> returnListofHashMap(){
        List<HashMap<String,String>> result = null;

        HashMap<String,String> hm;
        XSSFSheet sheet;
        try {
            FileInputStream fip = new FileInputStream(FrameWorkConstants.getRouteTestExcel());
            XSSFWorkbook workbook  = new XSSFWorkbook(fip);
             sheet = workbook.getSheet("Sheet1");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        int totalRows = sheet.getLastRowNum();
        int totalColumns = sheet.getRow(0).getLastCellNum();

        for (int i = 1; i <=totalRows ; i++) {
            hm = new HashMap<>();
            for (int j = 0; j < totalColumns; j++) {
                String key = sheet.getRow(0).getCell(j).toString();
                String value = sheet.getRow(i).getCell(j).toString();
                hm.put(key, value);
            }
            result.add(hm);
        }





        return result;

    }

    public Object[] returnExcelSheetInHashMap() throws IOException, InvalidFormatException {

        XSSFWorkbook workbook = new XSSFWorkbook(new File(FrameWorkConstants.getRouteTestExcel()));
        XSSFSheet sheet = workbook.getSheet("Sheet1");

        int totalRows = sheet.getLastRowNum();

        int totalColumns = sheet.getRow(0).getLastCellNum();

        HashMap<Object,Object> hm;
        Object[] data = new Object[totalRows];

        for (int i = 1; i <= totalRows; i++) {
            String key;
            String value = null;
            hm = new HashMap<>(); //Create new Hashmap for eachdata row
            for (int j = 0; j < totalColumns; j++) {

                key = sheet.getRow(0).getCell(j).getStringCellValue();
                if(value != null){
                    sheet.getRow(i).getCell(j).setCellType(CellType.STRING);
                    value = sheet.getRow(i).getCell(j).toString();
                }else{
                    value = "";
                }

                hm.put(key,value);
            }
            data[i-1] = hm;


        }

        return  data;
    }

}
