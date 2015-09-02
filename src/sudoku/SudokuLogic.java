package sudoku;

public class SudokuLogic
{
    private SudokuBoard mySudokuBoard;

    public SudokuLogic(SudokuBoard sudokuBoard)
    {
        mySudokuBoard = sudokuBoard;
    }

    public void generateBoard()
    {
        // TODO: Generate valid start sudoku board
    }

    public boolean isValid(int row, int column, int value)
    {
    	// Check if row and column are valid
    	if (!isValidValue(row) || !isValidValue(column))
    	{
            System.out.println("Row or column value is invalid. Valid values are 1 - " + mySudokuBoard.getSize());
            return false;    		
    	}
    	
        // First get a copy and set the cell you want to change to 0
        SudokuBoard board = mySudokuBoard.getCopy();
        board.setCell(0, row, column);

        if (!isValidValue(value))
        {
            System.out.println("Valid values are 1 - " + board.getSize() + ".");
            return false;
        }

        for (int i = 0; i < board.getSize(); i++)
        {
            // Find the region start row/column
            int c = ((column - 1) / board.getRegionSize())
                    * board.getRegionSize();
            int r = ((row - 1) / board.getRegionSize()) * board.getRegionSize();

            if (board.getCell(row - 1, i) == value)
            {
                System.out.println("Entered value, " + value + ", already exists on row.");
                return false;
            }
            else if (board.getCell(i, column - 1) == value)
            {
                System.out.println("Entered value, " + value + ", already exists in column.");
                return false;

            }
            else if (board.getCell(r + (i % board.getRegionSize()),
                    c + (i / board.getRegionSize())) == value)
            {
                System.out.println("Entered value, " + value + ", already exists in region.");
                return false;
            }
        }
        return true;
    }

    private boolean isValidValue(int value)
    {
        return value > 0 && value <= mySudokuBoard.getSize();
    }
}
