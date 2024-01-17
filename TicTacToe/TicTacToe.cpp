#include <iostream>
#include <vector>

using namespace std;

const char EMPTY = ' ';
const char PLAYER_X = 'X';
const char PLAYER_O = 'O';

struct Move {
    int row, col;
};

class TicTacToe {
public:
    TicTacToe();
    void playGame();

private:
    void printBoard();
    bool isBoardFull();
    bool isWinner(char player);
    bool makeMove(int row, int col, char player);
    Move getBestMove();
    int minimax(bool isMaximizing);
    
    vector<vector<char>> board;
};

TicTacToe::TicTacToe() : board(3, vector<char>(3, EMPTY)) {}

void TicTacToe::printBoard() {
    for (const auto &row : board) {
        for (char cell : row) {
            cout << cell << " ";
        }
        cout << endl;
    }
}

bool TicTacToe::isBoardFull() {
    for (const auto &row : board) {
        for (char cell : row) {
            if (cell == EMPTY) {
                return false;
            }
        }
    }
    return true;
}

bool TicTacToe::isWinner(char player) {
    // Check rows and columns
    for (int i = 0; i < 3; ++i) {
        if ((board[i][0] == player && board[i][1] == player && board[i][2] == player) ||
            (board[0][i] == player && board[1][i] == player && board[2][i] == player)) {
            return true;
        }
    }
    // Check diagonals
    if ((board[0][0] == player && board[1][1] == player && board[2][2] == player) ||
        (board[0][2] == player && board[1][1] == player && board[2][0] == player)) {
        return true;
    }
    return false;
}

bool TicTacToe::makeMove(int row, int col, char player) {
    if (row < 0 || row >= 3 || col < 0 || col >= 3 || board[row][col] != EMPTY) {
        return false; // Invalid move
    }

    board[row][col] = player;
    return true;
}

int TicTacToe::minimax(bool isMaximizing) {
    if (isWinner(PLAYER_X)) return -1;
    if (isWinner(PLAYER_O)) return 1;
    if (isBoardFull()) return 0;

    int bestScore = isMaximizing ? INT_MIN : INT_MAX;

    for (int i = 0; i < 3; ++i) {
        for (int j = 0; j < 3; ++j) {
            if (board[i][j] == EMPTY) {
                board[i][j] = isMaximizing ? PLAYER_O : PLAYER_X;
                int score = minimax(!isMaximizing);
                board[i][j] = EMPTY;

                if (isMaximizing) {
                    bestScore = max(bestScore, score);
                } else {
                    bestScore = min(bestScore, score);
                }
            }
        }
    }

    return bestScore;
}

Move TicTacToe::getBestMove() {
    int bestScore = INT_MIN;
    Move bestMove;

    for (int i = 0; i < 3; ++i) {
        for (int j = 0; j < 3; ++j) {
            if (board[i][j] == EMPTY) {
                board[i][j] = PLAYER_O;
                int score = minimax(false);
                board[i][j] = EMPTY;

                if (score > bestScore) {
                    bestScore = score;
                    bestMove.row = i;
                    bestMove.col = j;
                }
            }
        }
    }

    return bestMove;
}

void TicTacToe::playGame() {
    cout << "Tic-Tac-Toe Game - You are X, Computer is O" << endl;

    for (int turn = 0; turn < 9; ++turn) {
        if (turn % 2 == 0) {
            // Human's turn
            int row, col;
            cout << "Enter your move (row and column, e.g., 1 2): ";
            cin >> row >> col;
            --row; --col;

            if (!makeMove(row, col, PLAYER_X)) {
                cout << "Invalid move. Try again." << endl;
                --turn;
                continue;
            }
        } else {
            // Computer's turn
            Move computerMove = getBestMove();
            makeMove(computerMove.row, computerMove.col, PLAYER_O);
            cout << "Computer's move: " << computerMove.row + 1 << " " << computerMove.col + 1 << endl;
        }

        printBoard();

        if (isWinner(PLAYER_X)) {
            cout << "Congratulations! You win!" << endl;
            break;
        } else if (isWinner(PLAYER_O)) {
            cout << "Computer wins! Better luck next time." << endl;
            break;
        } else if (isBoardFull()) {
            cout << "It's a tie!" << endl;
            break;
        }
    }

    cout << "Game over. Thanks for playing!" << endl;
}

int main() {
    TicTacToe game;
    game.playGame();

    return 0;
}

