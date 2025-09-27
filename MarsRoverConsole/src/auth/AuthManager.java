package auth;

import util.Logger;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class AuthManager {
    private final Map<String, User> users = new HashMap<>();

    public void seedDefaultAdmin() {
        if (!users.containsKey("admin")) {
            users.put("admin", new User("admin", "admin123", Role.ADMIN));
            Logger.info("Default admin created: username=admin, password=admin123");
        }
    }

    public boolean register(String username, String password, Role role) {
        if (users.containsKey(username)) return false;
        users.put(username, new User(username, password, role));
        return true;
    }

    public Optional<User> authenticate(String username, String password) {
        if (!users.containsKey(username)) return Optional.empty();
        User user = users.get(username);
        if (user.getPassword().equals(password)) return Optional.of(user);
        return Optional.empty();
    }
}
