package api.users;

import io.restassured.response.Response;
import org.testng.Reporter;
import org.testng.SkipException;
import org.testng.annotations.Test;
import java.util.HashMap;
import java.util.Map;
import api.utils.TokenManager;
import io.qameta.allure.Allure;

import static io.restassured.RestAssured.given;

public class VerifyTokenTest {

    @Test
    public void testVerifyToken() {
        // cek apakah token verifikasi tersedia
        String verificationToken = TokenManager.getVerificationToken();

        if (verificationToken == null) {
            String skipMessage = "SKIPPED: Token verifikasi tidak tersedia (email tidak diakses).";
            System.out.println(skipMessage);
            Reporter.log(skipMessage);
            Allure.addAttachment("Skip Evidence", skipMessage);
            throw new SkipException(skipMessage);
        }

        // Data request pakai token verifikasi asli
        Map<String, Object> body = new HashMap<>();
        body.put("token", verificationToken);
        body.put("password", "newPassword123");
        body.put("passwordConfirmation", "newPassword123");
        body.put("date", java.time.Instant.now().toString());

        System.out.println("=== REQUEST BODY ===");
        System.out.println(body);

        Response response = given()
                .baseUri("https://resonance.dibimbing.id")
                .contentType("application/json")
                .header("Authorization", "Bearer " + TokenManager.getToken())
                .body(body)
                .when()
                .post("/api/rest/verifyToken");

        System.out.println("=== RESPONSE ===");
        System.out.println(response.asPrettyString());

        // simpan evidence ke TestNG Reporter
        Reporter.getCurrentTestResult().setAttribute("statusCode", response.getStatusCode());
        Reporter.getCurrentTestResult().setAttribute("response", response.getBody().asString());

        // attach response ke Allure
        Allure.addAttachment("VerifyToken Response", response.asPrettyString());

        response.then().statusCode(200);
    }
}
