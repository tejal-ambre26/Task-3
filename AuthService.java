package loginapp;

import java.util.HashMap;
import java.util.Map;

public class AuthService {
    // In-memory user store (username -> passwordHash)
    private static final Map<String, String> USERS = new HashMap<>();

    static {
        // Preload some demo accounts
        addUser("admin", "admin123");
        addUser("tejal", "tejal@123");
    }

    public static void addUser(String username, String plainPassword) {
        USERS.put(username.toLowerCase(), HashUtil.sha256(plainPassword));
    }

    public static boolean authenticate(String username, String plainPassword) {
        if (username == null || plainPassword == null) return false;
        String stored = USERS.get(username.toLowerCase());
        if (stored == null) return false;
        String hash = HashUtil.sha256(plainPassword);
        return stored.equals(hash);
    }
}
