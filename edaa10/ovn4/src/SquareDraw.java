import se.lth.cs.p.ovn.turtle.Turtle;
import se.lth.cs.window.SimpleWindow;
import java.util.Random;
import java.util.Scanner;

public class SquareDraw 
{
	SimpleWindow simple;
	Turtle t;
	Scanner scan;
	
	public void Run() 
	{
		scan = new Scanner(System.in);
		simple = new SimpleWindow(1200, 1200, "Square");
		t = new Turtle(simple, 0, 0);
		t.penDown();

		while (true)
		{
			simple.waitForMouseClick();
			DrawSquare(simple.getMouseX(), simple.getMouseY());
			
			System.out.println("If you do not want to draw more squares, type e");
			if (scan.hasNext("e")) 
			{
				break;
			}
		} 
		
		RandomTurtle();
	}
	
	public void RandomTurtle() 
	{
		simple.clear();
		Turtle o = new Turtle(simple, 250, 250);
		Random random = new Random();
		
		o.penDown();
		t.jumpTo(350, 350);
		t.penDown();
		
		double distanceX = 0;
		double distanceY = 0;
		double distance = 0;
		do {
			distanceX = Math.abs(t.getX() - o.getX());
			distanceY = Math.abs(t.getY() - o.getY());
			distance = Math.sqrt((distanceX * distanceX + distanceY * distanceY));
			
			t.forward(random.nextInt(10) + 1);
			o.forward(random.nextInt(10) + 1);
			
			t.right(random.nextInt(360));
			o.right(random.nextInt(360));
			simple.delay(5);
			
		} while(distance >= 50);
		
		System.out.println("Done!");
		
	}
	
	private void DrawSquare(int posX, int posY) 
	{
		t.jumpTo(posX, posY);
		for (int i = 0; i < 4; ++i) 
		{
			t.right(90);
			t.forward(300);
		}
	}
	
}
