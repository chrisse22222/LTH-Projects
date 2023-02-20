import javax.swing.*;
public class Dialog {

	/** Skapar ett Dialog-objekt för hantering av popup-dialogrutor. */
	public Dialog() {
	}

	/** Visar en dialogruta med hjälptexten s där användaren skall mata in ett 
	  * heltal som returneras.
	  * Om användaren anger ett felaktigt värde eller klickar på "avbryt" så 
	  * returneras Integer.MAX_VALUE.
	  */
	public int readInt(String s) {
		String in = JOptionPane.showInputDialog(s);
		try {
			int i = Integer.parseInt(in);
			return i;
		} catch (NumberFormatException e) {
			return Integer.MAX_VALUE;
		}
	}

	/** Visar en dialogruta med hjälptexten s där användaren skall mata in en 
	  * teckensträng som returneras.
	  * Om användaren klickar på "avbryt" så returneras null.
	  */
	public String readString(String s) {
		String in = JOptionPane.showInputDialog(s);
		if (in == null) {
			return null;
		} else {
			return in.trim();
		}
	}

	/** Visar en dialogruta med textsträngen s. */
	public void printString(String s) {
	   JOptionPane.showMessageDialog(null, s, "",JOptionPane.PLAIN_MESSAGE);
	}
}
