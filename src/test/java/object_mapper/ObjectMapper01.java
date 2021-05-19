package object_mapper;

import base_urls.JsonPlaceHolderBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;
import org.testng.Assert;
import utils.JsonUtil;
import static io.restassured.RestAssured.*;
import java.util.HashMap;

public class ObjectMapper01 extends JsonPlaceHolderBaseUrl {

    /*
	 	When
	 		I send GET Request to the URL https://jsonplaceholder.typicode.com/todos/198

	 	Then
	 		Status code is 200
	 		And response body is like {
									    "userId": 10,
									    "id": 198,
									    "title": "quis eius est sint explicabo",
									    "completed": true
									  }

    */

    @Test
    public void get01(){
        //1)Set the url
        spec.pathParams("first", "todos",
                "second", 198);

        //2)Set the expected data
        String expected = "{\n" +
                            "\"userId\": 10,\n" +
                            "\"id\": 198,\n" +
                            "\"title\": \"quis eius est sint explicabo\",\n" +
                            "\"completed\": true\n" +
                            "}";
        HashMap<String, Object> expectedMap = JsonUtil.convertJsonToJava(expected, HashMap.class);

        //3)Send the request
        Response response = given().spec(spec).when().get("/{first}/{second}");
        response.prettyPrint();

        HashMap<String, Object> actualMap = JsonUtil.convertJsonToJava(response.asString(), HashMap.class);

        //4)Assert the output
        Assert.assertEquals(expectedMap.get("userId"), actualMap.get("userId"));
        Assert.assertEquals(expectedMap.get("title"), actualMap.get("title"));
        Assert.assertEquals(expectedMap.get("completed"), actualMap.get("completed"));
    }
}
