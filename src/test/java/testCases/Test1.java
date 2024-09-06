

package testCases;


import base.BaseTest;
import org.testng.annotations.Test;
import pages.FacebookLoginPage;
import utilities.ReaderExcelFiles;

import java.io.IOException;

public class Test1 extends BaseTest {
/*
 FacebookLoginPage flp;
Is a potential thread-safety issue if your tests are running in parallel. In parallel execution,
different test methods can potentially share the same instance of TestFLP,
meaning that the same FaceLoginPage flp instance could be accessed by multiple threads.
Solution: Make flp a local variable in each test method to avoid shared state across threads:
 */

    @Test
    public void gotoPage1() {

        FacebookLoginPage flp = new FacebookLoginPage(getDriverBT());
        flp.loginFacebookTest1();
    }

    @Test
    public void gotoPage2() {
        new FacebookLoginPage(getDriverBT()).loginFacebookTest2();// Anonymous  class

    }

    @Test
    public void testFindByLocators() throws Exception {
        FacebookLoginPage fc = new FacebookLoginPage(getDriverBT());
        fc.locatorNewStrategyFindBy();
    }

    //--------------------------------------------------------
    //Test for Excel Sheet

    @Test(dataProviderClass = ReaderExcelFiles.class, dataProvider = "ExcelData")
    public void Sheet1(String username, String password) throws IOException {
        new FacebookLoginPage(getDriverBT()).Sheet1(username, password);
    }

    @Test(dataProviderClass = ReaderExcelFiles.class, dataProvider = "data")
    public void Sheet2(String username, String password) {
        new FacebookLoginPage(getDriverBT()).Sheet2(username, password);
    }

    @Test(dataProviderClass = ReaderExcelFiles.class, dataProvider = "ExcelTest")
    public void test22(Object obj) {
        new FacebookLoginPage(getDriverBT()).excelTest(obj);
    }

    @Test(dataProviderClass = ReaderExcelFiles.class, dataProvider = "ExcelTest2")
    public void test33(Object obj) {
        new FacebookLoginPage(getDriverBT()).excelTest2(obj);
    }


}