import se.lth.cs.window.SimpleWindow;
import se.lth.cs.util.*;
import java.awt.Color;

/** Testprogram för att kolla att installationen av hjälpklasserna
    fungerar, CLASSPATH är rätt, osv */

public class HelpTest {
  public static void main(String[] args) {
    System.out.println("Detta program testar att klasserna SimpleWindow,");
    System.out.println("SimpleRandom och SimpleInput är korrekt installerade");
    System.out.println("Programmet ritar ett antal linjer med slumpmässiga" +
		       " färger");
    System.out.println();

    Color[] colors = new Color[13];
    colors[0]  = Color.black;
    colors[1]  = Color.white;
    colors[2]  = Color.blue;
    colors[3]  = Color.cyan;
    colors[4]  = Color.darkGray;
    colors[5]  = Color.gray;
    colors[6]  = Color.green;
    colors[7]  = Color.lightGray;
    colors[8]  = Color.magenta;
    colors[9]  = Color.orange;
    colors[10] = Color.pink;
    colors[11] = Color.red;
    colors[12] = Color.yellow;

    System.out.print("Antal linjer: ");
    System.out.flush();
    int nbrLines = SimpleInput.readInt();

    SimpleWindow w = new SimpleWindow(600,600,"HelpTest");
    w.moveTo(0,0);
    w.setLineWidth(10);
    for (int k = 1; k<=nbrLines; k++) {
      int colNbr = SimpleRandom.randInt(0,colors.length-1);
      w.setLineColor(colors[colNbr]);
      int newX = SimpleRandom.randInt(0,w.getWidth()-1);
      int newY = SimpleRandom.randInt(0,w.getHeight()-1);
      w.lineTo(newX,newY);
      SimpleWindow.delay(5);
    }
    System.out.println("*** Slutritat ***");
  }
}
