package testCases;

import base.BaseTest;
import base.BaseTestParallel;
import base.DriverFactoryParallel;
import org.testng.annotations.Test;
import pages.FaceLoginPage;

public class TestFLP extends BaseTest{

    @Test
    public void test() {
        new FaceLoginPage(getDriverBT()).loginFacebook();
    //  new FaceLoginPage(DriverFactoryParallel.getInstance().getDriver()).loginFacebook();

    }

    @Test
    public void testUsingThreadSafe() {
        new FaceLoginPage(getDriverBT()).loginFacebookUsingLocalThread();
      // new FaceLoginPage(DriverFactoryParallel.getInstance().getDriver()).loginFacebookUsingLocalThread();


    }

}
