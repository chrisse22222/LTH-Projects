package inl3;

public class Mandelbrot {
	public static void main (String [] args) {
		MandelbrotGUI gui = new MandelbrotGUI();
		Generator gen = new Generator();
		
		boolean isRendered = false;
		
		while (true) {
			switch (gui.getCommand()) {
			case MandelbrotGUI.QUIT:
				System.exit(0);
				break;
			case MandelbrotGUI.RESET:
				gui.resetPlane();
				gui.clearPlane();
				isRendered = false;
				break;
			case MandelbrotGUI.RENDER:
				gen.render(gui);
				isRendered = true;
			case MandelbrotGUI.ZOOM:
				if (isRendered) {
					gen.render(gui);
				} else {
					gui.resetPlane();
				}
				break;
			
			default:
				break;
			}
		}
	}
}
