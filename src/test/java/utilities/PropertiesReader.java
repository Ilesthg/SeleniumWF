package utilities;

import ConfigFiles.ConfigProperties;
import ConfigFiles.FrameWorkConstants;
import org.apache.poi.util.StringUtil;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
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
    public static String giveKeyValueFromProperties(ConfigProperties cp) throws Exception {


        String key = cp.toString().toLowerCase();
        String valueToReturn = prop.getProperty(key).toLowerCase().toString();

        if (StringUtil.isBlank(key) || Objects.isNull(key) || Objects.isNull(valueToReturn)){
            throw  new Exception("VALUE IS NOT SPECIFIED FOR THE KEY" + key + "in properties files" );
        }

        return  valueToReturn;



    }
}
