package sudoku;

public class SudokuBoard
{

    private int[][] mySudokuBoard;
    private int myBoardSize;
    private int myBoardRegionSize;
    private SudokuLogic myLogic;

    public SudokuBoard(int size)
    {
        mySudokuBoard = new int[size][size];
        myBoardSize = size;
        myBoardRegionSize = (int) Math.sqrt(size);
        myLogic = new SudokuLogic(this);
        myLogic.generateBoard();
    }

    public SudokuBoard(int[][] board)
    {
        mySudokuBoard = board;
        myBoardSize = board.length;
        myBoardRegionSize = (int) Math.sqrt(myBoardSize);
        myLogic = new SudokuLogic(this);
    }

    public SudokuBoard getCopy()
    {
        return new SudokuBoard(mySudokuBoard.clone());
    }

    public int getSize()
    {
        return myBoardSize;
    }

    public int getRegionSize()
    {
        return myBoardRegionSize;
    }

    public int getCell(int row, int column)
    {
        return mySudokuBoard[row][column];
    }

    public void setCell(int value, int row, int column)
    {
        if (value == 0 || myLogic.isValid(row, column, value))
        {
            mySudokuBoard[row - 1][column - 1] = value;
        }
    }

    public boolean isSolved()
    {
        for (int[] row : mySudokuBoard)
        {
            for (int i : row)
            {
                if (i == 0)
                {
                    return false;
                }
            }
        }
        return true;
    }

    public void printSudokuBoard()
    {
        int printHorizontalBorder = 0;

        StringBuilder sb = new StringBuilder(checkPrintBoardSize());

        printHorizontalBorder(sb);

        for (int[] values : mySudokuBoard)
        {
            printHorizontalBorder++;
            sb.append("!");
            int printVerticalBorder = 0;
            for (int value : values)
            {
                printVerticalBorder++;
                sb.append(" " + value + " ");
                if (printVerticalBorder % myBoardSize == myBoardRegionSize)
                {
                    sb.append("!");
                }
            }
            sb.append("!" + System.lineSeparator());
            if (printHorizontalBorder % myBoardSize == myBoardRegionSize)
            {
                printHorizontalBorder(sb);
            }
        }
        printHorizontalBorder(sb);

        System.out.print(sb.toString());
    }

    private int checkPrintBoardSize()
    {
    	int length = 0;
        length++;
        for (int i = 1; i <= getSize(); i++)
        {
            length = length + 3;
            if (i % getRegionSize() == 0)
            {
                length++;
            }
        }
        return length;    	
    }
    
    private void printHorizontalBorder(StringBuilder sb)
    {
        sb.append('-');
        for (int i = 1; i <= getSize(); i++)
        {
            sb.append("---");
            if (i % getRegionSize() == 0)
            {
                sb.append('-');
            }
        }
        sb.append(System.lineSeparator());
    }
}
