import java.util.ArrayList;
import java.util.List;

public class SudokuSolver {

    private static final int GRID_SIZE = 9;

    public static void main(String[] args) {
        int[][] sudokuBoard = {
            // Replace with an unsolved Sudoku puzzle (0 represents empty cells)
            {0, 0, 3, 0, 2, 0, 8, 0, 0},
            {0, 7, 0, 0, 0, 0, 0, 4, 0},
            {9, 0, 0, 7, 4, 0, 0, 0, 1},
            {0, 5, 0, 0, 0, 0, 7, 3, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 8, 0, 0, 0, 0, 1, 0},
            {0, 9, 0, 0, 0, 1, 0, 0, 8},
            {0, 0, 0, 0, 0, 7, 0, 0, 0},
            {0, 0, 0, 0, 6, 8, 9, 0, 0}
        };

        if (solveSudoku(sudokuBoard)) {
            System.out.println("Sudoku Solved!");
            printBoard(sudokuBoard);
        } else {
            System.out.println("No solution found for the given Sudoku puzzle.");
        }
    }

    private static boolean solveSudoku(int[][] board) {
        for (int row = 0; row < GRID_SIZE; row++) {
            for (int col = 0; col < GRID_SIZE; col++) {
                if (board[row][col] == 0) {
                    for (int num = 1; num <= GRID_SIZE; num++) {
                        if (isValidPlacement(board, row, col, num)) {
                            board[row][col] = num;
                            if (solveSudoku(board)) {
                                return true;
                            } else {
                                board[row][col] = 0; // Backtrack if placement doesn't lead to solution
                            }
                        }
                    }
                    return false; // No valid number found for this cell
                }
            }
        }
        return true; // All cells filled successfully
    }

    private static boolean isValidPlacement(int[][] board, int row, int col, int num) {
        // Check row and column for conflicts
        for (int i = 0; i < GRID_SIZE; i++) {
            if (board[row][i] == num || board[i][col] == num) {
                return false;
            }
        }

        // Check subgrid for conflicts (assuming 3x3 subgrids)
        int subgridRowStart = (row / 3) * 3;
        int subgridColStart = (col / 3) * 3;
        for (int i = subgridRowStart; i < subgridRowStart + 3; i++) {
            for (int j = subgridColStart; j < subgridColStart + 3; j++) {
                if (board[i][j] == num) {
                    return false;
                }
            }
        }

        return true;
    }

    private static void printBoard(int[][] board) {
        for (int i = 0; i < GRID_SIZE; i++) {
            for (int j = 0; j < GRID_SIZE; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
    }
}
