import java.util.Scanner;

public class GuessNumber 
{
	private int counter, guess;
	private Scanner scan;
	
	public void Run() {
		
		scan = new Scanner(System.in);
		System.out.println("Skriv in tv� tal, ett min v�rde och ett max v�rde");
		
		NumberGenerator numberGen = new NumberGenerator(CheckIfInputIsLegit(scan.next()),CheckIfInputIsLegit(scan.next()) );
		numberGen.drawNbr();
		
		System.out.print("Jag t�nker p� ett tal mellan " + numberGen.getMin() + " till " + numberGen.getMax() + ", gissa vilket!");
		
		do {
			guess = CheckIfInputIsLegit(scan.next());
			
			if (numberGen.isBiggerThan(guess)) {
				System.out.println("Du gissade p� ett tal som var mindre �n det jag t�nkte p�, f�rs�k igen!");
			} 
			else if (!numberGen.isBiggerThan(guess) && !numberGen.isEqual(guess)) {
				System.out.println("Du gissade p� ett tal som var st�rre �n det jag t�nkte p�, f�rs�k igen!");
			}
			
			counter++;
			
		} while(!numberGen.isEqual(guess));

		System.out.println("Bra jobbat, du gissade r�tt och det tog dig " + counter + " f�rs�k");
		scan.close();
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
				System.out.println("Du skrev inte in ett nummer, f�rs�k igen!");
				input = scan.next();
			}
		}
		
		return number;
	}
}
