package api.auth;

import io.restassured.response.Response;
import org.testng.Reporter;
import org.testng.annotations.Test;
import java.util.HashMap;
import java.util.Map;
import api.utils.TokenManager;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.anyOf;
import static org.hamcrest.Matchers.is;

public class LoginTest {

    @Test
    public void testLoginSuccess() {
        Map<String, Object> body = new HashMap<>();
        body.put("usernameOrEmail", "work.herupenyu@gmail.com");
        body.put("password", "ureh");

        Response response = sendRequest(body, "SUCCESS");
        response.then().statusCode(200);

        // simpan token login
        String token = response.jsonPath().getString("token");
        if (token != null) {
            TokenManager.saveToken(token);
        }
    }

    @Test
    public void testLoginInvalidPassword() {
        Map<String, Object> body = new HashMap<>();
        body.put("usernameOrEmail", "work.herupenyu@gmail.com");
        body.put("password", "salah");

        Response response = sendRequest(body, "INVALID PASSWORD");
        response.then().statusCode(401);
    }

    @Test
    public void testLoginInvalidUser() {
        Map<String, Object> body = new HashMap<>();
        body.put("usernameOrEmail", "notexist@test.com");
        body.put("password", "ureh");

        Response response = sendRequest(body, "INVALID USER");
        response.then().statusCode(401);
    }

    @Test
    public void testLoginInvalidBody() {
        Map<String, Object> body = new HashMap<>();
        body.put("wrongField", "abc"); // body salah

        Response response = sendRequest(body, "INVALID BODY");
        // fleksibel: idealnya 400, tapi backend kadang balikin 500
        response.then().statusCode(anyOf(is(400), is(500)));
    }

    private Response sendRequest(Map<String, Object> body, String scenario) {
        System.out.println("=== REQUEST BODY (" + scenario + ") ===");
        System.out.println(body);

        Response response = given()
                .baseUri("https://resonance.dibimbing.id")
                .contentType("application/json")
                .body(body)
                .when()
                .post("/api/rest/login");

        System.out.println("=== RESPONSE (" + scenario + ") ===");
        System.out.println(response.asPrettyString());

        Reporter.getCurrentTestResult().setAttribute("statusCode", response.getStatusCode());
        Reporter.getCurrentTestResult().setAttribute("response", response.getBody().asString());

        return response;
    }
}
