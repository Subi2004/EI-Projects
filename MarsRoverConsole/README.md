# 🚀 Mars Rover Console Game

A console-based Mars Rover simulation implemented in Java, featuring Admin and Player modes, obstacle detection, and flexible rover navigation. The project demonstrates object-oriented design, behavioral and structural patterns, and a modular architecture.

## 📂 Project Structure

```
mars-rover-console/
├─ src/
│  ├─ app/
│  │  ├─ MarsRoverApplication.java   // Main entry point
│  │  ├─ MenuManager.java            // Menus & input loop
│  │
│  ├─ auth/
│  │  ├─ User.java                   // User entity
│  │  ├─ AuthManager.java            // Authentication logic
│  │  ├─ Role.java                   // Enum: ADMIN / PLAYER
│  │
│  ├─ grid/
│  │  ├─ CompositeGrid.java          // Composite grid + obstacles
│  │  ├─ GridComponent.java          // Base component
│  │  ├─ Obstacle.java               // Obstacle entity
│  │
│  ├─ rover/
│  │  ├─ Rover.java                  // Rover movement logic
│  │  ├─ Position.java               // Position + direction
│  │  ├─ Direction.java              // Enum: N/E/S/W
│  │
│  ├─ commands/
│  │  ├─ Command.java                // Command interface
│  │  ├─ MoveCommand.java            // Move forward action
│  │  ├─ LeftCommand.java            // Turn left action
│  │  ├─ RightCommand.java           // Turn right action
│  │
│  ├─ util/
│  │  ├─ Logger.java                 // Custom logging
│  │  ├─ InputHelper.java            // Input & validation
│
└─ README.md
```

## 🎯 Design Patterns Used

* **Command Pattern (Behavioral):** Encapsulates rover actions (`MoveCommand`, `LeftCommand`, `RightCommand`) for flexible execution and batch or interactive input.
* **Composite Pattern (Structural):** Represents the grid and obstacles so that components (empty cells, obstacles, rover) can be handled uniformly.
* **OOP Principles:** Encapsulation, inheritance, and polymorphism are applied across modules.

## 🛠️ Tech Stack

* **Language:** Java (14+ recommended)
* **Environment:** Console-based application
* **Libraries:** Pure Java (no external dependencies)
* **Build & Run:** Compile with `javac` and run with `java`

## 👥 Roles

**Admin**

* Create / resize grid
* Add or remove obstacles
* Set rover starting position
* View updated grid

**Player**

* Batch Mode: Enter sequences like `MMRMLM` to move the rover
* Interactive Mode: Use single-letter commands to control rover step-by-step

## 🕹️ Interactive Mode Commands

| Key | Action            |
| --- | ----------------- |
| w   | Move forward      |
| a   | Turn left         |
| d   | Turn right        |
| p   | Print the grid    |
| s   | Show rover status |
| q   | Quit to menu      |

## 📄 Example Gameplay

**Admin Setup**

```
> Create Grid: 10 x 10
> Add Obstacles at: (2,2), (3,5)
> Set Rover Start: (0,0,N)
```

**Player (Batch Mode)**

```
Input: M M R M L M
Output: Rover at (1,3), facing East
Grid:

[R] . . . . . . . . .
.  . . . . . . . . .
.  . [X] . . . . . .
.  . . . . [X] . . .
.  . . . . . . . . .
.  . . . . . . . . .
.  . . . . . . . . .
.  . . . . . . . . .
.  . . . . . . . . .
.  . . . . . . . . .

Legend:
[R] → Rover
[X] → Obstacle
. → Empty cell
```

## ✅ Features

* Role-based authentication (Admin / Player)
* Configurable grid & obstacles
* Step-by-step rover navigation with collision detection
* Command pattern for extensible rover actions
* Console-based design – scalable & easy to extend

## 🔧 How to Run

```bash
# Compile all classes
javac -d bin src/app/*.java src/auth/*.java src/grid/*.java src/rover/*.java src/commands/*.java src/util/*.java

# Run the application
java -cp bin app.MarsRoverApplication
```

## ⚡ Notes

* The rover `[R]` updates in real-time as it moves.
* Obstacles `[X]` remain fixed throughout gameplay.
* Press `p` anytime in interactive mode to print the grid.
* Designed for scalability: you can increase grid size, add obstacles, or extend rover commands easily.
