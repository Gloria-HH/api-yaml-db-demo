package bean;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class APITestSuite {
    private String description;
    private List<APITestCase> testCaseList = new ArrayList<APITestCase>();
    private Map<String, Object> testSuiteParameters = new HashMap<String, Object>();

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<APITestCase> getTestCaseList() {
        return testCaseList;
    }

    public void setTestCaseList(List<APITestCase> testCaseList) {
        this.testCaseList = testCaseList;
    }

    public Map<String, Object> getTestSuiteParameters() {
        return testSuiteParameters;
    }

    public void setTestSuiteParameters(Map<String, Object> testSuiteParameters) {
        this.testSuiteParameters = testSuiteParameters;
    }
}