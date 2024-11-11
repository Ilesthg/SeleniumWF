package utilities.GetData;

import ConfigFiles.ConfigProperties;
import ConfigFiles.FrameWorkConstants;
import exceptions.InvalidFilePathException;
import exceptions.SeleniumFrameworkException;
import org.apache.poi.util.StringUtil;
import org.testng.annotations.DataProvider;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.Properties;

public class PropertiesReader {
    private static Properties prop = new Properties();



    //Creating static bloc so poperties load in cache and increase performance by decreasing the time used to load properties
    //This is advisable if properties are used  to read and perfrm operations multiple times

    static {
        FileReader fR = null;
        try {
            fR = new FileReader(FrameWorkConstants.getRoutePropertiesFile());
            prop.load(fR);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
    public static String giveKeyValueFromProperties(ConfigProperties cp){


       String key = cp.toString().toLowerCase(); //browser

        String valueToReturn = prop.getProperty(key).toString();

        if (StringUtil.isBlank(key) || Objects.isNull(key) || Objects.isNull(valueToReturn)){
            throw  new InvalidFilePathException( "VALUE IS NOT SPECIFIED FOR THE KEY" + key + "in properties files" );
        }

        return  valueToReturn;
    }
    @DataProvider(name = "browsers", parallel = true)
    public static  Object[][] dataProviderDataProperties(ConfigProperties cp){
        String[] arrayBrowsers = prop.getProperty(cp.toString().toLowerCase()).split(",");
        Object[][] data = new Object[arrayBrowsers.length][1];




        for (int i = 0; i < arrayBrowsers.length; i++) {
            data[i][0] = arrayBrowsers[i].trim();
        }
        return data;
    }
}
