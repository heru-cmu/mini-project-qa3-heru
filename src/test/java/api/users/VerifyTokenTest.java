package api.users;

import io.restassured.response.Response;
import org.testng.Reporter;
import org.testng.SkipException;
import org.testng.annotations.Test;
import java.util.HashMap;
import java.util.Map;
import api.utils.TokenManager;

import static io.restassured.RestAssured.given;

public class VerifyTokenTest {

    @Test
    public void testVerifyToken() {
        // cek apakah token verifikasi tersedia
        String verificationToken = TokenManager.getVerificationToken();

        if (verificationToken == null) {
            System.out.println("SKIPPED: Tidak ada akses ke token verifikasi (token dikirim via email).");
            Reporter.log("SKIPPED: Test VerifyToken dilewati karena token verifikasi tidak tersedia (email tidak diakses).");
            throw new SkipException("Skip VerifyTokenTest: token verifikasi tidak tersedia.");
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

        response.then().statusCode(200);
    }
}
