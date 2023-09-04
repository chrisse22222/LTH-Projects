import se.lth.cs.window.SimpleWindow;

import java.util.Random;
import java.util.Scanner;

public class Figure 
{
	private Turtle t;
	private SimpleWindow w;
	private Scanner scan;
	public Figure(int xSize, int ySize) 
	{	
		w = new SimpleWindow(xSize, ySize, "Fönster");
		t = new Turtle(w, 0, 0);
		scan = new Scanner(System.in);
		t.penDown();
	}
	
	public void Run() 
	{
		System.out.println("Skriv in antal kvadrater som du vill rita");
		int repeat = scan.nextInt();
		
		for (int i = 0; i <= repeat; i++) 
		{
			w.waitForMouseClick();
			DrawSquare(w.getMouseX(), w.getMouseY());
		}
		
		System.out.println("Färdig med kvadraterna");
		t.turnNorth();	
		RandomTurtle();
	}
	
	public void RandomTurtle() 
	{
		w.clear();
		Turtle t2 = new Turtle(w, 250, 250);
		t2.penDown();
		t.jumpTo(350, 350);
		Random random = new Random();
		
		double distanceX = 0;
		double distanceY = 0;
		double distance = 0;
		do {
			distanceX = Math.abs(t.getX() - t2.getX());
			distanceY = Math.abs(t.getY() - t2.getY());
			distance = Math.sqrt((distanceX * distanceX + distanceY * distanceY));
			
			t.forward(random.nextInt(10) + 1);
			t2.forward(random.nextInt(10) + 1);
			
			t.right(random.nextInt(360));
			t2.right(random.nextInt(360));
			w.delay(5);
			
		} while(distance >= 50);
		
		System.out.println("Klar!");
	}
	
	/** Ritar en kvadrat i position x, y */
	private void DrawSquare(int posX, int posY) 
	{
		t.jumpTo(posX, posY);
		for (int i = 0; i < 4; ++i) 
		{
			t.right(90);
			t.forward(50);
		}
	}
}
