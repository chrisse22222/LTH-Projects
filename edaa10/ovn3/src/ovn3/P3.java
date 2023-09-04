package ovn3;
import java.util.ArrayList;
import java.util.Scanner;

public class P3 
{
	private Scanner scan;
	public void Start() 
	{
		scan = new Scanner(System.in);
	}
	
	public int SumAdd() 
	{
		int sum = 0;
		for (int i = 1; i < 100; i++) 
		{
			sum += i;
		}
		
		return sum;
	}
	
	public int Terms() 
	{
		int count, sum, add;
		count = 0;
		sum = 0;
		add = 1;
		
		while (sum < 100000) 
		{
			sum += add;
			add += 2;
			count++;
		}
		
		return count; 
	}
	
	public int Counter() 
	{
		boolean isItDone = false;
		int count = 0;
		
		while (!isItDone) 
		{
			int input = scan.nextInt();
			if (input > 0) 
			{
				count++;
			}
			else if (input == 0)
			{
				isItDone = true;
			}
		}
		
		return count;
	}
	
	public void Calculator() 
	{
		ArrayList<Double> myList = new ArrayList<Double>();
		while (true) 
		{
			if (scan.hasNextDouble()) 
			{
				myList.add(scan.nextDouble());
			}
			else 
			{
				break;
			}
		}
		
		double sum = 0;
		double avgPow = 0;
		for (int i = 0; i < myList.size(); i++) 
		{
			sum += myList.get(i);
			avgPow += Math.pow(myList.get(i), 2);
		}
		
		double avg = sum / myList.size();
		System.out.println("Medelvärdet är " + avg);
		
		if (myList.size() <= 5) 
		{
			System.out.println("För få tal för standardavvikelsen");
		}
		else 
		{
			double s = Math.sqrt((avgPow - myList.size() * Math.pow(avg, 2)) / (myList.size() - 1));
			System.out.println("Standardavvikelse " + s);
		}
		
	}

	public int Input() 
	{
		scan.nextLine();
		
		boolean isItDone = false;
		ArrayList<Integer> myList = new ArrayList<Integer>();
		
		while (!isItDone) 
		{
			int input = scan.nextInt();
			if (input == 0) 
			{
				isItDone = true;
			}
			else if (input > 0)
			{
				myList.add(input);
			}
			
		}
		
		int count = 0;
		for (int i = 0; i < myList.size() - 1; i++) 
		{
			if (myList.get(i) == myList.get((i + 1))) 
			{
				count++;
			}
		}
		
		return count;
	}
}
