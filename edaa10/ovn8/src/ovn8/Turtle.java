package ovn8;
import java.awt.Color;

import se.lth.cs.window.SimpleWindow;
public class Turtle 
{
	private boolean isPenDown;
	private SimpleWindow w;
	private int x, y, alfa;
	private Color color;
  /** skapar en sköldpadda som ritar i ritfönstret w. 
      Från början befinner sig sköldpaddan i punkten xHome,yHome med pennan 
      lyft och huvudet pekande rakt uppåt i fönstret, dvs i negativ y-riktning  
  */
  Turtle(SimpleWindow w, int xHome, int yHome) 
  {
	  this.w = w;
	  x = xHome;
	  y = yHome;
	  alfa = 90;
	  color = color.black;
  }

  /** sänker pennan */
  public void penDown() 
  {
	  isPenDown = true;
  }

  /** lyfter pennan */
  public void penUp() 
  {
	isPenDown = false;  
  }

  /** går rakt framåt n pixlar i huvudets riktning */
  public void forward(int n) 
  {  
	  w.setLineColor(color);
	  
	  w.moveTo(x, y);
	  y -= Math.round(n*Math.sin(Math.toRadians(alfa)));
	  x += Math.round(n*Math.cos(Math.toRadians(alfa)));
	  
	    if (isPenDown) {
		      w.lineTo(x, y);
	    }
  }

  /** vrider huvudet beta grader åt vänster */
  public void left(int beta) 
  {
	  alfa += beta;
	  if (alfa >= 360) 
	  {
		  alfa -= 360;
	  }
  }

  /** vrider hvudet beta grader åt höger */
  public void right(int beta) 
  {
	  alfa -= beta;
	  if (alfa <= 0) 
	  {
		  alfa += 360;
	  }
  }

  /** går till punkten newX,newY utan att rita. 
      Pennans läge och huvudets riktning påverkas inte */
  public void jumpTo(int newX, int newY) 
  {
	  x = newX;
	  y = newY;
  }

  /** återställer huvudets riktning till den ursprungliga */
  public void turnNorth() 
  {
	  alfa = 90;
  }

  /** tar reda på sköldpaddans aktuella x-koordinat */
  public int getX() 
  {
	  return x;
  }

  /** tar reda på sköldpaddans aktuella y-koordinat */
  public int getY() 
  {
	  return y;
  }
  
  public void SetColor(Color color) 
  {
	  this.color = color;
  }
  
}
