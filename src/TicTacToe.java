import java.util.Scanner;

public class TicTacToe {
    // Define constants and create game board
    private static final int ROW = 3;
    private static final int COL = 3;
    private static String[][] board = new String[ROW][COL];
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        // Initialize the board
        clearBoard();
        // Display the initial state of the board
        display();

        while (true) {
            // Player X's turn
            System.out.println("Player X's turn:");
            int[] move = getPlayerMove();
            int row = move[0] - 1;
            int col = move[1] - 1;

            if (!isValidMove(row, col)) {
                System.out.println("Invalid move! Try again.");
                continue;
            }

            board[row][col] = "X";
            display();

            if (isWin("X")) {
                System.out.println("Player X wins!");
                break;
            }

            if (isTie()) {
                System.out.println("It's a tie!");
                break;
            }

            // Player O's turn
            System.out.println("Player O's turn:");
            move = getPlayerMove();
            row = move[0] - 1;
            col = move[1] - 1;

            if (!isValidMove(row, col)) {
                System.out.println("Invalid move! Try again.");
                continue;
            }

            board[row][col] = "O";
            display();

            if (isWin("O")) {
                System.out.println("Player O wins!");
                break;
            }

            if (isTie()) {
                System.out.println("It's a tie!");
                break;
            }
        }
    }

    // Helper method to clear the board
    private static void clearBoard() {
        for (int i = 0; i < ROW; i++) {
            for (int j = 0; j < COL; j++) {
                board[i][j] = " ";
            }
        }
    }

    // Helper method to display the board
    private static void display() {
        System.out.println("-------------");
        for (int i = 0; i < ROW; i++) {
            System.out.print("| ");
            for (int j = 0; j < COL; j++) {
                System.out.print(board[i][j] + " | ");
            }
            System.out.println("\n-------------");
        }
    }

    // Helper method to get player move
    private static int[] getPlayerMove() {
        System.out.print("Enter your move (row[1-3] column[1-3]): ");
        int row = SafeInput.getRangedInt(scanner, "", 1, ROW);
        int col = SafeInput.getRangedInt(scanner, "", 1, COL);
        return new int[]{row, col};
    }

    // Helper method to check if move is valid
    private static boolean isValidMove(int row, int col) {
        return row >= 0 && row < ROW && col >= 0 && col < COL && board[row][col].equals(" ");
    }

    // Helper method to check for win
    private static boolean isWin(String player) {
        return isRowWin(player) || isColWin(player) || isDiagonalWin(player);
    }

    // Helper method to check for row win
    private static boolean isRowWin(String player) {
        for (int i = 0; i < ROW; i++) {
            if (board[i][0].equals(player) && board[i][1].equals(player) && board[i][2].equals(player)) {
                return true;
            }
        }
        return false;
    }

    // Helper method to check for column win
    private static boolean isColWin(String player) {
        for (int j = 0; j < COL; j++) {
            if (board[0][j].equals(player) && board[1][j].equals(player) && board[2][j].equals(player)) {
                return true;
            }
        }
        return false;
    }

    // Helper method to check for tie
    private static boolean isTie() {
        for (int i = 0; i < ROW; i++) {
            for (int j = 0; j < COL; j++) {
                if (board[i][j].equals(" ")) {
                    return false; // If any empty space is found, the game is not tied
                }
            }
        }
        return true; // If no empty space is found, the game is tied
    }

    // Helper method to check for diagonal win
    private static boolean isDiagonalWin(String player) {
        return (board[0][0].equals(player) && board[1][1].equals(player) && board[2][2].equals(player));
    }
}