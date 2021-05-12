package get_method;

import static io.restassured.RestAssured.*;
import base_urls.JsonPlaceHolderBaseUrl;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import static org.junit.Assert.*;
import org.junit.Test;

import java.util.Collections;
import java.util.List;

public class GetRequest07 extends JsonPlaceHolderBaseUrl {
    /*
        When
			 I send GET Request to URL https://jsonplaceholder.typicode.com/todos
		 Then
			 Status code is 200
			 1)Print all ids greater than 190 on the console
			   Assert that there are 10 ids greater than 190
			 2)Print all userIds less than 5 on the console
			   Assert that maximum userId less than 5 is 4
			 3)Print all titles whose ids are less than 5
			   Assert that "delectus aut autem" is one of the titles whose id is less than 5
     */
    @Test
    public void get01(){
        //1)Set the url
        spec.pathParam("first", "todos");

        //2)Set expected data

        //3)Send the request
        Response response = given().spec(spec).when().get("/{first}");
        response.prettyPrint();

        JsonPath json = response.jsonPath();

        //1)Print all ids greater than 190 on the console
        // findAll{it.id>190} ==> Tells to Groovy to look at the json data under the condition id>190
        // .id ==> Tells to Groovy to select what
        List<Integer> idList = json.getList("findAll{it.id>190}.id");
        System.out.println(idList);
        //Assert that there are 10 ids greater than 190
        assertEquals(10, idList.size());

        //2)Print all userIds less than 5 on the console
        List<Integer> userIdList = json.getList("findAll{it.userId<5}.userId");
        System.out.println(userIdList);
        //Assert that maximum userId less than 5 is 4
        Collections.sort(userIdList);
        assertEquals((Integer)4, userIdList.get(userIdList.size()-1));

        //3)Print all titles whose ids are less than 5
        List<String> titleList = json.getList("findAll{it.id<5}.title");
        System.out.println(titleList);
        //Assert that "delectus aut autem" is one of the titles whose id is less than 5
        boolean result = titleList.stream().anyMatch(t->t.equals("delectus aut autem"));
        assertTrue(result);

    }
}
