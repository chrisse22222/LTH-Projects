import se.lth.cs.window.SimpleWindow;

public class ShapeListTest {
	public static void main(String[] args) {
	    	SimpleWindow w = new SimpleWindow(600, 600, "ShapeListTest");
	    	ShapeList shapes = new ShapeList();
	    	shapes.insert(new Square(100, 300, 100));
	    	shapes.insert(new Triangle(400, 200, 100));
	    	shapes.insert(new Circle(400, 400, 50));
	    	shapes.insert(new Square(450, 450, 50));
	    	shapes.insert(new Square(200, 200, 35));
	    	shapes.draw(w);
	    	
	    	int x, y;
	    	while (true) {
	    		w.waitForMouseClick();
	    		x = w.getMouseX();
	    		y = w.getMouseY();
	    		
	    		Shape s = shapes.findHit(x, y);
	    		if (s != null) {
	    			w.waitForMouseClick();
	    			x = w.getMouseX();
	    			y = w.getMouseY();
	    			
	    			s.moveToAndDraw(w, x, y);
	    		}
	    	}
	}
}