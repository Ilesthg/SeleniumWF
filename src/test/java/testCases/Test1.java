

package testCases;


import base.BaseTest;
import org.testng.annotations.Test;
import pages.FacebookLoginPage;
import utilities.ReaderExcelFiles;

import java.io.IOException;

public class Test1 extends BaseTest {


   @Test
    public void gotoPage1() {

       FacebookLoginPage flp = new FacebookLoginPage();
        flp.loginFacebook();
    }

  /*  @Test
    public void gotoPage2() {
        FacebookLoginPage.loginFacebook2();

    }

    @Test
    public void testFindByLocators() throws Exception {
        FacebookLoginPage fc = new FacebookLoginPage();
     //   FacebookLoginPage.locatorNewStrategyFindBy();
        fc.locatorNewStrategyFindBy();
    }
*/

    //--------------------------------------------------------
    //Test for Excel Sheet
/*
    @Test(dataProviderClass = ReaderExcelFiles.class, dataProvider = "ExcelData")
    public void Sheet1(String username, String password) throws IOException {
        FacebookLoginPage.Sheet1(username, password);
    }

    @Test(dataProviderClass = ReaderExcelFiles.class, dataProvider = "data")
    public void Sheet2(String username, String password) {
        FacebookLoginPage.Sheet2(username, password);
    }

    @Test(dataProviderClass = ReaderExcelFiles.class, dataProvider = "ExcelTest")
    public void test22(Object obj) {
        FacebookLoginPage.excelTest(obj);
    }

    @Test(dataProviderClass = ReaderExcelFiles.class, dataProvider = "ExcelTest2")
    public void test33(Object obj) {
        FacebookLoginPage.excelTest2(obj);
    }
*/


}