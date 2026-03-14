package api.utils;

public class TokenManager {
    private static String token;
    private static String verificationToken;

    public static void saveToken(String t) {
        token = t;
        System.out.println("Token saved: " + token);
    }

    public static String getToken() {
        return token;
    }

    public static void saveVerificationToken(String t) {
        verificationToken = t;
        System.out.println("Verification Token saved: " + verificationToken);
    }

    public static String getVerificationToken() {
        return verificationToken;
    }
}
