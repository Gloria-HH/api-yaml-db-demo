package common;

import bean.APITestCase;
import com.alibaba.fastjson.JSONObject;
import io.restassured.response.ValidatableResponse;

import java.util.Map;

import static io.restassured.RestAssured.given;

public class RestAssuredAPI {

    public static ValidatableResponse getValidatableResponse(String method, Map<String, Object> requestParameters, String apiUrl, Map<String, Object> globalRequestParameters) {
        ValidatableResponse validatable;//post提交
        if (Constants.METHOD_POST.equals(method)) {
            JSONObject jsonObject = new JSONObject();
            globalRequestParameters.putAll(requestParameters);
            jsonObject.putAll(globalRequestParameters);
            validatable = given().body(jsonObject.toJSONString()).when().post(apiUrl).then();
        } else {//get方法提交
            validatable = given().queryParams(requestParameters).when().get(apiUrl).then();
        }
        return validatable;
    }
}
