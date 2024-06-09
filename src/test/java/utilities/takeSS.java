package utilities;


import base.BaseTest;
import freemarker.core.OutputFormat;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestResult;

import java.io.File;
import java.io.IOException;

public  class takeSS  extends BaseTest {

    public static void takeScreenS(ITestResult result) throws IOException {
        String FileName = System.getProperty("user.dir") + File.separator + "src/test/resources/screenshots" + result.getMethod().getMethodName();
        File ss = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(ss , new File(FileName+ ".png"));
    }
}
