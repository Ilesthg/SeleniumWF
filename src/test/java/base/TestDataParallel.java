package base;

import java.util.HashMap;

public class TestDataParallel {

    private TestDataParallel(){

    }
    private static   TestDataParallel instance = new TestDataParallel();



    public static TestDataParallel getInstance(){
        return instance;
    }


    ThreadLocal<HashMap<String,String>> testDataParallel = new ThreadLocal<>();

    public HashMap<String,String> getTestData(){
            return testDataParallel.get();
    }


    public void setTestDataParallel(HashMap<String, String> testData) {
        testDataParallel.set(testData);
    }
    public void closeTestData() {
        testDataParallel.remove();

    }



}
