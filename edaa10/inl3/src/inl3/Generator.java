package inl3;

import java.awt.Color;
import java.lang.invoke.SwitchPoint;

public class Generator {
	private int pixelSize = 1;
	private int MAX_ITER = 200;
	private Color [] colorScale;
	
	public Generator() {
		colorScale = new Color[MAX_ITER];
		for (int i = 0; i < colorScale.length; i++) {
			// Desto n�rmre den kommer antalter iterationer
			// Desto ljusare blir den
			colorScale[i] = new Color(i, i, i + 15);
		}
	}
	
	/** Ritar en bild i f�nstret i anv�ndargr�nssnittet gui */
	public void render(MandelbrotGUI gui) {
		gui.disableInput();
		
		Complex[][] complex = mesh(gui.getMinimumReal(), gui.getMaximumReal(), 
				   gui.getMinimumImag(), gui.getMaximumImag(), 
				   gui.getWidth(), gui.getHeight());
		
		switch (gui.getResolution()) {
			case MandelbrotGUI.RESOLUTION_VERY_HIGH:
				pixelSize = 1;
				break;
			case MandelbrotGUI.RESOLUTION_HIGH:
				pixelSize = 3;
				break;
			case MandelbrotGUI.RESOLUTION_MEDIUM:
				pixelSize = 5;
				break;
			case MandelbrotGUI.RESOLUTION_LOW:
				pixelSize = 7;
				break;
			case MandelbrotGUI.RESOLUTION_VERY_LOW:
				pixelSize = 9;
				break;
				
			default:
				break;
		}
		
		Color[][] picture = new Color[gui.getHeight() / pixelSize][gui.getWidth() / pixelSize];
		int count = 0;
		
		for (int i = 0; i < complex.length / pixelSize; i++) {
			for (int j = 0; j < complex[0].length / pixelSize; j++) {
				Complex c = complex[i * pixelSize + pixelSize / 2][j * pixelSize + pixelSize / 2];
				Complex z = new Complex(0, 0);
				
				// F�r abs i kvadrat, alts� 2^2 = 4,
				// s� l�nge abs �r mindre �n 2 (2^2), divergerar inte mandelbrotf�ljden
				while (count < MAX_ITER && z.getAbs2() <= 4) {
					count++;
					z.mul(z);
					z.add(c);
				}
		
				switch (gui.getMode()) {
					case MandelbrotGUI.MODE_COLOR:
						if (count < MAX_ITER) {
							picture[i][j] = colorScale[count];
						} else {
							picture[i][j] = Color.ORANGE;
						}
					
						break;
					case MandelbrotGUI.MODE_BW:
						if (z.getAbs2() <= 4) {
							picture[i][j] = Color.black;
						} else {
							picture[i][j] = Color.white;
						}
						break;

					default:
						break;
				}
				
				count = 0;
			}
		}
		
		gui.putData(picture, pixelSize, pixelSize);
		gui.enableInput();
	}
	
	/** Skapar en matris d�r varje element �r ett komplext tal som
	 * har r�tt koordinater */
	private Complex[][] mesh (double minRe, double maxRe, double minIm, double maxIm, int width, int height) {
		Complex[][] complex = new Complex[height][width];
		
		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				// �kar n�r vi g�r fr�n v�nster till h�ger i det komplexa talplanet, beoende av j (l�ngden)
				double re = minRe + (maxRe - minRe) / (width - 1) * j; 
				// Minskas n�r vi g�r fr�n upp till ner i det komplexa talplanet, beroende av i (h�jden)
				double im = maxIm - (maxIm -minIm) / (height - 1) * i;
				complex[i][j] = new Complex(re, im);
			}
		}
		
		return complex;
	}
}
