package app;

import auth.AuthManager;
import auth.Role;
import auth.User;
import util.Logger;

import java.util.Optional;

public class MarsRoverApplication {
    public static void main(String[] args) {
        Logger.info("=== Mars Rover Console Game ===");
        AuthManager authManager = new AuthManager();
        authManager.seedDefaultAdmin();
        MenuManager menuManager = new MenuManager(authManager);
        menuManager.start();
    }
}
