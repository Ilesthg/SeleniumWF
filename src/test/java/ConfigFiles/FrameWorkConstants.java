package ConfigFiles;

import utilities.GetData.PropertiesReader;

import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;

public final class FrameWorkConstants {
    private FrameWorkConstants() {
    }
private static final String routeDir = System.getProperty("user.dir");

    private static final Duration timeToWait = Duration.ofSeconds(5);
    private static final String routeDirProperties = System.getProperty("user.dir") + "/src/test/resources/configFiles/data.properties";
    private static final String routeTestExcel = System.getProperty("user.dir") + "/src/test/resources/testData/testData2.xlsx";

    private static String dirForCreateExtentReport = "";


    public static Duration getTimeToWait() {
        return timeToWait;
    }

    public static String getRoutePropertiesFile() {
        return routeDirProperties;
    }

    public static String getRouteTestExcel() {
        return routeTestExcel;
    }

    public static String decideIfDynamicReport(ConfigProperties key){
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyy HH/mm/ss");
        Date date = new Date();
        String ssDate = format.format(date);
        try {
            if (PropertiesReader.giveKeyValueFromProperties(key).equalsIgnoreCase("yes") ) {
               return dirForCreateExtentReport = routeDir + "/src/test/resources/reports/SDTE/" + System.currentTimeMillis()+".html";

            }else{
                return dirForCreateExtentReport = routeDir + "/src/test/resources/reports/SDTE/index.html";
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


}
