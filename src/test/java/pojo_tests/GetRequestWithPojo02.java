package pojo_tests;

import base_urls.HerokuappBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;
import pojos.BookingDatePojo;
import pojos.BookingPojo;
import utils.JsonUtil;

import static io.restassured.RestAssured.*;
import static org.junit.Assert.*;

public class GetRequestWithPojo02 extends HerokuappBaseUrl {

    /*
     When
 		I send GET Request to the URL https://restful-booker.herokuapp.com/booking/2
 	Then
 		Status code is 200
 		And response body is like {
                                    "firstname": "Mary",
                                    "lastname": "Smith",
                                    "totalprice": 647,
                                    "depositpaid": false,
                                    "bookingdates": {
                                        "checkin": "2016-02-05",
                                        "checkout": "2021-01-16"
                                     }
                                  }
     */
    @Test
    public void get01(){
        //1)Set the url
        spec.pathParams("first", "booking",
                "second", 2);

        //2)Set the expected data
        BookingDatePojo bookingDatePojo = new BookingDatePojo("2016-02-05", "2021-01-16");
        BookingPojo expectedPojo = new BookingPojo("Mary", "Smith", 647, false, bookingDatePojo);
        System.out.println(expectedPojo);

        //3)Send the request
        Response response = given().spec(spec).when().get("/{first}/{second}");
        response.prettyPrint();

        //4)Assert the output
        //1)Use GSON
        BookingPojo actualPojo = response.as(BookingPojo.class);
        System.out.println(actualPojo);

//        assertEquals(200, response.getStatusCode());
//        assertEquals(expectedPojo.getFirstName(), actualPojo.getFirstName());
//        assertEquals(expectedPojo.getLastName(), actualPojo.getLastName());
//        assertEquals(expectedPojo.getTotalPrice(), actualPojo.getTotalPrice());
//        assertEquals(expectedPojo.isDepositPaid(), actualPojo.isDepositPaid());
//        assertEquals(expectedPojo.getBookingDates().getCheckIn(), actualPojo.getBookingDates().getCheckIn());
//        assertEquals(expectedPojo.getBookingDates().getCheckOut(), actualPojo.getBookingDates().getCheckOut());

        //2)Use Object Mapper
        BookingPojo actualPojo02 = JsonUtil.convertJsonToJava(response.asString(), BookingPojo.class);

        assertEquals(200, response.getStatusCode());
        assertEquals(expectedPojo.getFirstName(), actualPojo02.getFirstName());
        assertEquals(expectedPojo.getLastName(), actualPojo02.getLastName());
        assertEquals(expectedPojo.getTotalPrice(), actualPojo02.getTotalPrice());
        assertEquals(expectedPojo.isDepositPaid(), actualPojo02.isDepositPaid());
        assertEquals(expectedPojo.getBookingDates().getCheckIn(), actualPojo02.getBookingDates().getCheckIn());
        assertEquals(expectedPojo.getBookingDates().getCheckOut(), actualPojo02.getBookingDates().getCheckOut());
    }
}
