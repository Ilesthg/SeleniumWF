package base;

import java.util.HashMap;
import java.util.List;

public final class TestDataParallel {

    private TestDataParallel(){

    }
    private ThreadLocal<HashMap<String,String>> testDataParallel = new ThreadLocal<>();
    private static   TestDataParallel instance = new TestDataParallel();
    public static TestDataParallel getInstance(){
        return instance;
    }

    public HashMap<String,String> getTestData(){
            return testDataParallel.get();
    }


    public void setTestDataParallel(HashMap<String,String> testData) {
        testDataParallel.set(testData);
    }
    public void closeTestData() {
        testDataParallel.remove();

    }



}
