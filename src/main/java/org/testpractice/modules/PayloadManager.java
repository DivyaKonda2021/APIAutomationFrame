package org.testpractice.modules;

import com.github.javafaker.Faker;
import com.google.gson.Gson;
//import org.checkerframework.checker.units.qual.A;
import org.testpractice.pojo.*;
import org.testpractice.pojo.BookingResponse;

public class PayloadManager {

     static Gson gson;
public static String createBookingPayloadAsString(){
    Booking booking=new Booking();
    Faker faker=new Faker();
    booking.setFirstname("Divya");
    booking.setLastname(faker.name().lastName());
    booking.setTotalprice(faker.random().nextInt(1,1000));
    booking.setDepositpaid(faker.random().nextBoolean());

    Bookingdates bookingdates = new Bookingdates();
    bookingdates.setCheckin("2023-01-01");
    bookingdates.setCheckout("2023-01-02");
    booking.setBookingdates(bookingdates);
    booking.setAdditionalneeds("Breakfast");
    gson=new Gson();
    return gson.toJson(booking);
}

public String updateBookingPayload(){

    Booking booking=new Booking();
    booking.setFirstname("Lakshmi");
    booking.setLastname("Nalla");
    booking.setTotalprice(3456);
    booking.setDepositpaid(false);

    Bookingdates bookingdates = new Bookingdates();
    bookingdates.setCheckin("2023-02-01");
    bookingdates.setCheckout("2023-02-02");
    booking.setBookingdates(bookingdates);
    booking.setAdditionalneeds("Dinner");
    return gson.toJson(booking);
}

public BookingResponse bookingResponseJava(String responseString){

    gson=new Gson();
    BookingResponse bookingResponse=gson.fromJson(responseString, BookingResponse.class);
    return bookingResponse;

}

public static String tokenPayload(){
    Auth auth=new Auth();

    auth.setUsername("admin");
    auth.setPassword("password123");

    gson=new Gson();
    String jsonPayloadString=gson.toJson(auth);
    System.out.println("Payload set to"+ jsonPayloadString);
    return jsonPayloadString;
}

public static String getTokenfromJSON(String tokenResponse){
    gson=new Gson();
    TokenResponse tokenResponse1=gson.fromJson(tokenResponse, TokenResponse.class);
    return tokenResponse1.getToken();
}

    public Booking getResponsefromJSON(String getResponse){
        gson=new Gson();
        Booking booking=gson.fromJson(getResponse, Booking.class);
        return booking;
    }


}
