package sudoku;

import java.util.Scanner;

public class Sudoku
{

    public static void main(String[] args)
    {
        int[][] initBoard = new int[][] { { 1, 0, 0, 2 }, { 0, 0, 0, 0 },
                { 0, 0, 3, 1 }, { 0, 0, 0, 4 } };

        SudokuBoard board = new SudokuBoard(initBoard);
        board.printSudokuBoard();

        @SuppressWarnings("resource")
        Scanner scanner = new Scanner(System.in);

        while (true)
        {

            System.out.println(
                    "Enter row, column and value separated by 'space' or 999 to exit: ");
            int row = scanner.nextInt();

            if (row == 999)
            {
                System.out.println(
                        System.lineSeparator() + "Goodbye, Sudoku exit");
                System.exit(0);
            }

            int col = scanner.nextInt();
            int value = scanner.nextInt();

            board.setCell(value, row, col);
            board.printSudokuBoard();
            // Check if Solved
            if (board.isSolved())
            {
                System.out.println(System.lineSeparator()
                        + "Congratulations, Sudoku is solved!");
                System.exit(0);
            }
        }
    }
}
