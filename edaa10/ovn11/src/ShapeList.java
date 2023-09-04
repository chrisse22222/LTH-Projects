import java.util.ArrayList;
import se.lth.cs.window.SimpleWindow;

public class ShapeList 
{
	ArrayList <Shape> shapeList;
	
	public ShapeList() {
		shapeList = new ArrayList<Shape>();
	}
	
	public void insert(Shape s) {
		shapeList.add(s);
	}
	
	public void draw(SimpleWindow w) {
		for (int i = 0; i < shapeList.size(); i++) {
			shapeList.get(i).draw(w);
		}
	}
	
	public Shape findHit(int xc, int yc) {
		for (int i = 0; i < shapeList.size(); i++) {
			if (shapeList.get(i).near(xc, yc)) {
				return shapeList.get(i);
			}
		}
		
		return null;
	}
}
