package api.auth;

import io.restassured.response.Response;
import org.testng.Reporter;
import org.testng.annotations.Test;
import java.util.Map;
import api.utils.LoginDataProvider;

import static io.restassured.RestAssured.given;

public class LoginTest {

    @Test(dataProvider = "loginData", dataProviderClass = LoginDataProvider.class)
    public void testLogin(Map<String, Object> creds) {
        System.out.println("=== REQUEST BODY ===");
        System.out.println(creds);

        Response response = given()
                .baseUri("https://resonance.dibimbing.id")
                .contentType("application/json")
                .body(creds)
                .when()
                .post("/api/rest/login");

        System.out.println("=== RESPONSE ===");
        System.out.println(response.asPrettyString());

        // simpan evidence ke TestNG Reporter
        Reporter.getCurrentTestResult().setAttribute("statusCode", response.getStatusCode());
        Reporter.getCurrentTestResult().setAttribute("response", response.getBody().asString());

        // cek status sesuai expected dari JSON
        int expectedStatus = (int) creds.get("expectedStatus");
        response.then().statusCode(expectedStatus);
    }
}
