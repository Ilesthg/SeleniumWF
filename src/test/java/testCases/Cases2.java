package testCases;



import base.DriverFactoryThread;
import org.testng.annotations.Test;
import pages.Logic;

public class Cases2 extends DriverFactoryThread {

    @Test
    public void test1()  {
        Logic.loginFacebookMv1();

    }

    @Test
    public void test2() {
        Logic.loginFacebookMove();

    }

    @Test
    public void test3(){
        Logic.loginFacebookMove2();

    }
}
