package testCases;



import base.BaseTestParallel;
import org.testng.annotations.Test;
import pages.Logic;

public class Cases2 extends BaseTestParallel {

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
