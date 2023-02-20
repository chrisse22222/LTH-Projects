// Denna klass definerar vad som ska finnas i en signal. Det som finns här är ett minimum.
// Man kan lägga till mer om man vill att en signal ska kunna skicka mer information.

class Signal{
	public Proc destination;
	public double arrivalTime;
	public int signalType;
	public Signal next;
}
