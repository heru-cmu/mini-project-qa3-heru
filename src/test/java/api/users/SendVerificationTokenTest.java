package api.users;

import io.restassured.response.Response;
import org.testng.Reporter;
import org.testng.annotations.Test;
import java.util.HashMap;
import java.util.Map;
import api.utils.TokenManager;

import static io.restassured.RestAssured.given;

public class SendVerificationTokenTest {

    @Test
    public void testSendVerificationTokenSuccess() {
        Map<String, Object> body = new HashMap<>();
        body.put("email", "work.herupenyu@gmail.com"); // email valid yang sudah terdaftar
        body.put("date", java.time.Instant.now().toString());

        Response response = sendRequest(body, "SUCCESS");
        response.then().statusCode(200);
    }

    @Test
    public void testSendVerificationTokenFail() {
        Map<String, Object> body = new HashMap<>();
        body.put("email", "heru.qa+" + System.currentTimeMillis() + "@test.com"); // email random belum ada
        body.put("date", java.time.Instant.now().toString());

        Response response = sendRequest(body, "FAIL");
        // idealnya 404 atau 400, kalau masih 500 berarti bug backend
        response.then().statusCode(404);
    }

    // helper method untuk kirim request dan log
    private Response sendRequest(Map<String, Object> body, String scenario) {
        System.out.println("=== REQUEST BODY (" + scenario + ") ===");
        System.out.println(body);

        Response response = given()
                .baseUri("https://resonance.dibimbing.id")
                .contentType("application/json")
                .header("Authorization", "Bearer " + TokenManager.getToken())
                .body(body)
                .when()
                .post("/api/rest/sendVerificationToken");

        System.out.println("=== RESPONSE (" + scenario + ") ===");
        System.out.println(response.asPrettyString());

        Reporter.getCurrentTestResult().setAttribute("statusCode", response.getStatusCode());
        Reporter.getCurrentTestResult().setAttribute("response", response.getBody().asString());

        return response;
    }
}
