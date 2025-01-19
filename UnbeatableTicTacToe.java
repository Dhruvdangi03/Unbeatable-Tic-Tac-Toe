import java.util.Scanner;

public class UnbeatableTicTacToe {
    static int[][] board = new int[3][3];
    static Scanner scanner = new Scanner(System.in);
    static int HUMAN; // Human player symbol (1 for X, 2 for O)
    static int BOT;   // Bot player symbol (2 for O, 1 for X)

    public static void main(String[] args) {
        displayWelcomeMessage();
        chooseSymbol();

        int currentPlayer = HUMAN;

        while (true) {
            if (currentPlayer == HUMAN) {
                System.out.println("Your Turn! Choose a position (1-9): ");
            }

            int[] position = (currentPlayer == HUMAN) ? takeInput() : calculateBotMove();
            updateBoard(position, currentPlayer);
            displayBoard();
            if (checkWin(currentPlayer)) {
                displayBoard();
                if (currentPlayer == HUMAN) {
                    System.out.println("Congratulations! You won!");
                } else {
                    System.out.println("You can't Beat the Unbeatable BOT!");
                }
                break;
            }

            if (isGameDraw()) {
                displayBoard();
                System.out.println("It's a draw! What a tight match!");
                break;
            }

            currentPlayer = (currentPlayer == HUMAN) ? BOT : HUMAN;
        }
        displayGoodbyeMessage();
    }

    private static void displayWelcomeMessage() {
        System.out.println("*************************************");
        System.out.println("  Welcome to Tic-Tac-Toe! ");
        System.out.println("*************************************");
        System.out.println("You will play against the AI bot. Good luck!");
        System.out.println("Here’s how the board looks:");
        System.out.println("Positions: 1 | 2 | 3");
        System.out.println("           4 | 5 | 6");
        System.out.println("           7 | 8 | 9");
    }

    private static void chooseSymbol() {
        System.out.println("Choose your symbol (X or O)");
        System.out.println("Enter X for X or O for O: ");
        while (true) {
            String input = scanner.nextLine().trim().toUpperCase();
            if (input.equals("X")) {
                HUMAN = 1;
                BOT = 2;
                System.out.println("You are X. Bot is O.");
                break;
            } else if (input.equals("O")) {
                HUMAN = 2;
                BOT = 1;
                System.out.println("You are O. Bot is X.");
                break;
            } else {
                System.out.println("Invalid choice! Please choose X or O: ");
            }
        }
    }

    private static void displayGoodbyeMessage() {
        System.out.println("\n*************************************");
        System.out.println("  Thanks for playing Tic-Tac-Toe!");
        System.out.println("  See you next time!");
        System.out.println("*************************************");
    }

    private static void displayBoard() {
        System.out.println("\nCurrent Board:");
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                String symbol = switch (board[row][col]) {
                    case 1 -> "X"; // X
                    case 2 -> "O"; // O
                    default -> " "; // Empty cell
                };
                System.out.print(" " + symbol + " ");
                if (col < 2) System.out.print("|"); // Add separator for columns
            }
            System.out.println();
            if (row < 2) System.out.println("---+---+---"); // Add separator for rows
        }
    }

    private static int[] takeInput() {
        while (true) {
            int input = scanner.nextInt() - 1;
            int row = input / 3;
            int col = input % 3;

            if (input >= 0 && input < 9 && board[row][col] == 0) {
                return new int[]{row, col};
            }
            System.out.println("Invalid move! Choose an empty position (1-9):");
        }
    }

    private static void updateBoard(int[] pos, int player) {
        board[pos[0]][pos[1]] = player;
    }

    private static boolean checkWin(int player) {
        for (int row = 0; row < 3; row++) {
            if (board[row][0] == player && board[row][1] == player && board[row][2] == player) return true;
        }

        for (int col = 0; col < 3; col++) {
            if (board[0][col] == player && board[1][col] == player && board[2][col] == player) return true;
        }

        if (board[0][0] == player && board[1][1] == player && board[2][2] == player) return true;

        return board[0][2] == player && board[1][1] == player && board[2][0] == player;
    }

    private static boolean isGameDraw() {
        for (int[] row : board) {
            for (int cell : row) {
                if (cell == 0) return false;
            }
        }
        return true;
    }

    private static int[] calculateBotMove() {
        int[] bestMove = new int[2];
        int bestScore = Integer.MIN_VALUE;

        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                if (board[row][col] == 0) {
                    board[row][col] = BOT;
                    int score = minimax(false);
                    board[row][col] = 0;

                    if (score > bestScore) {
                        bestScore = score;
                        bestMove[0] = row;
                        bestMove[1] = col;
                    }
                }
            }
        }

        System.out.println("Bot's turn...");
        return bestMove;
    }

    private static int minimax(boolean isMaximizing) {
        if (checkWin(HUMAN)) return -1;
        if (checkWin(BOT)) return 1;
        if (isGameDraw()) return 0;

        int bestScore = isMaximizing ? Integer.MIN_VALUE : Integer.MAX_VALUE;

        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                if (board[row][col] == 0) {
                    board[row][col] = isMaximizing ? BOT : HUMAN;
                    int score = minimax(!isMaximizing);
                    board[row][col] = 0;
                    bestScore = isMaximizing ? Math.max(bestScore, score) : Math.min(bestScore, score);
                }
            }
        }

        return bestScore;
    }
}
