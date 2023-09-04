public class LifeBoard 
{
	private boolean [][] board;
	private int rows;
	private int cols;
	private int generation;
	/** Skapar en spelplan med rows rader och cols kolonner. Spelplanen är från
	    början tom, dvs alla rutorna är tomma och generationsnumret är 1. */	
	public LifeBoard(int rows, int cols) 
	{
		this.rows = rows;
		this.cols = cols;
		board = new boolean[rows][cols];
		generation = 1;
	}

	/** Ger true om det finns en individ i rutan med index row, col, false annars. 
	    Om index row, col är utanför spelplanen returneras false */
	public boolean get(int row, int col) 
	{
		if (row >= rows || row < 0 || col >= cols || col < 0) 
		{
			return false; // Utanför spelplanen
		}
		
		if (board[row][col]) 
		{
			return true;
		}
		else 
		{
			return false;
		}
	}

	/** Lagrar värdet val i rutan med index row, col */
	public void put(int row, int col, boolean val) 
	{
		if (row >= rows || row < 0 || col >= cols || col < 0) 
		{
			System.out.println("Error, utanför spelplanen!");
			return; // Utanför spelplanen
		}

		board[row][col] = val;
	}

	/** Tar reda på antalet rader */
	public int getRows() 
	{
		return rows;
	}

 	/** Tar reda på antalet kolonner */
	public int getCols() 
	{
		return cols;
	}

	/** Tar reda på aktuellt generationsnummer */
	public int getGeneration() 
	{
		return generation;
	}

	/** Ökar generationsnumret med ett */
	public void increaseGeneration() 
	{
		generation++;
	}
}