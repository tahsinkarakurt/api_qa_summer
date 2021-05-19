package test_data;

import java.util.HashMap;
import java.util.Map;

public class JsonPlaceHolderTestData {

    public Map<String, Object> expectedDataSetUp(){
        Map<String, Object> expected = new HashMap<>();
        expected.put("userId", 10);
        expected.put("title", "Tidy your room");
        expected.put("completed", true);
        return expected;
    }

    public Map<String, Object> expectedPatchDataSetUp(){
        Map<String, Object> expected = new HashMap<>();
        expected.put("title", "Tidy your room");
        return expected;
    }
}
