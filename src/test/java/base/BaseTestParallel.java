package base;

import ConfigFiles.ConfigProperties;
import com.aventstack.extentreports.ExtentReports;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.*;
import utilities.BrowserFactory;
import utilities.GetData.FromExcel;
import utilities.GetData.PropertiesReader;
import utilities.Driver.GetDriver;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.*;

public class BaseTestParallel {
    protected BaseTestParallel() {

    }


    private String browser, url;
    private ExtentReports extent;
    //Use n time
    // private  ExtentTest logger;
    private BrowserFactory bf = new BrowserFactory();


    public void initExtentReports() {
        try {
            browser = PropertiesReader.giveKeyValueFromProperties(ConfigProperties.BROWSER);
            url = PropertiesReader.giveKeyValueFromProperties(ConfigProperties.URLFACEBOOK);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        // extent = ExtentReportNG.setUpExtentReports();//OK


    }

    /*    @Parameters({"browser"})*/
    @BeforeMethod()
    public void beforeMethod(Object[] data) { //public void beforeMethod( Object[] data) {
       /* System.setProperty("org.apache.commons.logging.Log", "org.apache.commons.logging.impl.NoOpLog");
        System.setProperty("webdriver.chrome.silentOutput", "true");*/
        initExtentReports();
        Map<String, String> map = (Map<String, String>) data[0];


        //    Object [] array = FromExcel.returnExcelSheetInObject();
        // Map<String, String> map =  (Map<String, String>) data[0];
        //System.out.println(map.get("username"));


        if (Objects.isNull(DriverFactoryParallel.getInstance().getDriver())) {
            DriverFactoryParallel.getInstance().setDriver(bf.setupDriverReturn(map.get("Browser"), data));
        }



/*        if (Objects.isNull(ExtentTestFactoryParallel.getInstance().getExtentTest())) {
            ExtentTestFactoryParallel.getInstance().setExtentTest(extent.createTest(testMethod.getName()));
        }*/


        //WebDriver driver = DriverFactoryParallel.getInstance().GetDriver();
        WebDriver driver = GetDriver.getDriverFromStaticMethod();//This is OK, but is optimal?
        driver.manage().window().maximize();
        driver.navigate().to(url);


      /*  TestDataParallel.getInstance().setTestDataParallel(FromExcel.returnListofHashMap4());
        Object[] dar = TestDataParallel.getInstance().getTestData();*/

        //  System.out.println(  Arrays.toString(data)); //This work with reflection, which is supported cause i create Annotataion Listener which establish the dataProvider
    }

    @AfterMethod
    public void afterMethod(ITestResult result) {

        DriverFactoryParallel.getInstance().closeDrivers();


    }

    @AfterTest
    public void afterTest() {

      /*  if (Objects.nonNull(extent)) {
            extent.flush();
        }*/


        // SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyy HH-mm-ss");
        //Date date = new Date();
        // String ssDate = format.format(date);
        //Not working

    }

  /*  public String dosome(){
        List<HashMap<String,String>> hashMapList = FromExcel.returnListofHashMap("Cross");
        for (int i = 0; i < hashMapList.size(); i++) {
           return hashMapList.get(i).get("browser");
        }
        return null;
    }*/

}
