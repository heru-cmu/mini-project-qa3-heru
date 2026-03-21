package api.users;

import io.restassured.response.Response;
import org.testng.Reporter;
import org.testng.annotations.Test;
import java.util.Map;
import api.utils.UserDataProvider;

import static io.restassured.RestAssured.given;

public class CreateUserTest {

    @Test(dataProvider = "userData", dataProviderClass = UserDataProvider.class)
    public void testCreateUser(Map<String, Object> user) {
        System.out.println("=== REQUEST BODY ===");
        System.out.println(user);

        Response response = given()
                .baseUri("https://resonance.dibimbing.id")
                .contentType("application/json")
                .header("Authorization", "Bearer " + api.utils.TokenManager.getToken())
                .body(user)
                .when()
                .post("/api/rest/createUser");

        System.out.println("=== RESPONSE ===");
        System.out.println(response.asPrettyString());

        // simpan evidence ke TestNG Reporter
        Reporter.getCurrentTestResult().setAttribute("statusCode", response.getStatusCode());
        Reporter.getCurrentTestResult().setAttribute("response", response.getBody().asString());

        // cek status sesuai expected dari JSON
        int expectedStatus = (int) user.get("expectedStatus");
        response.then().statusCode(expectedStatus);

        // kalau sukses, simpan user ke file evidence
        if (expectedStatus == 200) {
            api.utils.UserUtils.saveUser(response);
        }
    }
}
