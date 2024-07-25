package org.testpractice.testcases.CRUD;

//import io.qameta.allure.Description;
import io.qameta.allure.*;
//import io.qameta.allure.Severity;
//import io.qameta.allure.SeverityLevel;
import io.restassured.RestAssured;
import org.testng.annotations.Test;
import org.testpractice.base.Basetest;
import org.testpractice.endpoints.APIConstants;
import org.testpractice.modules.PayloadManager;
import org.testpractice.pojo.BookingResponse;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class TCreateBooking extends Basetest {


    @Test
   
//    @Severity(SeverityLevel.NORMAL)
//    @Description("Create Booking using framework")
    public void testCreateBooking(){
        requestSpecification.basePath(APIConstants.Create_Update_BookingURL);
        response= RestAssured.given(requestSpecification).when()
                .body(PayloadManager.createBookingPayloadAsString()).post();
        validatableResponse=response.then().log().all();
        validatableResponse.statusCode(200);

        //Deser
        BookingResponse bookingResponse= payloadManager.bookingResponseJava(response.asString());

        //AssertJ
        assertThat(bookingResponse.getBookingid()).isNotNull();
        assertThat(bookingResponse.getBooking().getFirstname()).isNotNull().isNotBlank();
        assertThat(bookingResponse.getBooking().getFirstname()).isEqualTo("Divya");

        //TestNG assertions
        assertActions.VerifyStatuscode(response, "200");
    }



}
