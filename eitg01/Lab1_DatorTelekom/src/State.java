import java.util.*;
class State extends GlobalSimulation{
	
	public int numberInQueue = 0, accumulated = 0, noMeasurements = 0;
	
	private EventList myEventList;

	private Random slump = new Random();
	private int bPackageCount;
	
	public State(EventList x){
		myEventList = x;
	}

	private void InsertEvent(int event, double timeOfEvent){
		myEventList.InsertEvent(event, timeOfEvent);
	}

	public void TreatEvent(Event x){
		switch (x.eventType){
			case ARRIVALA:
				arrivalA();
				break;
			case ARRIVALB:
				arrivalB();
				break;
			case READY:
				ready();
				break;
			case MEASURE:
				measure();
				break;
			case CONNECTION:
				establishConnection();
				break;
		}
	}
	
	private double generateMean(double mean){
		return mean * slump.nextDouble();
	}
	
	private void arrivalA(){
		if (numberInQueue == 0)
			InsertEvent(READY, time + 0.002);

		numberInQueue++;
		InsertEvent(ARRIVALA, time + generateMean(2/150.));
	}

	private void arrivalB(){
		if (numberInQueue == 0){
			InsertEvent(READY, time + 0.004);
		}

		numberInQueue++;
	}

	private void ready(){
		if (bPackageCount == 0){
			InsertEvent(CONNECTION, time + 1);
		}

		numberInQueue--;
		if (numberInQueue > 0){
			if (bPackageCount > 0){
				InsertEvent(READY, time + 0.004);
				bPackageCount--;
			} else{
				InsertEvent(READY, time + 0.002);
			}
		}
	}
	
	private void measure(){
		accumulated += numberInQueue;
		noMeasurements++;
		InsertEvent(MEASURE, time + 0.1);
	}

	// Skapa b paket och sätt in först i kön
	private void establishConnection(){
		InsertEvent(ARRIVALB, time);
		bPackageCount++;
	}
}