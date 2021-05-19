package object_mapper;

import base_urls.HerokuappBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;
import org.testng.Assert;
import utils.JsonUtil;
import static io.restassured.RestAssured.*;
import java.util.HashMap;
import java.util.Map;

public class ObjectMapper02 extends HerokuappBaseUrl {

    /*
        When
		 		I send GET Request to the URL https://restful-booker.herokuapp.com/booking/2

		 	Then
		 		Status code is 200
		 		And response body is like {
										    "firstname": "Mark",
										    "lastname": "Ericsson",
										    "totalprice": 726,
										    "depositpaid": true,
										    "bookingdates": {
										        "checkin": "2015-08-07",
										        "checkout": "2020-10-25"
										     }
										  }
     */

    @Test
    public void get01(){
        //1) Set the URL
        spec.pathParams("first", "booking",
                "second", 2);

        //2)Set the expected data
        String expected = "{\n" +
                            "\"firstname\": \"Mark\",\n" +
                            "\"lastname\": \"Jones\",\n" +
                            "\"totalprice\": 514,\n" +
                            "\"depositpaid\": false,\n" +
                            "\"bookingdates\": {\n" +
                                                "\"checkin\": \"2015-02-11\",\n" +
                                                "\"checkout\": \"2021-01-07\"\n" +
                                               "}\n" +
                            "}";
        HashMap<String, Object> expectedMap = JsonUtil.convertJsonToJava(expected, HashMap.class);

        //3)Send the request
        Response response = given().spec(spec).when().get("/{first}/{second}");
        response.prettyPrint();

        HashMap<String, Object> actualMap = JsonUtil.convertJsonToJava(response.asString(), HashMap.class);

        //4)Assert the output
        //Assert.assertEquals(actualMap.get("firstname"), expectedMap.get("firstname"));
        //Assert.assertEquals(actualMap.get("lastname"), expectedMap.get("lastname"));
        //Assert.assertEquals(actualMap.get("totalprice"), expectedMap.get("totalprice"));
        //Assert.assertEquals(actualMap.get("depositpaid"), expectedMap.get("depositpaid"));
        //Assert.assertEquals(((Map)actualMap.get("bookingdates")).get("checkin"), ((Map)expectedMap.get("bookingdates")).get("checkin"));
        //Assert.assertEquals(((Map)actualMap.get("bookingdates")).get("checkout"), ((Map)expectedMap.get("bookingdates")).get("checkout"));

        //2.Way: To assert checkin and checkout
        Assert.assertEquals(actualMap.get("bookingdates.checkin"), expectedMap.get("bookingdates.checkin"));
        Assert.assertEquals(actualMap.get("bookingdates.checkout"), expectedMap.get("bookingdates.checkout"));

    }
}
