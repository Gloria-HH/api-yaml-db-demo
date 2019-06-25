package api;


import bean.APITestCase;
import bean.APITestProject;
import bean.APITestSuite;
import utils.YamlUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class APITestInfo {
    private static String baseUrl;
    private static Map<String, Object> globalRequestParameters = new HashMap<String, Object>();
    private static BlockingQueue<APITestCase> blockingQueue;
    private static APITestCase currentTestCase;

    public APITestInfo() {

    }

    public void readTestCase() {
        APITestProject apiTestProject = YamlUtils.getApiFromYaml("/apiTestProject.yaml", APITestProject.class);
        baseUrl = apiTestProject.getBaseUrl();
        globalRequestParameters = apiTestProject.getGlobalRequestParameters();
        /** 遍历用例并放入队例中 **/
        blockingQueue = new LinkedBlockingQueue();
        Map<String, APITestSuite> testSuites = apiTestProject.getTestSuites();
        for (Map.Entry<String, APITestSuite> entry : testSuites.entrySet()) {
            List<APITestCase> testCases = entry.getValue().getTestCaseList();
            blockingQueue.addAll(testCases);
        }
    }

    public BlockingQueue<APITestCase> getBlockingQueue() {
        return blockingQueue;
    }

    public static APITestCase getNewTestCase() {
        currentTestCase = blockingQueue.poll();
        return currentTestCase;
    }

    public static APITestCase getCurrentTestCase() {
        return currentTestCase;
    }

    public static String getBaseUrl() {
        return baseUrl;
    }


    public static Map<String, Object> getGlobalRequestParameters() {
        return globalRequestParameters;
    }


}
