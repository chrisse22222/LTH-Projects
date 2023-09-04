package ovn8;
import java.util.Random;
import se.lth.cs.window.SimpleWindow;

public class RacingEvent 
{
	Random random;
	RaceTrack track;
	Turtle t1, t2;
	public RacingEvent (RaceTrack track, Turtle t1, Turtle t2)
	{
		this.t1 = t1;
		this.t2 = t2;
		this.track = track;
		
		random = new Random();
	}
	
	/** Hanterar racet mellan de två sköldpaddorna */
	public String Race(SimpleWindow w) 
	{
		t1.jumpTo(250, track.TrackBegin());
		t1.penDown();
		t2.jumpTo(750, track.TrackBegin());
		t2.penDown();
		
		while (t1.getY() > track.TrackEnd() && t2.getY() > track.TrackEnd())
		{
			t1.forward(random.nextInt(3));
			t2.forward(random.nextInt(3));
			w.delay(5);
		}
		
		if (t1.getY() <= track.TrackEnd() && t2.getY() > track.TrackEnd()) 
		{
			return "Den gröna sköldpaddan vann!";
		}
		else if (t2.getY() <= track.TrackEnd() && t1.getY() > track.TrackEnd()) 
		{
			return "Den röda sköldpaddan vann!";
		}
		else 
		{
			return "Oavgjort, ingen vann!";
		}
	}
}
