

package testCases;

import base.BaseTest;
import org.testng.annotations.Test;
import pages.FacebookLoginPage;
import utilities.ReaderExcelFiles;

import java.io.IOException;

public class Test1 extends BaseTest {

    //Test for Excel Sheet

    @Test
    public void gotoPage1(){
        FacebookLoginPage.loginFacebook();
    }
    @Test(dataProviderClass = ReaderExcelFiles.class, dataProvider = "ExcelData")
    public void Sheet1(String username, String password) throws IOException {
        FacebookLoginPage.Sheet1(username, password);
    }

    @Test(dataProviderClass = ReaderExcelFiles.class, dataProvider = "data")
    public void Sheet2(String username, String password) throws IOException {
        FacebookLoginPage.Sheet2(username, password);
    }


    @Test
    public void readExcelsheet() throws IOException, InterruptedException {
        ReaderExcelFiles.readExcel();
    }
    @Test
    public void testFindByLocators() throws IllegalAccessException {
    FacebookLoginPage.locatorNewStrategyFindBy();
    }
}