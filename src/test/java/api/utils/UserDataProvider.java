package api.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.type.TypeReference;
import org.testng.annotations.DataProvider;

import java.io.File;
import java.io.IOException;
import java.time.Instant;
import java.util.List;
import java.util.Map;

public class UserDataProvider {

    @DataProvider(name = "userData")
    public Object[][] getUserData() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        List<Map<String, Object>> users = mapper.readValue(
                new File("src/test/resources/data/users.json"),
                new TypeReference<List<Map<String, Object>>>() {}
        );

        // Generate email unik sekali per run
        String uniqueEmail = "heru.qa+" + System.currentTimeMillis() + "@test.com";
        String now = Instant.now().toString();

        // Replace placeholder di JSON
        for (Map<String, Object> user : users) {
            if ("DYNAMIC_EMAIL".equals(user.get("email"))) {
                user.put("email", uniqueEmail);
            }
            user.put("date", now);
        }

        Object[][] data = new Object[users.size()][1];
        for (int i = 0; i < users.size(); i++) {
            data[i][0] = users.get(i);
        }
        return data;
    }
}
