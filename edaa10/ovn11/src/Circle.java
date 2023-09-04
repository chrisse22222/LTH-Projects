import se.lth.cs.window.SimpleWindow;

public class Circle extends Shape {

	private int radius;
	public Circle(int x, int y, int radius) {
		super(x, y);
		this.radius = radius;
	}

	public void draw(SimpleWindow w) {
		w.moveTo(x + radius, y);
		
		double alfa;
		int xCord, yCord;
		for (int i = 0; i <= 360; i++) {
			alfa = Math.toRadians(1) * i;
			xCord = (int) Math.round(x + Math.cos(alfa) * radius);
			yCord = (int) Math.round(y + Math.sin(alfa) * radius);
			
			w.lineTo(xCord, yCord);
		}
	}

}
