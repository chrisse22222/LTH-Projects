import java.util.*;
import java.io.*;

//Denna klass ärver Global så att man kan använda time och signalnamnen utan punktnotation


public class MainSimulation extends Global{

    public static void main(String[] args) throws IOException {

    	//Signallistan startas och actSignal deklareras. actSignal är den senast utplockade signalen i huvudloopen nedan.

    	Signal actSignal;
    	new SignalList();
    	Random random = new Random();

    	//Här nedan skapas de processinstanser som behövs och parametrar i dem ges värden.

		ArrayList<QS> queue = new ArrayList<>();
		for (int i = 0; i < 5; i++) {
			queue.add(new QS());
		}

    	Gen Generator = new Gen();
		Generator.lambda = 45; //Generator ska generera nio kunder per sekund
		//Generator.sendTo = Q1; //De genererade kunderna ska skickas till kösystemet QS

    	//Här nedan skickas de första signalerna för att simuleringen ska komma igång.

    	SignalList.SendSignal(READY, Generator, time);
		for (int i = 0; i < queue.size(); i++) {
			SignalList.SendSignal(MEASURE, queue.get(i), time);
		}

    	// Detta är simuleringsloopen:

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
			System.out.println("Medelantal kunder i kösystem: " + 1.0*queue.get(i).accumulated/queue.get(i).noMeasurements);
		}
    }
}