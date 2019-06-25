package api;

import org.testng.TestNG;

public class APITestRun {

    public static void main(String[] args) {
        //读取测试用例
        APITestInfo apiTestInfo=new APITestInfo();
        apiTestInfo.readTestCase();
        int size = apiTestInfo.getBlockingQueue().size();
        for (int i = 0; i < size; i++) {
            TestNG testNG = new TestNG();
            testNG.setTestClasses(new Class[]{APITestExecutor.class});
            testNG.run();
        }

    }
}
