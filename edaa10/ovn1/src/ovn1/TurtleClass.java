package ovn1;

public class TurtleClass 
{
	public static void main(String [] args) 
	{
		MyProgram myProgram = new MyProgram();
		myProgram.Run();	
		
		TrainArrival trainArrival = new TrainArrival();
		trainArrival.CalculateArrival();
		
		InData inData = new InData();
		inData.Compare();
	}
}
