package testCases;

import base.BaseTestParallel;
import base.BaseTestTestContext;
import org.testng.annotations.Test;
import pages.FaceLoginPage;
import utilities.GetData.ReaderExcelFiles;
import utilities.TestContext;

public class TestFLP extends BaseTestParallel {


    @Test
    public void testPrueba11()  {
       new FaceLoginPage().loginFacebook();//

    }
    @Test(dataProviderClass = ReaderExcelFiles.class, dataProvider = "DataHashMap")
    public void testP2(Object obj)  {
        new FaceLoginPage().newExceltestData2( obj);//  using BaseTestParallel,  in page TestFLP you can get the driver and not pass in constructor as in  example, could be use like this --> new FaceLoginPage().loginFacebook();

    }
    @Test(dataProviderClass = ReaderExcelFiles.class, dataProvider = "DataHashMap")
    public void testPdgfsijflkdsjh(Object obj)  {
        new FaceLoginPage().newExceltestData2( obj);//  using BaseTestParallel,  in page TestFLP you can get the driver and not pass in constructor as in  example, could be use like this --> new FaceLoginPage().loginFacebook();

    }
    @Test(dataProviderClass = ReaderExcelFiles.class, dataProvider = "DataHashMap")
    public void fg(Object obj)  {
        new FaceLoginPage().newExceltestData2( obj);//  using BaseTestParallel,  in page TestFLP you can get the driver and not pass in constructor as in  example, could be use like this --> new FaceLoginPage().loginFacebook();

    }
    @Test
    public void testPara22()  {
        new FaceLoginPage().loginFacebook3();//

    }


    public void testUsingThreadSafe() {

        ///  new FaceLoginPage(tc.GetDriver(),tc.getLogger()).loginFacebook(); //.Pasing the driver in constructor, With Singleton method using TestContext to get and set driver, using BaseTestTestContext


    }

}
