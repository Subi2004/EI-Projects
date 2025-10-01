package app;

import auth.AuthManager;
import auth.Role;
import auth.User;
import grid.CompositeGrid;
import grid.Obstacle;
import rover.Direction;
import rover.Position;
import rover.Rover;
import util.InputHelper;
import util.Logger;
import commands.Command;
import commands.MoveCommand;
import commands.LeftCommand;
import commands.RightCommand;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class MenuManager {
    private final AuthManager authManager;
    private CompositeGrid grid;
    private Rover rover;

    public MenuManager(AuthManager authManager) {
        this.authManager = authManager;
    }

    public void start() {
        boolean running = true;
        while (running) {
            Logger.info("\nMain Menu:\n 1) Sign In\n 2) Sign Up\n 3) Exit");
            int choice = InputHelper.readInt("Choose option: ", 1, 3);
            switch (choice) {
                case 1 -> signInFlow();
                case 2 -> signUpFlow();
                case 3 -> {
                    Logger.info("Exiting...");
                    running = false;
                }
            }
        }
    }

    private void signUpFlow() {
        Logger.info("\nSign Up");
        Logger.info("Choose role: 1) Admin  2) Player");
        int roleChoice = InputHelper.readInt("Role: ", 1, 2);
        Role role = roleChoice == 1 ? Role.ADMIN : Role.PLAYER;
        String username = InputHelper.readString("Username: ");
        String password = InputHelper.readString("Password: ");
        boolean created = authManager.register(username, password, role);
        Logger.info(created ? "User created successfully." : "Username already exists.");
    }

    private void signInFlow() {
        Logger.info("\nSign In");
        String username = InputHelper.readString("Username: ");
        String password = InputHelper.readString("Password: ");
        Optional<User> user = authManager.authenticate(username, password);
        user.ifPresentOrElse(u -> {
            Logger.info("Welcome " + u.getUsername() + " (" + u.getRole() + ")");
            switch (u.getRole()) {
                case ADMIN -> adminMenu();
                case PLAYER -> playerMenu();
            }
        }, () -> Logger.warn("Authentication failed."));
    }

    private void adminMenu() {
        boolean back = false;
        while (!back) {
            Logger.info("\nAdmin Menu:\n 1) Create/Resize Grid\n 2) Add Obstacle\n 3) Remove Obstacle\n" +
                    "4) Set Rover Start Position\n5) View Grid\n6) Back");
            int choice = InputHelper.readInt("Choice: ", 1, 6);
            switch (choice) {
                case 1 -> createOrResizeGrid();
                case 2 -> addObstacleCLI();
                case 3 -> removeObstacleCLI();
                case 4 -> setRoverStartCLI();
                case 5 -> viewGridCLI();
                case 6 -> back = true;
            }
        }
    }

    private void createOrResizeGrid() {
        int w = InputHelper.readInt("Grid width: ", 1, 100);
        int h = InputHelper.readInt("Grid height: ", 1, 100);
        CompositeGrid newGrid = new CompositeGrid(w, h);
        if (grid != null) {
            grid.getComponents().stream()
                    .filter(c -> newGrid.inBounds(c.getX(), c.getY()))
                    .forEach(newGrid::addComponent);
        }
        grid = newGrid;
        Logger.info("Grid created/resized: " + w + " x " + h);
    }

    private void addObstacleCLI() {
        if (grid == null) {
            Logger.warn("Create a grid first.");
            return;
        }
        int x = InputHelper.readInt("Obstacle X: ", 0, grid.getWidth() - 1);
        int y = InputHelper.readInt("Obstacle Y: ", 0, grid.getHeight() - 1);
        if (!grid.hasObstacle(x, y)) {
            grid.addComponent(new Obstacle(x, y));
            Logger.info("Obstacle added at (" + x + "," + y + ")");
        } else Logger.warn("Obstacle already exists here.");
        viewGridCLI();
    }

    private void removeObstacleCLI() {
        if (grid == null) {
            Logger.warn("Create a grid first.");
            return;
        }
        int x = InputHelper.readInt("Remove Obstacle X: ", 0, grid.getWidth() - 1);
        int y = InputHelper.readInt("Remove Obstacle Y: ", 0, grid.getHeight() - 1);
        boolean removed = grid.removeObstacleAt(x, y);
        Logger.info(removed ? "Obstacle removed." : "No obstacle at that position.");
        viewGridCLI();
    }

    private void setRoverStartCLI() {
        if (grid == null) {
            Logger.warn("Create a grid first.");
            return;
        }
        int x = InputHelper.readInt("Start X: ", 0, grid.getWidth() - 1);
        int y = InputHelper.readInt("Start Y: ", 0, grid.getHeight() - 1);
        String dStr = InputHelper.readString("Start Direction (N/E/S/W): ").toUpperCase();
        Direction dir = switch (dStr) {
            case "N" -> Direction.N;
            case "E" -> Direction.E;
            case "S" -> Direction.S;
            case "W" -> Direction.W;
            default -> Direction.N;
        };
        if (!grid.hasObstacle(x, y)) {
            rover = new Rover(new Position(x, y, dir), grid);
            Logger.info("Rover placed at (" + x + "," + y + ") facing " + dir);
        } else Logger.warn("Cannot place rover on obstacle.");
        viewGridCLI();
    }

    private void viewGridCLI() {
        if (grid != null) grid.printGrid(rover != null ? rover.getPosition() : null);
    }

    private void playerMenu() {
        if (grid == null || rover == null) {
            Logger.warn("Grid or rover not ready. Ask admin to configure.");
            return;
        }
        Map<String, Command> keyMap = new HashMap<>();
        keyMap.put("w", new MoveCommand(rover));
        keyMap.put("a", new LeftCommand(rover));
        keyMap.put("d", new RightCommand(rover));

        boolean quit = false;
        Logger.info("Controls: w=forward, a=left, d=right, p=print, s=status, q=quit");
        while (!quit) {
            String input = InputHelper.readString("Command: ").toLowerCase();
            switch (input) {
                case "p" -> viewGridCLI();
                case "s" -> Logger.info("Rover status: " + rover.getPosition());
                case "q" -> quit = true;
                default -> Optional.ofNullable(keyMap.get(input))
                        .ifPresentOrElse(Command::execute, () -> Logger.warn("Unknown command."));
            }
        }
    }
}
