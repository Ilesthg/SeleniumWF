package utilities.GetData;

import java.lang.reflect.Method;
import java.util.*;

import org.testng.annotations.DataProvider;

public final class WhichTestToExecuteExcel {
    //Cannot make constructor private as dataProvider use reflection to instantiate  class, so constructor must be default without params


    //This method will provide logic, if your tests(name) stored in TestClass are matching with test  in excelsheet and execute is Y, then those Tests will be executed
    //This data provider is called n times, depending on amount of tests that use this dataProvider   List<HashMap<String, String>> listOfHashMap= FromExcel.returnListofHashMap("Data");
    //Here we provided 2 optimizations  in code, one is load the data just 1 time
    // Second is remove thoose index of testCases already used to run tests


    List<HashMap<String, String>> listOfHashMap = new ArrayList<>(); //empty list

    @DataProvider(name = "SelectTestToRun") ////@DataProvider(name = "SelectTestToRun" , parallel = true) and in testng specific the thread count
    public Object[] returnTestsToExecute(Method m) {
        if (listOfHashMap.isEmpty()) {//1
            listOfHashMap = FromExcel.returnListofHashMap("Data");
        }



        List<HashMap<String, String>> smallListOfHashMaps = new ArrayList<>();

        for (int i = 0; i < listOfHashMap.size(); i++) {
            if (listOfHashMap.get(i).get("testName").equalsIgnoreCase(m.getName())) {
                if (listOfHashMap.get(i).get("execute").equalsIgnoreCase("y")) {
                    smallListOfHashMaps.add(listOfHashMap.get(i));
                }
            }

        }

       // listOfHashMap.removeAll(smallListOfHashMaps);// 2 Remove thoose index of testCases already used to run tests
        return smallListOfHashMaps.toArray();

    }
}
