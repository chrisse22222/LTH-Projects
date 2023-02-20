package ovn1;
import se.lth.cs.window.SimpleWindow;
import java.util.Scanner;
import se.lth.cs.p.ovn.turtle.Turtle;

public class MyProgram 
{
	public void Run() 
	{
		SimpleWindow x = new SimpleWindow(750, 750, "Turtles");
		Turtle t = new Turtle(x, 250, 300);
		DrawFigure(t);
		
		Scanner scanner = new Scanner(System.in);
		scanner.nextLine();
		x.clear();
		
		DrawSquare(t);
		
		scanner.nextLine();
		x.clear();
		
		DrawTriangle(t, 300, 500);
		DrawTriangle(t, 350, 500);
		scanner.nextLine();
		x.clear();
	}
	
	private void DrawFigure(Turtle t) 
	{
		t.penDown();
		t.forward(100);
		t.penUp();
		t.forward(25);
		t.left(90);
		t.forward(50);
		t.right(180);
		t.penDown();
		t.forward(100);
	}
	
	private void DrawSquare(Turtle t) 
	{
		t.jumpTo(50, 600);
		for (int i = 0; i <= 4; ++i) 
		{
			t.forward(500);
			t.left(90);
		}
	}
	
	private void DrawTriangle(Turtle t, int x, int y) 
	{
		t.jumpTo(x, y);
		t.left(90);
		for (int i = 0; i <= 3; ++i) 
		{
			t.forward(100);
			t.right(120);
		}
		
		t.right(90);
	}
}
