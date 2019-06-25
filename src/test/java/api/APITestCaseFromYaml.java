package api;

import bean.APITestCase;
import bean.APITestProject;
import bean.APITestSuite;
import com.alibaba.fastjson.JSONObject;
import common.Constants;
import common.RestAssuredAPI;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.response.ValidatableResponse;
import listener.TestListenerImpl;
import org.hamcrest.Matcher;
import org.hamcrest.Matchers;
import org.testng.annotations.*;
import utils.MatcherUtils;
import utils.Utils;
import utils.YamlUtils;

import java.lang.reflect.InvocationTargetException;
import java.net.MalformedURLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;

@Listeners({TestListenerImpl.class})
public class APITestCaseFromYaml {

    private static Map<String, Object> testParametersMap = new HashMap<String, Object>();
    private static APITestProject apiTestProject;

    @BeforeTest
    public void beforeTest() {
        //读取测试用例
        apiTestProject = YamlUtils.getApiFromYaml("/apiTestProject.yaml", APITestProject.class);
        testParametersMap.putAll(apiTestProject.getGlobalRequestParameters());
    }


    @BeforeMethod
    @Parameters({"baseUrl", "appVersion"})
    public void init(@Optional String baseUrl, @Optional String appVersion) {
        //读取url和版本信息
        if (baseUrl == null) {
            baseUrl = apiTestProject.getBaseUrl();
        }
        if (appVersion != null) {
            testParametersMap.put("apiVersion", appVersion);
        }
        //初始化RestAssured参数
        RestAssured.baseURI = baseUrl;
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
        RestAssured.requestSpecification = new RequestSpecBuilder().build().accept(JSON).contentType(JSON);

    }

    @Test
    public void runTestCase() {
        Map<String, APITestSuite> testSuites = apiTestProject.getTestSuites();
        //读取测试集
        for (Map.Entry<String, APITestSuite> entry : testSuites.entrySet()) {
            //读取每个测试集的测试用例
            List<APITestCase> testCases = entry.getValue().getTestCaseList();
            for (APITestCase testCase : testCases) {
                ValidatableResponse validatable = RestAssuredAPI.getValidatableResponse(testCase.getMethod(),
                        testCase.getRequestParameters(), testCase.getApiUrl(), testParametersMap);
                //验证响应参数
                Map<String, Map<String, Object>> resultVerify = testCase.getResultVerify();
                resultVerify.forEach((condition, item
                ) -> {
                    item.forEach((key, value) -> {
                        try {
                            Matcher matcher = MatcherUtils.getMatcher(condition, value);
                            validatable.body(key, matcher);
                        } catch (IllegalAccessException e) {
                            e.printStackTrace();
                        } catch (InvocationTargetException e1) {
                            e1.printStackTrace();
                        } catch (NoSuchMethodException e) {
                            e.printStackTrace();
                        }

                    });
                });

                validatable.log().all();
            }
        }

    }


}