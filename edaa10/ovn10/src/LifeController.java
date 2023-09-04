
public class LifeController 
{
	public static void main (String [] args) 
	{
		LifeBoard board = new LifeBoard(15, 15);
		LifeView window = new LifeView(board);
		Life life = new Life(board);
		
		while (true) 
		{
			switch (window.getCommand()) 
			{
				case 1:
					if (!board.get(window.getRow(), window.getCol())) 
					{
						board.put(window.getRow(), window.getCol(), true);
					}
					else
					{
						board.put(window.getRow(), window.getCol(), false);
					}
					break;
				
				case 2:
					life.newGeneration();
					break;
				
				case 3:
					System.exit(3);
					break;
					
				default:
					break;
			}
			
			window.update();
		}
	}
}
