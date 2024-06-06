

package testCases;

import base.BaseTest;
import org.testng.annotations.Test;
import pages.FacebookLoginPage;

public class Test1 extends BaseTest {

    @Test
    public void gotoPage() {
        FacebookLoginPage.loginFacebook();
    }
}