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
import java.util.*;

public class PropertiesReader {
    private static Properties prop = new Properties();


    //Creating static bloc so poperties load in cache and increase performance by decreasing the time used to load properties
    //This is advisable if properties are used  to read and perform operations multiple times
    //Use try with resources only to autoclosable objects
    //the runtime exception does not stop program for running, so use System.exit(0)
    static final HashMap<Object, Object> CONFIGMAP = new HashMap<>();

    static {

        try (FileReader fR = new FileReader(FrameWorkConstants.getRoutePropertiesFile())) {
            prop.load(fR);

            for (Map.Entry<Object, Object> entry : prop.entrySet()) {
                String key = (String) entry.getKey();
                String value = (String) entry.getValue();

                // Split the value by commas and trim any extra spaces
                List<String> valuesArray = Arrays.asList(value.split(","));
                CONFIGMAP.put(key, valuesArray);

            }
            //CONFIGMAP.put(String.valueOf(entry.getKey()), String.valueOf(entry.getValue()).trim());
        } catch (IOException e) {
            e.printStackTrace();
            // throw  new RuntimeException();
            System.exit(0);
        }

    }

    public static String giveKeyValueFromProperties(ConfigProperties cp) {


        String key = cp.toString().toLowerCase(); //browser

        String valueToReturn = prop.getProperty(key).toString();

        if (StringUtil.isBlank(key) || Objects.isNull(key) || Objects.isNull(valueToReturn)) {
            throw new InvalidFilePathException("VALUE IS NOT SPECIFIED FOR THE KEY" + key + "in properties files");
        }

        return valueToReturn;
    }

    @DataProvider(name = "browsers", parallel = true)
    public static Object[][] dataProviderDataProperties(ConfigProperties cp) {
        String[] arrayBrowsers = prop.getProperty(cp.toString().toLowerCase()).split(",");
        Object[][] data = new Object[arrayBrowsers.length][1];


        for (int i = 0; i < arrayBrowsers.length; i++) {
            data[i][0] = arrayBrowsers[i].trim();
        }
        return data;
    }
}
