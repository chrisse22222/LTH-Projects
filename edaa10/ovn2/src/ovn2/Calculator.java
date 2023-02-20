package ovn2;
import java.util.Scanner;

public class Calculator 
{
	public static void main (String[] args) 
	{
		double nbr1, nbr2, sum;
		System.out.println("Skriv två tal");
		Scanner scan = new Scanner(System.in);
		nbr1 = scan.nextDouble();
		nbr2 = scan.nextDouble();
		
		sum = nbr1 + nbr2;
		System.out.println("Summan av talen är " + sum);
		
		sum = nbr1 - nbr2;
		System.out.println("Skillnaden mellan talen är " + sum);
		
		sum = nbr1 * nbr2;
		System.out.println("Produkten av talen är " + sum);
		
		sum = nbr1 / nbr2;
		System.out.println("Kvoten av talen är " + sum);
	}
}
