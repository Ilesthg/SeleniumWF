package testCases;

import base.BaseTestParallel;
import base.TestDataParallel;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;
import pages.FaceLoginPage;
import utilities.GetData.FromExcel;
import utilities.GetData.WhichTestToExecuteExcel;


import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;


public class TestFLP extends BaseTestParallel{


    @Test(dataProviderClass = WhichTestToExecuteExcel.class, dataProvider = "SelectTestToRun")
    public void test1(HashMap<String,String> data)  {
       new FaceLoginPage().probando1(data);//
    }

    @Test(dataProviderClass = WhichTestToExecuteExcel.class, dataProvider = "SelectTestToRun")
    public void test2(HashMap<String,String> data)  {
        new FaceLoginPage().probando2(data);//
    }


  /*  @Test(dataProviderClass = FromExcel.class, dataProvider = "dataInObject")
    public void test3(Object obj)  {
        new FaceLoginPage().probando3(obj);//  using BaseTestParallel,  in page TestFLP you can get the driver and not pass in constructor as in  example, could be use like this --> new FaceLoginPage().loginFacebook();

    }*/
   /* @Test
    public void doSomething() throws IOException, InvalidFormatException {

        new FaceLoginPage().dosome();


    }*/

    public void testUsingThreadSafe() {

        ///  new FaceLoginPage(tc.GetDriver(),tc.getLogger()).loginFacebook(); //.Pasing the driver in constructor, With Singleton method using TestContext to get and set driver, using BaseTestTestContext


    }

}
