package ovn3;

public class Main 
{
	public static void main(String [] args ) 
	{
		P3 p3 = new P3();
		p3.Start();
		System.out.println("Din totala summa �r " + p3.SumAdd());
		
		System.out.println("Antal termer som beh�vs " + p3.Terms());
		
		System.out.println("Antal tal i f�ljd " + p3.Counter());
		
		p3.Calculator();
		
		System.out.println("Antal g�nger " + p3.Input());
	}
}
