import se.lth.cs.window.SimpleWindow;

public class Triangle extends Shape {

	int alfa, length;
	public Triangle(int x, int y, int length) {
		super(x, y);
		this.length = length;
		alfa = 0;
	}

	public void draw(SimpleWindow w) {
		w.moveTo(x, y);
		
		for (int i = 0; i < 3; i++) {
			x += Math.round(length * Math.cos(Math.toRadians(alfa)));
			y -= Math.round(length * Math.sin(Math.toRadians(alfa)));
			w.lineTo(x, y);
			alfa += 120;
		}
		
		if (alfa >= 360) {
			alfa = 0;
		}
	}

}
