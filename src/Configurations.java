/*
CS2210 Assignment 2 X+ Game
Talha Chaudhry
tchaud@uwo.ca
251390028
October 17, 2023
 */

public class Configurations {

    private int boardSize; // Size of the board
    private int lengthToWin; // Length of X and + shape needed to win
    private int maxLevels; // Maximum level of the game tree that is explored by algorithm
    private char[][] board; // 2D array that will store the game board

    // Constructor method that creates a Configurations object with parameters given and initializes the game board
    public Configurations(int board_size, int lengthToWin, int max_levels) {

        boardSize = board_size;
        this.lengthToWin = lengthToWin;
        maxLevels = max_levels;
        board = new char[board_size][board_size];

        for (int i = 0; i < board_size; i++) { // Sets each index in game board to an empty value instead of null
            for (int j = 0; j < board_size; j++) {
                board[i][j] = ' ';
            }
        }
    }
    // This method will create and return an empty HashDictionary with a size of 8297. The size 8297 was a random
    // prime number chosen from between 6000 and 10000.
    public HashDictionary createDictionary() {
        return new HashDictionary(8297);
    }

    // This method first stores the characters of board in a String, then it checks whether the
    // String representing the board is in the parameter hashTable: If the String is in hashTable, this method
    // returns its associated score, otherwise it returns the value -1.
    public int repeatedConfiguration(HashDictionary hashTable) {

        StringBuilder s = new StringBuilder(); // Use StringBuilder to create a string from characters
        for (int i = 0; i < boardSize; i++) {
            for (int j = 0; j < boardSize; j++) {
                s.append(board[i][j]); // Add every character to s using StringBuilder
            }
        }
        String toReturn = s.toString(); // toReturn is converted to a String with toString method
        return hashTable.get(toReturn);
    }

    // This method first represents the content of board as a String; then it inserts this String and score in
    // hashDictionary as a Data object.
    public void addConfiguration(HashDictionary hashDictionary, int score) {

        StringBuilder s = new StringBuilder(); // Use StringBuilder to create a string from characters
        for (int i = 0; i < boardSize; i++) {
            for (int j = 0; j < boardSize; j++) {
                s.append(board[i][j]); // Add every character to s using StringBuilder
            }
        }
        String toAdd = s.toString(); // toAdd is converted to a String with toString method
        hashDictionary.put(new Data(toAdd, score)); // Returns a Data object with the config as toAdd and given score
    }
    // This method stores the symbol in board[row][col]
    public void savePlay(int row, int col, char symbol) {
        board[row][col] = symbol;
    }

    // This method returns true if board[row][col] is ’ ’; otherwise it returns false
    public boolean squareIsEmpty(int row, int col) {
        if (board[row][col] == ' ') return true;
        else return false;
    }

    // This method returns true if there is an X-shape or a + shape with length of lengthToWin formed by tiles of the
    // kind of symbol on the board. Returns true if there is an X or + shape found with length of lengthToWin and
    // false if it is not found.
    public boolean wins(char symbol) {

        int count = 0;
        for (int i = 1; i < boardSize-1; i++) { // bounds are 1 to boardSize - 1 to prevent IndexOutOfBound error,
            for (int j = 1; j < boardSize-1; j++) { // and also the center of an X or + shape cannot be on an edge

                // Looks for the center tile of a + shape by checking to see if there is a symbol stored to the
                // North, South, East and West of the center tile

                if ((board[i][j] == symbol) && (board[i][j-1] == symbol) && (board[i+1][j] == symbol) &&
                        (board[i][j+1] == symbol) && (board[i-1][j] == symbol)) { // + Shape found
                    count = 1; // Center tile found, start count at +1

                    for (int x = j-1; x > -1; x--)  {   // Counts symbols West of center tile
                        if (board[i][x] == symbol) count++;
                        if (board[i][x] != symbol) break; // Breaks current loop from this direction
                        if (count == lengthToWin) return true;
                    }
                    for (int x = i+1; x < boardSize; x++) { // Counts symbols North of center tile
                        if (board[x][j] == symbol) count++;
                        if (board[x][j] != symbol) break; // Breaks current loop from this direction
                        if (count == lengthToWin) return true;
                    }
                    for (int x = j+1; x < boardSize; x++) { // Counts symbols East of center tile
                        if (board[i][x] == symbol) count++;
                        if (board[i][x] != symbol) break; // Breaks current loop from this direction
                        if (count == lengthToWin) return true;
                    }
                    for (int x = i-1; x > -1; x--) { // Counts symbols South of center tile
                        if (board[x][j] == symbol) count++;
                        if (board[x][j] != symbol) break; // Breaks current loop from this direction
                        if (count == lengthToWin) return true;
                    }

                    if (count == lengthToWin) return true;
                }

                // Looks for the center tile of an X shape by checking to see if there is a symbol stored to the
                // North-East, South-East, North-West, and South-West of the center tile

                if ((board[i][j] == symbol) && (board[i + 1][j + 1] == symbol) && (board[i - 1][j - 1] == symbol) &&
                        (board[i - 1][j + 1] == symbol) && (board[i + 1][j - 1] == symbol)) { // X shape found
                    count = 1; // Center tile found, start count at 1

                    // Counts symbols North-East of center tile
                    for (int x = i + 1, p = j + 1; x < boardSize && p < boardSize; x++, p++) {
                        if (board[x][p] == symbol) count++;
                        if (board[x][p] != symbol) break; // Breaks current loop from this direction
                        if (count == lengthToWin) return true;
                        }

                    // Counts symbols South-West of center tile
                    for (int x = i - 1, p = j - 1; x > -1 && p > -1; x--, p--) {
                        if (board[x][p] == symbol) count++;
                        if (board[x][p] != symbol) break; // Breaks current loop from this direction
                        if (count == lengthToWin) return true;
                        }

                    // Counts symbols South-East of center tile
                    for (int x = i - 1, p = j + 1; x > -1 && p < boardSize; x--, p++) {
                        if (board[x][p] == symbol) count++;
                        if (board[x][p] != symbol) break; // Breaks current loop from this direction
                        if (count == lengthToWin) return true;
                    }
                    // Counts symbols North-West of center tile
                    for (int x = i + 1, p = j - 1; x < boardSize && p > -1; x++, p--) {
                        if (board[x][p] == symbol) count++;
                        if (board[x][p] != symbol) break; // Breaks current loop from this direction
                        if (count == lengthToWin) return true;
                    }
                    if (count == lengthToWin) return true;
                }
            }
        }
        return false; // No shape of X or + with length of lengthToWin was found, returns false
    }

    // This method returns true if board has no empty positions left and no player has won the game.
    public boolean isDraw() {
        for (int i = 0; i < boardSize; i++) { // Checks every index of board to see if it is empty
            for (int j = 0; j < boardSize; j++) {
                if (board[i][j] == ' ') return false; // Empty index in board found, return false
            }
        }
        return (!wins('X') && !wins('O')); // Returns false if a player won, true if no player won
    }

    // This method is used to find if a player or computer has won, or if there is a draw
    // This method will return 3, if the computer has won, i.e. there is an X-shape or a +shape formed by tiles of type
    // 'O' on the board, 0 if the human player has won, 2 if the game is a draw, i.e. there are no empty positions in
    // board and no player has won, 1 if the game is still undecided, i.e. there are still empty positions in board and
    // no player has won yet.
    public int evalBoard() {
        if (wins('O')) return 3;
        if (wins('X')) return 0;
        if (isDraw()) return 2;
        return 1;
    }

}
