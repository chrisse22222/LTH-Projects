import java.util.Scanner;

public class GuessNumber 
{
	private int counter, guess;
	private Scanner scan;
	
	public void Run() {
		
		scan = new Scanner(System.in);
		System.out.println("Skriv in två tal, ett min värde och ett max värde");
		
		NumberGenerator numberGen = new NumberGenerator(CheckIfInputIsLegit(scan.next()),CheckIfInputIsLegit(scan.next()) );
		numberGen.drawNbr();
		
		System.out.print("Jag tänker på ett tal mellan " + numberGen.getMin() + " till " + numberGen.getMax() + ", gissa vilket!");
		
		do {
			guess = CheckIfInputIsLegit(scan.next());
			
			if (numberGen.isBiggerThan(guess)) {
				System.out.println("Du gissade på ett tal som var mindre än det jag tänkte på, försök igen!");
			} 
			else if (!numberGen.isBiggerThan(guess) && !numberGen.isEqual(guess)) {
				System.out.println("Du gissade på ett tal som var större än det jag tänkte på, försök igen!");
			}
			
			counter++;
			
		} while(!numberGen.isEqual(guess));

		System.out.println("Bra jobbat, du gissade rätt och det tog dig " + counter + " försök");
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
				System.out.println("Du skrev inte in ett nummer, försök igen!");
				input = scan.next();
			}
		}
		
		return number;
	}
}
