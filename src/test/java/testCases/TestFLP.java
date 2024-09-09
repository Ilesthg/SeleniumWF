package testCases;

import base.BaseTest;
import base.BaseTestC;
import base.BaseTestParallel;
import base.DriverFactoryParallel;
import org.testng.annotations.Test;
import pages.FaceLoginPage;
import utilities.SeleniumMethodsCust;

public class TestFLP extends BaseTestC{



    @Test
    public void test() {

        //new FaceLoginPage(getDriverBT()).loginFacebook();// Pasing the driver in constructor, using BaseTest
      //new FaceLoginPage(DriverFactoryParallel.getInstance().getDriver()).loginFacebook();// Pasing the driver in constructor, using BaseTestParallel, which is reduntant cause in page class you can get the driver and not pass in constructor as in below example, could be use like this --> new FaceLoginPage().loginFacebook();
        new FaceLoginPage(tc.getDriver()).loginFacebook(); //Need to investigate.....With Singleton method using TestContex to get and set driver, using BaseTestC

    }

    @Test
    public void testUsingThreadSafe() {
     //  new FaceLoginPage(getDriverBT()).loginFacebookUsingLocalThread();
      // new FaceLoginPage(DriverFactoryParallel.getInstance().getDriver()).loginFacebookUsingLocalThread();
       // new FaceLoginPage(tc.getDriver()).loginFacebookUsingLocalThread();


    }

}
