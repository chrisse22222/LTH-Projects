import java.util.*;
import java.io.*;

//Denna klass ärver Proc, det gör att man kan använda time och signalnamn utan punktnotation

class Gen extends Proc{

	//Slumptalsgeneratorn startas:
	Random slump = new Random();

	//Generatorn har två parametrar:
	public Proc sendTo;    //Anger till vilken process de genererade kunderna ska skickas
	public double lambda;  //Hur många per sekund som ska generas

	//Här nedan anger man vad som ska göras när en signal kommer
	public void TreatSignal(Signal x){
		switch (x.signalType){
			case READY:{
				SignalList.SendSignal(ARRIVAL, sendTo, time);
				SignalList.SendSignal(READY, this, time + (2.0/lambda)*slump.nextDouble());}
				break;
		}
	}
}