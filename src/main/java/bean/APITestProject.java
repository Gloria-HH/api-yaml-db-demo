package bean;

import java.util.HashMap;
import java.util.Map;

public class APITestProject {

    private String baseUrl;
    private Map<String, Object> globalRequestParameters = new HashMap<String,Object>();
    private Map<String, APITestSuite> testSuites = new HashMap<String,APITestSuite>();

    public String getBaseUrl() {
        return baseUrl;
    }

    public void setBaseUrl(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    public Map<String, Object> getGlobalRequestParameters() {
        return globalRequestParameters;
    }

    public void setGlobalRequestParameters(Map<String, Object> globalRequestParameters) {
        this.globalRequestParameters = globalRequestParameters;
    }

    public Map<String, APITestSuite> getTestSuites() {
        return testSuites;
    }

    public void setTestSuites(Map<String, APITestSuite> testSuites) {
        this.testSuites = testSuites;
    }
}
