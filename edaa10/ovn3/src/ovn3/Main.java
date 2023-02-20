package ovn3;

public class Main 
{
	public static void main(String [] args ) 
	{
		P3 p3 = new P3();
		p3.Start();
		System.out.println("Din totala summa är " + p3.SumAdd());
		
		System.out.println("Antal termer som behövs " + p3.Terms());
		
		System.out.println("Antal tal i följd " + p3.Counter());
		
		p3.Calculator();
		
		System.out.println("Antal gånger " + p3.Input());
	}
}
