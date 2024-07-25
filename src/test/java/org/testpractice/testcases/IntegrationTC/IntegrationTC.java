package org.testpractice.testcases.IntegrationTC;

//import io.qameta.allure.Description;
//import io.qameta.allure.Owner;
import io.restassured.RestAssured;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.Test;
import org.testpractice.base.Basetest;
import org.testpractice.endpoints.APIConstants;
import org.testpractice.pojo.Booking;
import org.testpractice.pojo.BookingResponse;


import static io.restassured.RestAssured.requestSpecification;
import static org.assertj.core.api.Assertions.assertThat;

public class IntegrationTC extends Basetest {

    //{check line no 72 once if there is any error}

    //Create a Booking, Get it, Update it & Delete it

    @Test(groups = "Integration", priority = 1)
//    @Owner("Divya")
//    @Description("TC#1 Verify Create Booking")
    public void testCreateBooking(ITestContext iTestContext){
        //Assert.assertTrue(true);
      iTestContext.setAttribute("token", getToken());

      requestSpecification.basePath(APIConstants.Create_Update_BookingURL);

      response= RestAssured.given(requestSpecification).when()
              .body(payloadManager.createBookingPayloadAsString()).post();

      validatableResponse=response.then().log().all();
      validatableResponse.statusCode(200);

        BookingResponse bookingResponse= payloadManager.bookingResponseJava(response.asString());
        assertThat(bookingResponse.getBookingid()).isNotNull();
        assertThat(bookingResponse.getBooking().getLastname()).isNotBlank();
        assertThat(bookingResponse.getBooking().getFirstname()).isEqualTo("Divya");

        iTestContext.setAttribute("bookingid", bookingResponse.getBookingid());
    }

    @Test(groups = "Integration", priority = 2)
//    @Owner("Divya")
//    @Description("TC#2 Get Booking")
    public void testVerifyBookingid(ITestContext iTestContext){
        //Assert.assertTrue(true);
        String bookingid= iTestContext.getAttribute("bookingid").toString();
        String getBookingId_URL= APIConstants.Create_Update_BookingURL + "/" +bookingid;

        requestSpecification.basePath(getBookingId_URL);

        response= RestAssured.given(requestSpecification).when().get();

        validatableResponse=response.then().log().all();
        validatableResponse.statusCode(200);

        Booking booking= payloadManager.getResponsefromJSON(response.asString());

        assertThat(booking.getLastname()).isNotBlank();
        assertThat(booking.getFirstname()).isEqualTo("Divya");
    }

    @Test(groups = "Integration", priority = 3)
//    @Owner("Divya")
//    @Description("TC#3 Verify update Booking")
    public void testUpdateBooking(ITestContext iTestContext){
        //Assert.assertTrue(true);
        System.out.println("Token - " + iTestContext.getAttribute("token"));
        String token= (String) iTestContext.getAttribute("token");

        String bookingid= iTestContext.getAttribute("bookingid").toString();
        String getBookingId_URL= APIConstants.Create_Update_BookingURL + "/" +bookingid;

        requestSpecification.basePath(getBookingId_URL);

        response= RestAssured.given(requestSpecification).cookie("token", token)
                .when().body(payloadManager.updateBookingPayload()).put();

        validatableResponse=response.then().log().all();
        validatableResponse.statusCode(200);

        Booking booking= payloadManager.getResponsefromJSON(response.asString());

        assertThat(booking.getLastname()).isNotBlank();
        assertThat(booking.getFirstname()).isEqualTo("Divya");
        assertThat(booking.getLastname()).isEqualTo("Nalla");

    }

    @Test(groups = "Integration", priority = 4)
//    @Owner("Divya")
//    @Description("TC#4 Delete Booking")
    public void testDeleteBooking(ITestContext iTestContext){
//        Assert.assertTrue(true);
        System.out.println("Token - " + iTestContext.getAttribute("token"));
        String token= (String) iTestContext.getAttribute("token");

        String bookingid= iTestContext.getAttribute("bookingid").toString();
        String getBookingId_URL= APIConstants.Create_Update_BookingURL + "/" +bookingid;

        requestSpecification.basePath(getBookingId_URL).cookie("token", token);

        response= RestAssured.given(requestSpecification).when().delete();

        validatableResponse=response.then().log().all();
        validatableResponse.statusCode(201);

        Booking booking= payloadManager.getResponsefromJSON(response.asString());

    }

}
