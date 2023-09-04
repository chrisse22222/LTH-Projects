import java.util.*;
import java.io.*;

//Denna klass �rver Global s� att man kan anv�nda time och signalnamnen utan punktnotation


public class MainSimulation extends Global{

    public static void main(String[] args) throws IOException {

    	//Signallistan startas och actSignal deklareras. actSignal �r den senast utplockade signalen i huvudloopen nedan.

    	Signal actSignal;
    	new SignalList();
    	Random random = new Random();

    	//H�r nedan skapas de processinstanser som beh�vs och parametrar i dem ges v�rden.

		ArrayList<QS> queue = new ArrayList<>();
		for (int i = 0; i < 5; i++) {
			queue.add(new QS());
		}

    	Gen Generator = new Gen();
		Generator.lambda = 45; //Generator ska generera nio kunder per sekund
		//Generator.sendTo = Q1; //De genererade kunderna ska skickas till k�systemet QS

    	//H�r nedan skickas de f�rsta signalerna f�r att simuleringen ska komma ig�ng.

    	SignalList.SendSignal(READY, Generator, time);
		for (int i = 0; i < queue.size(); i++) {
			SignalList.SendSignal(MEASURE, queue.get(i), time);
		}

    	// Detta �r simuleringsloopen:

		Generator.sendTo = queue.get(0);
		int temp = 0;
    	while (time < 100000){
    		actSignal = SignalList.FetchSignal();
    		if (actSignal.signalType == ARRIVAL){
				//queue.sort(Comparator.comparingInt(x -> x.numberInQueue));
				Generator.sendTo = queue.get(temp);
				temp++;
				temp %= queue.size();
			}

    		time = actSignal.arrivalTime;
    		actSignal.destination.TreatSignal(actSignal);
    	}

    	//Slutligen skrivs resultatet av simuleringen ut nedan:
		for (int i = 0; i < queue.size(); i++) {
			System.out.println("Medelantal kunder i k�system: " + 1.0*queue.get(i).accumulated/queue.get(i).noMeasurements);
		}
    }
}