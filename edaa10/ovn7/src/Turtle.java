import se.lth.cs.window.SimpleWindow;
public class Turtle 
{
	private boolean isPenDown;
	private SimpleWindow w;
	private int x, y, alpha;
  /** skapar en sköldpadda som ritar i ritfönstret w. 
      Från början befinner sig sköldpaddan i punkten xHome,yHome med pennan 
      lyft och huvudet pekande rakt uppåt i fönstret, dvs i negativ y-riktning  
  */
  Turtle(SimpleWindow w, int xHome, int yHome) 
  {
	  this.w = w;
	  x = xHome;
	  y = yHome;
	  alpha = 90;
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
	  w.moveTo(x, y);
	  y -= Math.round(n*Math.sin(Math.toRadians(alpha)));
	  x += Math.round(n*Math.cos(Math.toRadians(alpha)));
	  
	    if (isPenDown) {
		      w.lineTo(x, y);
	    }
  }

  /** vrider huvudet beta grader åt vänster */
  public void left(int beta) 
  {
	  alpha += beta;
	  if (alpha >= 360) {
		  alpha -= 360;
	  }
  }

  /** vrider hvudet beta grader åt höger */
  public void right(int beta) 
  {
	  alpha -= beta;
	  if (alpha <= 0) {
		  alpha += 360;
	  }
  }

  /** går till punkten newX,newY utan att rita. 
      Pennans läge och huvudets riktning påverkas inte */
  public void jumpTo(int newX, int newY) 
  {
	  x = newX;
	  y = newY;
	 //w.moveTo(x, y);
  }

  /** återställer huvudets riktning till den ursprungliga */
  public void turnNorth() 
  {
	  alpha = 90;
  }

  /** tar reda på sköldpaddans aktuella x-koordinat */
  public int getX() 
  {
	  return x;
  }

  /** tar reda på sköldpaddans aktuella y-koordinat */
  int getY() 
  {
	  return y;
  }
}
