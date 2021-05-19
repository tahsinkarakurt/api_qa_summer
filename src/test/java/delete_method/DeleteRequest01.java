package delete_method;

import base_urls.JsonPlaceHolderBaseUrl;
import io.restassured.response.Response;
import org.hamcrest.Matcher;
import org.hamcrest.Matchers;
import org.junit.Test;
import static org.junit.Assert.*;
import java.util.HashMap;
import static io.restassured.RestAssured.*;
import java.util.Map;

public class DeleteRequest01 extends JsonPlaceHolderBaseUrl {

    /*
        When
	 		I send DELETE Request to the Url https://jsonplaceholder.typicode.com/todos/198
	 	Then
		 	Status code is 200
		 	And Response body is {}
    */
    @Test
    public void delete01(){
       //1)Set the url
       spec.pathParams("first", "todos",
               "second", 198);

       //2)Set the expected data
       Map<String, Object> expected = new HashMap<>();

       //3)Send the DELETE Request
       Response response = given().spec(spec).when().delete("/{first}/{second}");
       response.prettyPrint();

       //GSON: De-serialization
       Map<String, Object> actual = response.as(HashMap.class);

       //4)Assert the output
       //1.Way: body()
        response.then().assertThat().statusCode(200);
        assertEquals(expected, actual);
        assertEquals(expected.size(), actual.size());
    }
}
