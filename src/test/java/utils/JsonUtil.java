package utils;

import org.codehaus.jackson.map.ObjectMapper;

import java.io.IOException;
import java.util.HashMap;

public class JsonUtil {

                //Create a method to convert Json Data to Java Object (De-serialization)

    //1.Step: Create an Object Mapper Class object
    private static ObjectMapper mapper;

    static{
        mapper = new ObjectMapper();
    }

    //2.Step: Create De-serialization Method
    public static <T> T convertJsonToJava(String json, Class<T> cls) {

        T  javaResult = null;

        try {
            javaResult = mapper.readValue(json, cls);
        } catch (IOException e) {
            System.out.println("Json could not be converted to Java Object " + e.getMessage());
        }

        return javaResult;
    }
                        //Create a method to convert Java Object to Json Data (Serialization)
    public static String convertJavaToJson(Object obj){

        String jsonResult = null;

        try {
            jsonResult = mapper.writeValueAsString(obj);
        } catch (IOException e) {
            System.out.println("Java Object could not be converted to Json Data " + e.getMessage());
        }

        return jsonResult;
    }
}
