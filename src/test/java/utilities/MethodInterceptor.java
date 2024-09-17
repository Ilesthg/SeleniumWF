package utilities;

import org.testng.IMethodInstance;
import org.testng.IMethodInterceptor;
import org.testng.ITestContext;

import java.util.List;

public class MethodInterceptor implements IMethodInterceptor {

    public void modifyTestRunTime() {



    }

    @Override
    public List<IMethodInstance> intercept(List<IMethodInstance> list, ITestContext iTestContext) {


        for (int i = 0; i < list.size(); i++) {


        }
        return null;
    }
}
