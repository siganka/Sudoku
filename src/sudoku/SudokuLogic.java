package sudoku;

import java.util.Random;

public class SudokuLogic
{
	private SudokuBoard mySudokuBoard;
	private boolean showWarnings = true;

	public SudokuLogic(SudokuBoard sudokuBoard)
	{
		mySudokuBoard = sudokuBoard;
	}

	public void generateBoard()
	{
		showWarnings = false;
		boolean regenerateBoard = true;
		int c1 = 0;
		while (regenerateBoard && c1 < 1000)
		{
			c1++;
			regenerateBoard = false;
			for (int size = 1; size <= mySudokuBoard.getSize(); size++)
			{
				Random randomGen = new Random();

				for (int col = 1; col <= mySudokuBoard.getSize(); col++)
				{
					int randomValue = randomGen.nextInt(mySudokuBoard.getSize()) + 1;
					int counter = 0;

					// Try board size * 100000 when restart
					while (!isValid(size, col, randomValue) && counter < mySudokuBoard.getSize() * 100000)
					{
						randomValue = randomGen.nextInt(mySudokuBoard.getSize()) + 1;
						counter++;
					}
					if (isValid(size, col, randomValue))
					{
						mySudokuBoard.setCell(randomValue, size, col);
					} else
					{
						// no random number found, get first valid number for
						// cell
						boolean found = false;
						for (int v = 1; v <= mySudokuBoard.getSize(); v++)
						{
							if (isValid(size, col, v))
							{
								mySudokuBoard.setCell(v, size, col);
								break;
							}
						}
						if (!found)
						{
							regenerateBoard = true;
							break;
						}
					}
				}
				if (regenerateBoard)
				{
					break;
				}
			}
		}
		if (regenerateBoard)
		{
			System.out.println("Failed to generated valid Sudoku! Try again.");
		}
		showWarnings = true;
	}

	public boolean isValid(int row, int column, int value)
	{
		// Check if row and column are valid
		if (!isValidValue(row) || !isValidValue(column))
		{
			printWarning("Row or column value is invalid. Valid values are 1 - " + mySudokuBoard.getSize());
			return false;
		}

		// First get a copy and set the cell you want to change to 0
		SudokuBoard board = mySudokuBoard.getCopy();
		board.setCell(0, row, column);

		if (!isValidValue(value))
		{
			printWarning("Valid values are 1 - " + board.getSize() + ".");
			return false;
		}

		for (int i = 0; i < board.getSize(); i++)
		{
			// Find the region start row/column
			int c = ((column - 1) / board.getRegionSize()) * board.getRegionSize();
			int r = ((row - 1) / board.getRegionSize()) * board.getRegionSize();

			if (board.getCell(row - 1, i) == value)
			{
				printWarning("Entered value, " + value + ", already exists on row.");
				return false;
			} else if (board.getCell(i, column - 1) == value)
			{
				printWarning("Entered value, " + value + ", already exists in column.");
				return false;

			} else if (board.getCell(r + (i % board.getRegionSize()), c + (i / board.getRegionSize())) == value)
			{
				printWarning("Entered value, " + value + ", already exists in region.");
				return false;
			}
		}
		return true;
	}

	private void printWarning(String message)
	{
		if (showWarnings)
		{
			System.out.println(message);
		}
	}

	private boolean isValidValue(int value)
	{
		return value > 0 && value <= mySudokuBoard.getSize();
	}
}
