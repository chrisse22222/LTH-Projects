package ovn8;
import java.awt.Color;

import se.lth.cs.window.SimpleWindow;

public class TurtleRace 
{
	public static void main (String [] args) 
	{
		SimpleWindow w = new SimpleWindow(1000, 900, "TurtleRace");
		RaceTrack track = new RaceTrack(850, 50);
		track.draw(w);
		
		Turtle t1 = new Turtle(w, 0, 0);
		t1.SetColor(Color.green);
		Turtle t2 = new Turtle(w, 0, 0);
		t2.SetColor(Color.red);
		w.setLineWidth(20);
		
		w.waitForMouseClick();
		RacingEvent race = new RacingEvent(track, t1, t2);
		System.out.println(race.Race(w));
	}
}
