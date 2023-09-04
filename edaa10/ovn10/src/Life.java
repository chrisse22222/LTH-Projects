
public class Life 
{
	private LifeBoard board;
	private boolean [][] placeHolder;
 	public Life(LifeBoard board) 
	{
		this.board = board;
	}
	
	public void newGeneration() 
	{	
		placeHolder = new boolean[board.getRows()][board.getCols()];
		for (int i = 0; i < placeHolder.length; i++) 
	 	{
			for (int j = 0; j < placeHolder[0].length; j++) 
			{
				placeHolder[i][j] = board.get(i, j);
			}
		}
		
		for (int i = 0; i < placeHolder.length; i++) 
		{
			for (int j = 0; j < placeHolder[0].length; j++) 
			{
				if (getNeighbours(i, j) >= 4 || getNeighbours(i, j) <= 1) 
				{
					if (board.get(i, j))
						flip(i, j);
				}
				else if (getNeighbours(i, j) == 3)
				{
					if (!board.get(i, j))
						flip(i, j);
				}
			}
		}
		
		for (int i = 0; i < placeHolder.length; i++) 
		{
			for (int j = 0; j < placeHolder[0].length; j++) 
			{
				board.put(i, j, placeHolder[i][j]);
			}
		}
		
		board.increaseGeneration();
	}
	
	private int getNeighbours(int row, int col) 
	{
		int sum = 0;
		for (int i = row - 1; i <= row + 1; i++) 
		{
			for (int j = col - 1; j <= col + 1; j++) 
			{
				if (i != row || j != col) 
				{
					if (board.get(i, j)) 
					{
						sum++;
					}
				}
			}
		}
		
		return sum;
	}
	
	private void flip(int row, int col) 
	{
		if (board.get(row, col)) 
		{
			placeHolder[row][col] = false;
		}
		else 
		{
			placeHolder[row][col] = true;
		}
	}
}
