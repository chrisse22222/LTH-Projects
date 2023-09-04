package ovn1;
import java.util.Scanner;

public class TrainArrival 
{
	Scanner scanner;
	int departureH, departureM, travelTimeH, travelTimeM;
	int arrivalH, arrivalM, days;
	public void CalculateArrival() 
	{
		scanner = new Scanner(System.in);
		System.out.println("Ange avgånstid i Timmar och minuter och körtid i timmar och minuter");
		
		departureH = CheckIfInputIsLegit(scanner.next());
		departureM = CheckIfInputIsLegit(scanner.next()); 
		travelTimeH = CheckIfInputIsLegit(scanner.next());
		travelTimeM = CheckIfInputIsLegit(scanner.next());
		
		arrivalM = (departureM + travelTimeM) % 60;
		arrivalH = (departureM + travelTimeM) / 60 + (departureH + travelTimeH);
		while (arrivalH >= 24) 
		{
			arrivalH -= 24;
			days ++;
		}
		
		if (days == 0)
		{
			if (arrivalM == 0) 
			{
				System.out.println("Du kommer anlända idag klockan: " + arrivalH + ":00\n");
			}
			else 
			{
				System.out.println("Du kommer anlända idag klockan: " + arrivalH +":" + arrivalM + "\n");
			}
		}
		else 
		{
			if (arrivalM == 0) 
			{
				System.out.println("Du kommer anlända om " + days + " dag:ar klockan: " + arrivalH + ":00\n");
			}
			else 
			{
				System.out.println("Du kommer anlända om " + days + " dag:ar klockan: " + arrivalH + ":" + arrivalM + "\n");
			}
		}
		
		scanner.nextLine();
	}
	
	private int CheckIfInputIsLegit(String input) 
	{
		boolean isItDone = false;
		int number = 0;
		while (!isItDone) 
		{
			isItDone = true;
			try 
			{
				number = Integer.parseInt(input);
			}
			catch(NumberFormatException ex)
			{
				isItDone = false;
				System.out.println("Du skrev inte i ett nummer, försök igen!");
				input = scanner.next();
			}
		}
		
		return number;
	}
}
