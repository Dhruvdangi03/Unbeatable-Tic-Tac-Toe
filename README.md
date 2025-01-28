# Unbeatable Tic-Tac-Toe Game

This repository contains implementations of an unbeatable Tic-Tac-Toe game in both Java and C++. The game features a simple command-line interface and uses the Minimax algorithm to ensure that the bot never loses.

## Features
- Play against an AI bot that never loses.
- Human player can choose their symbol: `X` or `O`.
- Simple text-based board display.
- Minimax algorithm for optimal bot moves.
- Supports detecting wins, draws, and invalid moves.

## Getting Started

### Prerequisites
- For the Java version, you need:
  - Java Development Kit (JDK) 8 or higher.
- For the C++ version, you need:
  - Any C++ compiler (GCC, Clang, or MSVC).

### Running the Java Version
1. Navigate to the Java project directory.
2. Compile the program with the following command:
   ```bash
   javac UnbeatableTicTacToe.java
   ```
3. Run the game:
   ```bash
   java UnbeatableTicTacToe
   ```

### Running the C++ Version
1. Navigate to the C++ project directory.
2. Compile the program with the following command:
   ```bash
   g++ -o UnbeatableTicTacToe UnbeatableTicTacToe.cpp
   ```
3. Run the game:
   ```bash
   ./UnbeatableTicTacToe
   ```

## How to Play
1. Choose your symbol (`X` or `O`).
2. Enter a position between `1` and `9` when prompted.
   - The board positions are mapped as follows:
     ```
     1 | 2 | 3
     ---------
     4 | 5 | 6
     ---------
     7 | 8 | 9
     ```
3. The bot will make its move.
4. The game ends when there is a winner or a draw.

## Example Gameplay (Java)
```
*************************************
  Welcome to Tic-Tac-Toe!
*************************************
You will play against the AI bot. Good luck!
Here’s how the board looks:
Positions: 1 | 2 | 3
           4 | 5 | 6
           7 | 8 | 9
Choose your symbol (X or O)
Enter X for X or O for O: X
You are X. Bot is O.
Your Turn! Choose a position (1-9):
```

## Game Rules
- Players take turns to place their symbols (`X` or `O`) on the board.
- The first player to get three of their symbols in a row, column, or diagonal wins.
- If the board is full and no one wins, the game ends in a draw.

## Algorithms Used
- **Minimax Algorithm:** Ensures that the bot always makes the optimal move, either winning or forcing a draw.

## File Structure
```
.
├── Java
│   └── UnbeatableTicTacToe.java
└── C++
    └── UnbeatableTicTacToe.cpp
```

## Contributing
Contributions are welcome! Please open an issue or submit a pull request for any changes or improvements.
