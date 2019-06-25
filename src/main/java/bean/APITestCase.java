package bean;

import java.util.HashMap;
import java.util.Map;

public class APITestCase {

    private String name;
    private String description;
    private String apiUrl;
    private String method;
    private Map<String, String> sqlCommands = new HashMap<String, String>();
    private Map<String, Object> requestParameters = new HashMap<String, Object>();
    private Map<String, Map<String, Object>> resultVerify = new HashMap<String, Map<String, Object>>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getApiUrl() {
        return apiUrl;
    }

    public void setApiUrl(String apiUrl) {
        this.apiUrl = apiUrl;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public Map<String, String> getSqlCommands() {
        return sqlCommands;
    }

    public void setSqlCommands(Map<String, String> sqlCommands) {
        this.sqlCommands = sqlCommands;
    }

    public Map<String, Object> getRequestParameters() {
        return requestParameters;
    }

    public void setRequestParameters(Map<String, Object> requestParameters) {
        this.requestParameters = requestParameters;
    }

    public Map<String, Map<String, Object>> getResultVerify() {
        return resultVerify;
    }

    public void setResultVerify(Map<String, Map<String, Object>> resultVerify) {
        this.resultVerify = resultVerify;
    }

    @Override
    public String toString() {
        return "APITestCase{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", apiUrl='" + apiUrl + '\'' +
                ", method='" + method + '\'' +
                ", sqlCommands=" + sqlCommands +
                ", requestParameters=" + requestParameters +
                ", resultVerify=" + resultVerify +
                '}';
    }
}
