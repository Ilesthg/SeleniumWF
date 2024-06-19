package utilities;

import org.apache.poi.util.StringUtil;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class PropertiesReader {
    private static Properties prop = new Properties();
    public static String giveKeyValueFromProperties(String key) throws Exception {
        String routeProperties = System.getProperty("user.dir") + "/src/test/resources/configFiles/data.properties";
        FileReader fR = new FileReader(routeProperties);

        prop.load(fR);

        String valuetoRetorn = prop.getProperty(key).toString();

        if (StringUtil.isBlank(key)){
            throw  new Exception("VALUE IS NOT SPECIFIED FOR THE KEY" + key + "in properties files" );
        }

        return  valuetoRetorn;



    }
}
