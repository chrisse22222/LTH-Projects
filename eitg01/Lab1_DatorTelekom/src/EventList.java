public class EventList{
	
	private Event head;
	
	public EventList(){
		head = new Event();
    	head.next = new Event();
	}

	/** Sorterar den länkade listan i stigande ordning (eventet som tar kortast tid först) */
	public void InsertEvent(int type, double TimeOfEvent){
 		Event dummy, predummy;
 		Event newEvent = new Event();
 		newEvent.eventType = type;
 		newEvent.eventTime = TimeOfEvent;


 		predummy = head;
 		dummy = head.next;

 		while ((dummy.next != null) && (dummy.eventTime < newEvent.eventTime)){
 			predummy = dummy;
 			dummy = dummy.next;
 		}

 		predummy.next = newEvent;
 		newEvent.next = dummy;
 }

 	/** Returnerar nästa element i listan (nästa event)*/
	public Event FetchEvent(){
		Event dummy;
		dummy = head.next;
		head.next = dummy.next;
		dummy.next = null;
		return dummy;
	}
}
