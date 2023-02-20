package inl1;
import se.lth.cs.p.inl1.*;
import se.lth.cs.p.inl1.nbr14.*;

public class MyProgram 
{
	public static void main(String [] args) 
	{
		Key key = new Key();
		TestCase test = new TestCase();
		TextWindow tW = new TextWindow("Krypteringsfönster");
		
		TextView [] tV = new TextView[3];
		tV[0] = new TextView("Chiffertext", 5, 100);
		tV[1] = new TextView("Min klartext", 5, 100);
		tV[2] = new TextView("Korrekt klartext", 5, 100);
		
		for (int i = 0; i < tV.length; i++) {
			tW.addView(tV[i]);
		}
		
		Decryptographer decrypt = new Decryptographer(key);
		for (int i = 1; i <= 5; i++) 
		{
			tV[0].displayText(test.getCryptoText(i));
			tV[1].displayText(decrypt.decrypt(test.getCryptoText(i)));
			tV[2].displayText(test.getClearText(i));
			
			tW.waitForMouseClick();
		}
	}
}
