package org.testpractice.actions;

import io.restassured.response.Response;

import static org.testng.AssertJUnit.assertEquals;

public class AssertActions {

    public void VerifyResponseBody(String actual, String expected, String description) {
        assertEquals(actual, expected, description);
    }

    public void VerifyResponseBody(float actual, float expected, String description) {
        assertEquals(description, actual, expected);
    }

    public void VerifyResponseBody(int actual, int expected, String description) {
        assertEquals(String.valueOf(actual), expected, description);
    }

    public void VerifyResponseBody(double actual, double expected, String description) {
        assertEquals(description, actual, expected);
    }

    public void verifyResponseBody(boolean actual, boolean expected, String description) {
        assertEquals(String.valueOf(actual), expected, description);
    }

    public void VerifyStatuscodeInvalidReq(Response response) {
        assertEquals(String.valueOf(String.valueOf(response.getStatusCode())
                        .startsWith("50")), true,
                "value of code is " + response.getStatusCode());
    }

//    public void VerifyStatuscode(Response response){
//        assertEquals((String.valueOf(response.getStatusCode()).startsWith("20")),
//                true);
//    }

    public void VerifyStatuscode(Response response, String expected) {
        assertEquals(response.getStatusCode(), expected);
    }
}