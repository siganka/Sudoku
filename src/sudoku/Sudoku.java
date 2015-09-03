package sudoku;

import java.util.Scanner;

public class Sudoku
{

	public static void main(String[] args)
	{
		SudokuBoard board = new SudokuBoard(4);
		// TODO: "clean" board not to show all values........
		board.printSudokuBoard();

		@SuppressWarnings("resource")
		Scanner scanner = new Scanner(System.in);

		while (true)
		{

			System.out.println("Enter row, column and value separated by 'space' or 999 to exit: ");
			int row = scanner.nextInt();

			if (row == 999)
			{
				System.out.println(System.lineSeparator() + "Goodbye, Sudoku exit");
				System.exit(0);
			}

			int col = scanner.nextInt();
			int value = scanner.nextInt();

			board.setCell(value, row, col);
			board.printSudokuBoard();
			// Check if Solved
			if (board.isSolved())
			{
				System.out.println(System.lineSeparator() + "Congratulations, Sudoku is solved!");
				System.exit(0);
			}
		}
	}
}
