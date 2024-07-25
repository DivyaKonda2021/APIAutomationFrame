package org.testpractice.base;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;

import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.BeforeTest;
import org.testpractice.actions.AssertActions;
import org.testpractice.endpoints.APIConstants;
import org.testpractice.modules.PayloadManager;
//import io.restassured.path.json.Jsonpath;

public class Basetest {
    //Base Test is Father and Testcase is son...using single inheritance

    public RequestSpecification requestSpecification;
    public AssertActions assertActions;
    public PayloadManager payloadManager;
    //public JsonPath jsonPath;
    public Response response;
    public ValidatableResponse validatableResponse;

    @BeforeTest
    public void setConfig(){
        System.out.println("Configurations setting is done");
        payloadManager = new PayloadManager();
        assertActions = new AssertActions();
//        requestSpecification= RestAssured.given().baseUri(APIConstants.Base_URL)
//                .contentType(ContentType.JSON).log().all();
        requestSpecification = new RequestSpecBuilder().setBaseUri(APIConstants.Base_URL)
                .addHeader("Content-Type","application/json")
                .build().log().all();

    }

    public String getToken(){
        //Setup the URL
        requestSpecification =
                RestAssured.given().baseUri(APIConstants.Create_Update_BookingURL)
                        .basePath(APIConstants.Auth_URL);
        //Setting up the payload
        String payload = PayloadManager.tokenPayload();
        //creating a token
        requestSpecification
                .contentType(ContentType.JSON)
                .body(payload).when().post();
        //Extracting the token via deserialisation
        String token = payloadManager.getTokenfromJSON(response.asString());
        //Verify
        return token;



    }



}
