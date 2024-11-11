package utilities;



import base.BaseTest;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class takeSS extends BaseTest {

    public  void takeScreenS(ITestResult result) {
        //WebDriver driver =  driverFromBaseTest();

        SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyy HH-mm-ss");
        Date date = new Date();
        // String ssDate =  date.toString().replace(" ", "-").replace(":","-");
        String ssDate = format.format(date);
        String FileName = System.getProperty("user.dir") + "/src/test/resources/screenshots/"  +ssDate +"_"+result.getName()+".png";
        File ss = ((TakesScreenshot) getDriverBT()).getScreenshotAs(OutputType.FILE);

        try {
            FileUtils.copyFile(ss, new File(FileName));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


}
