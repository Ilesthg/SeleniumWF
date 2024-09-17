package utilities;

import ConfigFiles.ConfigProperties;
import base.ExtentReportNG;
import com.aventstack.extentreports.MediaEntityBuilder;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

public final class ScreenShotsForTests {

    private ScreenShotsForTests() {
    }

  /*

    To accomplish threadSafety here, and since this two methods are statics, opted for call driver and logger from static method in another class
    We found that if we create global variable and instance with the logger and driver from static methods results in thread issues,
    To avoid is just as simple as give a call from the class and static method, like below ( ---> ExtentReportNG.getLogger() &&  --> GetDriver.getDriverFromStaticMethod())


    private static WebDriver driver = GetDriver.getDriverFromStaticMethod();
    private static ExtentTest logger = ExtentReportNG.getLogger();
---------------------------------------------------------
 WebDriver driver = GetDriver.getDriverFromStaticMethod();

Its valid to assign to a non static variable a static method which returns something -->
    Yes, it is perfectly fine to assign the result of a static method to a non-static variable.
    In your case, you're assigning the return value of the static method getDriverFromStaticMethod() to the non-static variable driver.
    This is valid because the static method is simply returning an object (in this case, a WebDriver instance),
    and it doesn’t matter whether the method itself is static or not when you are assigning the return value.
    */



    public static void passMessage(String message) {
        ExtentReportNG.getLoggerFromStaticMethod().pass(message);
    }
    public static void failMessage(String message) {
        ExtentReportNG.getLoggerFromStaticMethod().fail(message);
    }
    public static void skipMessage(String message) {
        ExtentReportNG.getLoggerFromStaticMethod().skip(message);
    }
    public static void successTestWithSS(String message, boolean screenShotNeeded) throws Exception {

        if (PropertiesReader.giveKeyValueFromProperties(ConfigProperties.SUCCESSSCREENSHOT).equalsIgnoreCase("yes") && screenShotNeeded){
            ExtentReportNG.getLoggerFromStaticMethod().pass(message, MediaEntityBuilder.createScreenCaptureFromBase64String(getBase64Image()).build());
           /* ExtentReportNG.getLogger().log(Status.PASS, MarkupHelper.createLabel(message, ExtentColor.GREEN));
            ExtentReportNG.getLogger().pass(MediaEntityBuilder.createScreenCaptureFromBase64String(getBase64Image()).build());*/
        }else {
            passMessage(message);
        }
    }

    public static void failedTestWithSS(String message, boolean screenShotNeeded) throws Exception {

        if (PropertiesReader.giveKeyValueFromProperties(ConfigProperties.FAILEDSCREENSHOT).equalsIgnoreCase("yes") && screenShotNeeded){
            ExtentReportNG.getLoggerFromStaticMethod().fail(message, MediaEntityBuilder.createScreenCaptureFromBase64String(getBase64Image()).build());
        }else {
            failMessage(message);
        }
    }
    public static void skipTestWithSS(String message, boolean screenShotNeeded) throws Exception {

        if (PropertiesReader.giveKeyValueFromProperties(ConfigProperties.SKIPSCREENSHOT).equalsIgnoreCase("yes") && screenShotNeeded){
            ExtentReportNG.getLoggerFromStaticMethod().skip(message, MediaEntityBuilder.createScreenCaptureFromBase64String(getBase64Image()).build());
        }else {
            skipMessage(message);
        }

    }

    private static String getBase64Image(){
        return ((TakesScreenshot) GetDriver.getDriverFromStaticMethod()).getScreenshotAs(OutputType.BASE64);
    }




}
