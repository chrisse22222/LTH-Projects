import java.io.*;


public class MainSimulation extends GlobalSimulation{
 
    public static void main(String[] args) throws IOException {
    	Event actEvent;
    	EventList eventList = new EventList();
    	State actState = new State(eventList);
        eventList.InsertEvent(ARRIVALA, 2/150.);
        eventList.InsertEvent(MEASURE, .1);

    	while (actState.noMeasurements < 1000){
    		actEvent = eventList.FetchEvent();
    		time = actEvent.eventTime;
    		actState.TreatEvent(actEvent);
    	}

    	System.out.println("Mean number of customers: " + 1.0 * actState.accumulated/actState.noMeasurements);
    	System.out.println("Number of measurements done: " + actState.noMeasurements);
    }
}