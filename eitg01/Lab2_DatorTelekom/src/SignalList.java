// Denna klass definierar signallistan. Om man vill skicka mer information i signalen än minimum, så kan
// man skriva ytterligare variante av SendSignal som innehåller fler parametrar.

public class SignalList{
	public static Signal list, last;

	SignalList(){
    	list = new Signal();
    	last = new Signal();
    	list.next = last;
	}

	public static void SendSignal(int type, Proc dest, double arrtime){
 		Signal dummy, predummy;
 		Signal newSignal = new Signal();
 		newSignal.signalType = type;
 		newSignal.destination = dest;
 		newSignal.arrivalTime = arrtime;
 		predummy = list;
 		dummy = list.next;

 		while ((dummy.arrivalTime < newSignal.arrivalTime) & (dummy != last)){
 			predummy = dummy;
 			dummy = dummy.next;
 		}
 		predummy.next = newSignal;
 		newSignal.next = dummy;
 	}

	public static Signal FetchSignal(){
		Signal dummy;
		dummy = list.next;
		list.next = dummy.next;
		dummy.next = null;
		return dummy;
	}
}
