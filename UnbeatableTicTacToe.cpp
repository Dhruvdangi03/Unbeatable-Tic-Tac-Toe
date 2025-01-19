#include <iostream>
#include <vector>
#include <windows.h>

using namespace std;

vector<vector<int>> board(3, vector<int>(3, 0));
int HUMAN, BOT; // Human and Bot player symbols (1 for X, 2 for O)

void displayWelcomeMessage() {
    cout << "*************************************" << endl;
    cout << "  Welcome to Tic-Tac-Toe! " << endl;
    cout << "*************************************" << endl;
    cout << "You will play against the AI bot. Good luck!" << endl;
    cout << "Here’s how the board looks:" << endl;
    cout << "Positions: 1 | 2 | 3" << endl;
    cout << "           4 | 5 | 6" << endl;
    cout << "           7 | 8 | 9" << endl;
}

void chooseSymbol() {
    string input;
    cout << "Choose your symbol (X or O)" << endl;
    cout << "Enter X for X or O for O: " << endl;
    while (true) {
        cin >> input;
        if (input == "X" || input == "x") {
            HUMAN = 1;
            BOT = 2;
            cout << "You are X. Bot is O." << endl;
            break;
        } else if (input == "O" || input == "o") {
            HUMAN = 2;
            BOT = 1;
            cout << "You are O. Bot is X." << endl;
            break;
        } else {
            cout << "Invalid choice! Please choose X or O: "<< endl;
        }
    }
}

void displayBoard() {
    cout << "\nCurrent Board:" << endl;
    for (int row = 0; row < 3; row++) {
        for (int col = 0; col < 3; col++) {
            char symbol = ' ';
            if (board[row][col] == HUMAN) symbol = 'X';
            if (board[row][col] == BOT) symbol = 'O';
            cout << " " << symbol << " ";
            if (col < 2) cout << "|"; // Add separator for columns
        }
        cout << endl;
        if (row < 2) cout << "---+---+---" << endl; // Add separator for rows
    }
}

bool checkWin(int player) {
    for (int row = 0; row < 3; row++) {
        if (board[row][0] == player && board[row][1] == player && board[row][2] == player) return true;
    }

    for (int col = 0; col < 3; col++) {
        if (board[0][col] == player && board[1][col] == player && board[2][col] == player) return true;
    }

    if (board[0][0] == player && board[1][1] == player && board[2][2] == player) return true;

    return board[0][2] == player && board[1][1] == player && board[2][0] == player;
}

bool isGameDraw() {
    for (auto& row : board) {
        for (auto& cell : row) {
            if (cell == 0) return false;
        }
    }
    return true;
}

int minimax(bool isMaximizing) {
    if (checkWin(HUMAN)) return -1;
    if (checkWin(BOT)) return 1;
    if (isGameDraw()) return 0;

    int bestScore = isMaximizing ? -1000 : 1000;

    for (int row = 0; row < 3; row++) {
        for (int col = 0; col < 3; col++) {
            if (board[row][col] == 0) {
                board[row][col] = isMaximizing ? BOT : HUMAN;
                int score = minimax(!isMaximizing);
                board[row][col] = 0;
                bestScore = isMaximizing ? max(bestScore, score) : min(bestScore, score);
            }
        }
    }

    return bestScore;
}

pair<int, int> calculateBotMove() {
    int bestScore = -1000;
    pair<int, int> bestMove = {-1, -1};

    for (int row = 0; row < 3; row++) {
        for (int col = 0; col < 3; col++) {
            if (board[row][col] == 0) {
                board[row][col] = BOT;
                int score = minimax(false);
                board[row][col] = 0;

                if (score > bestScore) {
                    bestScore = score;
                    bestMove = {row, col};
                }
            }
        }
    }

    cout << "Bot's turn..." << endl;
    return bestMove;
}

pair<int, int> takeInput() {
    int input;
    while (true) {
        cout << "Your Turn! Choose a position (1-9): "<< endl;
        cin >> input;
        input -= 1; // Convert to 0-indexed

        int row = input / 3;
        int col = input % 3;

        if (input >= 0 && input < 9 && board[row][col] == 0) {
            return {row, col};
        }
        cout << "Invalid move! Choose an empty position (1-9): "<< endl;
    }
}

void updateBoard(pair<int, int> pos, int player) {
    board[pos.first][pos.second] = player;
}

void displayGoodbyeMessage() {
    cout << "\n*************************************" << endl;
    cout << "  Thanks for playing Tic-Tac-Toe!" << endl;
    cout << "  See you next time!" << endl;
    cout << "*************************************" << endl;
}

int main() {
    displayWelcomeMessage();
    chooseSymbol();

    int currentPlayer = HUMAN;

    while (true) {
        displayBoard();
        pair<int, int> position = (currentPlayer == HUMAN) ? takeInput() : calculateBotMove();
        updateBoard(position, currentPlayer);

        if (checkWin(currentPlayer)) {
            displayBoard();
            if (currentPlayer == HUMAN) {
                cout << "Congratulations! You won!" << endl;
            } else {
                cout << "Game Over !!!" << endl;
                cout << "You can't Beat the Unbeatable BOT!" << endl;
            }
            Sleep(10000);
            break;
        }

        if (isGameDraw()) {
            displayBoard();
            cout << "It's a draw! What a tight match!" << endl;
            break;
        }

        currentPlayer = (currentPlayer == HUMAN) ? BOT : HUMAN;
    }

    displayGoodbyeMessage();

    return 0;
}
