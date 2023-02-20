import se.lth.cs.window.SimpleWindow;

public class Square extends Shape {

	int alfa, length;
	public Square(int x, int y, int lenght) {
		super(x, y);
		this.length = lenght;
		alfa = 0;
	}

	public void draw(SimpleWindow w) {
		w.moveTo(x, y);
		
		for (int i = 0; i < 4; i++) {
			x += Math.round(length * Math.cos(Math.toRadians(alfa)));
			y -= Math.round(length * Math.sin(Math.toRadians(alfa)));
			w.lineTo(x, y);
			alfa += 90;
		}
		
		if (alfa >= 360) {
			alfa = 0;
		}
	}
}
