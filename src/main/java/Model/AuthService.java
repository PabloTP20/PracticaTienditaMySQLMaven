package Model;

import java.util.*;

public class AuthService {

    private final Map<String, User> users = new HashMap<>();

    public AuthService() {
        // Usuarios de ejemplo
        users.put("admin", new User("admin", "admin123", "ADMIN"));
        users.put("user",  new User("user",  "user123",  "USER"));
        users.put("pablo", new User("pablo", "pablo123", "PABLO"));
    }

    public Optional<User> login(String username, String password) {
        User u = users.get(username);
        if (u != null && u.matches(username, password)) {
            return Optional.of(u);
        }
        return Optional.empty();
    }
}
