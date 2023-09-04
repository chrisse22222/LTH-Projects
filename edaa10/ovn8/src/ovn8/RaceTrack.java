package ovn8;
import java.awt.Color;

import se.lth.cs.window.SimpleWindow;

public class RaceTrack 
{
	private int yStart, yFinnish;
	public RaceTrack(int yStart, int yFinnish)
	{
		this.yStart = yStart;
		this.yFinnish = yFinnish;
	}
	
	/** Skapar banan till racet */
	public void draw(SimpleWindow w) 
	{	
		w.setLineWidth(100);
		w.setLineColor(Color.DARK_GRAY);
		
		w.moveTo(0, yStart);
		w.lineTo(w.getWidth(), yStart);
		
		w.moveTo(0, yFinnish);
		w.lineTo(w.getWidth(), yFinnish);
		yFinnish += 50;
		yStart -= 50;
	}
	
	public int TrackEnd() 
	{
		return yFinnish;
	}
	
	public int TrackBegin() 
	{
		return yStart;
	}
}
