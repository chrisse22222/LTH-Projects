package ovn8;
import java.awt.Color;

import se.lth.cs.window.SimpleWindow;
public class Turtle 
{
	private boolean isPenDown;
	private SimpleWindow w;
	private int x, y, alfa;
	private Color color;
  /** skapar en sk�ldpadda som ritar i ritf�nstret w. 
      Fr�n b�rjan befinner sig sk�ldpaddan i punkten xHome,yHome med pennan 
      lyft och huvudet pekande rakt upp�t i f�nstret, dvs i negativ y-riktning  
  */
  Turtle(SimpleWindow w, int xHome, int yHome) 
  {
	  this.w = w;
	  x = xHome;
	  y = yHome;
	  alfa = 90;
	  color = color.black;
  }

  /** s�nker pennan */
  public void penDown() 
  {
	  isPenDown = true;
  }

  /** lyfter pennan */
  public void penUp() 
  {
	isPenDown = false;  
  }

  /** g�r rakt fram�t n pixlar i huvudets riktning */
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

  /** vrider huvudet beta grader �t v�nster */
  public void left(int beta) 
  {
	  alfa += beta;
	  if (alfa >= 360) 
	  {
		  alfa -= 360;
	  }
  }

  /** vrider hvudet beta grader �t h�ger */
  public void right(int beta) 
  {
	  alfa -= beta;
	  if (alfa <= 0) 
	  {
		  alfa += 360;
	  }
  }

  /** g�r till punkten newX,newY utan att rita. 
      Pennans l�ge och huvudets riktning p�verkas inte */
  public void jumpTo(int newX, int newY) 
  {
	  x = newX;
	  y = newY;
  }

  /** �terst�ller huvudets riktning till den ursprungliga */
  public void turnNorth() 
  {
	  alfa = 90;
  }

  /** tar reda p� sk�ldpaddans aktuella x-koordinat */
  public int getX() 
  {
	  return x;
  }

  /** tar reda p� sk�ldpaddans aktuella y-koordinat */
  public int getY() 
  {
	  return y;
  }
  
  public void SetColor(Color color) 
  {
	  this.color = color;
  }
  
}
