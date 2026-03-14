package api.users;

import io.restassured.response.Response;
import org.testng.Reporter;
import org.testng.annotations.Test;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import api.utils.TokenManager;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.anyOf;
import static org.hamcrest.Matchers.is;

public class CreateUserTest {

    @Test
    public void testCreateUserSuccess() {
        Map<String, Object> body = new HashMap<>();
        body.put("name", "HeruQA_" + System.currentTimeMillis() + "_" + new Random().nextInt(1000));
        body.put("email", "heru.qa+" + System.currentTimeMillis() + "@test.com");
        body.put("date", java.time.Instant.now().toString());
        body.put("sendEmail", true);

        Response response = sendRequest(body, "SUCCESS");
        response.then().statusCode(200);
    }

    @Test
    public void testCreateUserDuplicate() {
        Map<String, Object> body = new HashMap<>();
        body.put("name", "Heru QA Duplicate " + new Random().nextInt(1000));
        body.put("email", "work.herupenyu@gmail.com"); // email sudah ada
        body.put("date", java.time.Instant.now().toString());
        body.put("sendEmail", false);

        Response response = sendRequest(body, "DUPLICATE");
        // fleksibel: backend bisa balikin 200 (upsert) atau 409 (conflict)
        response.then().statusCode(anyOf(is(200), is(409)));
    }

    private Response sendRequest(Map<String, Object> body, String scenario) {
        System.out.println("=== REQUEST BODY (" + scenario + ") ===");
        System.out.println(body);

        Response response = given()
                .baseUri("https://resonance.dibimbing.id")
                .contentType("application/json")
                .header("Authorization", "Bearer " + TokenManager.getToken())
                .body(body)
                .when()
                .post("/api/rest/createUser");

        System.out.println("=== RESPONSE (" + scenario + ") ===");
        System.out.println(response.asPrettyString());

        Reporter.getCurrentTestResult().setAttribute("statusCode", response.getStatusCode());
        Reporter.getCurrentTestResult().setAttribute("response", response.getBody().asString());

        return response;
    }
}
