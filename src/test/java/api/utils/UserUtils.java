package api.utils;

import io.restassured.response.Response;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class UserUtils {

    public static void saveUser(Response response) {
        Map<String, Object> obj = new HashMap<>();
        obj.put("id", response.jsonPath().getString("id"));
        obj.put("email", response.jsonPath().getString("email"));

        ObjectMapper mapper = new ObjectMapper();
        try {
            mapper.writerWithDefaultPrettyPrinter()
                    .writeValue(new File("src/test/resources/data/createdUser.json"), obj);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
