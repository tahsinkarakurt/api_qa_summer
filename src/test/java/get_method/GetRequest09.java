package get_method;

import base_urls.HerokuappBaseUrl;
import io.restassured.response.Response;
import static org.junit.Assert.*;
import org.junit.Test;
import static io.restassured.RestAssured.*;
import java.util.HashMap;
import java.util.Map;

public class GetRequest09 extends HerokuappBaseUrl {

    /*
        When
	 		I send GET Request to https://restful-booker.herokuapp.com/booking/1
	 	Then
	 		Response body should be like that;
	 		{
			    "firstname": "Eric",
			    "lastname": "Smith",
			    "totalprice": 555,
			    "depositpaid": false,
			    "bookingdates": {
			        "checkin": "2016-09-09",
			        "checkout": "2017-09-21"
			     }
			}
     */
    @Test
    public void get01(){
        //1)Set the URL
        spec.pathParams("first", "booking",
                        "second", 1);

        //2)Set the expected data
        Map<String, String> bookingdates = new HashMap<>();
        bookingdates.put("checkin", "2019-12-17");
        bookingdates.put("checkout", "2020-03-01");

        Map<String, Object> expectedDataMap = new HashMap<>();
        expectedDataMap.put("firstname", "Sally");
        expectedDataMap.put("lastname", "Smith");
        expectedDataMap.put("totalprice", 688);
        expectedDataMap.put("depositpaid", true);
        expectedDataMap.put("bookingdates", bookingdates);

        System.out.println(expectedDataMap);

        //3)Send the request
        Response response = given().spec(spec).when().get("/{first}/{second}");
        response.prettyPrint();

        //Use GSON for De-Serialization
        Map<String, Object> actualDataMap = response.as(HashMap.class);
        System.out.println(actualDataMap);

        //4)Assert the output
        assertEquals(expectedDataMap.get("firstname"), actualDataMap.get("firstname"));
        assertEquals(expectedDataMap.get("lastname"), actualDataMap.get("lastname"));
        assertEquals(expectedDataMap.get("totalprice"), actualDataMap.get("totalprice"));
        assertEquals(expectedDataMap.get("depositpaid"), actualDataMap.get("depositpaid"));
        assertEquals(((Map)expectedDataMap.get("bookingdates")).get("checkin"), ((Map)actualDataMap.get("bookingdates")).get("checkin"));
        assertEquals(((Map)expectedDataMap.get("bookingdates")).get("checkout"), ((Map)actualDataMap.get("bookingdates")).get("checkout"));

    }

}
