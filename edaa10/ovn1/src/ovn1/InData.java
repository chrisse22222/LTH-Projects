package ovn1;
import java.util.Random;

public class InData 
{
	int big, nextBig;
	public void Compare()
	{
		int [] myArray = new int [100];
		Random random = new Random();
		
		big = 0;
		nextBig = 0;
		
		for (int i = 0; i < myArray.length; ++i) 
		{
			myArray[i] = random.nextInt(1001);
			
			if (myArray[i] > big) 
			{
				nextBig = big;
				big = myArray[i];
			}
			else if (nextBig < myArray[i]) 
			{
				nextBig = myArray[i];
			}
		}
		
		System.out.println("Största värde: " + big + ", näst störtsta: " + nextBig);
		System.out.println("Skillnad:" + (big - nextBig));
	}
}
