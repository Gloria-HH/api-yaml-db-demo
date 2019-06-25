package api;

import bean.APITestCase;
import com.alibaba.fastjson.JSONObject;
import common.RestAssuredAPI;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.response.ValidatableResponse;
import listener.TestListenerImpl;
import org.hamcrest.Matcher;
import org.hamcrest.Matchers;
import org.testng.annotations.*;
import retry.RetryAnalyzerImpl;
import utils.MatcherUtils;

import java.util.Map;

import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;

@Listeners({TestListenerImpl.class})
public class APITestExecutor {
    private APITestCase testCase;

    @BeforeMethod
    @Parameters({"baseURL"})
    public void init(@Optional String baseURL) {
        if (baseURL == null) {
            baseURL = APITestInfo.getBaseUrl();
        }

        RestAssured.baseURI = baseURL;
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
        RestAssured.requestSpecification = new RequestSpecBuilder().build().accept(JSON).contentType(JSON);
        testCase = APITestInfo.getNewTestCase();
    }

    @Test
    public void runCase() {

        ValidatableResponse validatableResponse = RestAssuredAPI.getValidatableResponse(testCase.getMethod(),
                testCase.getRequestParameters(), testCase.getApiUrl(), APITestInfo.getGlobalRequestParameters());
        Map<String, Map<String, Object>> resultVerify = testCase.getResultVerify();

        resultVerify.forEach((condition, v) -> {
            v.forEach((key, value) -> {
                try {
                    Matcher matcher = MatcherUtils.getMatcher(condition, value);
                    validatableResponse.body(key, matcher);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
        });

        validatableResponse.log().all();

        updateTestCaseInfo();
    }

    /**
     * 更新测试用例的名字和描述信息
     */
    public void updateTestCaseInfo() {
        //todo allure

    }
}
