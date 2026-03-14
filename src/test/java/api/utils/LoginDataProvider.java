package api.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.type.TypeReference;
import org.testng.annotations.DataProvider;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

public class LoginDataProvider {

    @DataProvider(name = "loginData")
    public Object[][] getLoginData() throws IOException {
        // Baca file JSON
        ObjectMapper mapper = new ObjectMapper();
        List<Map<String, Object>> creds = mapper.readValue(
                new File("src/test/resources/data/loginData.json"),
                new TypeReference<List<Map<String, Object>>>() {}
        );

        // Convert List ke Object[][] untuk TestNG
        Object[][] data = new Object[creds.size()][1];
        for (int i = 0; i < creds.size(); i++) {
            data[i][0] = creds.get(i);
        }
        return data;
    }
}
