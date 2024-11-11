package utilities.Listeners;

import org.testng.IAnnotationTransformer;
import org.testng.annotations.ITestAnnotation;
import utilities.GetData.WhichTestToExecuteExcel;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;


public class AnnotationTransformer implements IAnnotationTransformer { //Using retyAnalyzer and Invocation COunt might cause some erratic results
    //Method testMethod will returm method ran in the test Executed, method interceptor return a list of methods ran
    @Override
    public void transform(ITestAnnotation annotation, Class testClass, Constructor testConstructor, Method testMethod) {
        IAnnotationTransformer.super.transform(annotation, testClass, testConstructor, testMethod);
        annotation.setRetryAnalyzer(RetryAnalyzer.class);
        annotation.setDataProvider("SelectTestToRun");

    }



}
