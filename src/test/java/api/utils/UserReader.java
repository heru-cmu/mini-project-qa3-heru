package api.utils;

import com.fasterxml.jackson.databind.ObjectMapper;  // untuk ObjectMapper
import java.io.File;                                // untuk File
import java.util.Map;


public class UserReader {
    public static Map<String, Object> getUser() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(new File("src/test/resources/data/createdUser.json"), Map.class);
    }
}
