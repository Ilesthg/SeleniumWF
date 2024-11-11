package utilities.Listeners;

import org.testng.IMethodInstance;
import org.testng.IMethodInterceptor;
import org.testng.ITestContext;
import utilities.GetData.FromExcel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MethodInterceptor implements IMethodInterceptor {
    @Override
    public List<IMethodInstance> intercept(List<IMethodInstance> list, ITestContext iTestContext) {

        List<HashMap<String, String>> listofHashMap = FromExcel.returnListofHashMap("Runner");// tHIS method is only called on time

        List<IMethodInstance> endResult = new ArrayList<>();

        for (int i = 0; i < list.size(); i++) {
            for (int j = 0; j < listofHashMap.size(); j++) {
                if (list.get(i).getMethod().getMethodName().equalsIgnoreCase(listofHashMap.get(j).get("testName"))) {
                    if (listofHashMap.get(j).get("execute").equalsIgnoreCase("y")) {
                       // list.get(i).getMethod().setInvocationCount(Integer.parseInt(listofHashMap.get(j).get("count")));
                        System.out.println("si");
                        endResult.add(list.get(i));
                    }
                }
            }
        }
       // iTestContext.setAttribute("testsToRun", endResult);
        return endResult;
    }
}
