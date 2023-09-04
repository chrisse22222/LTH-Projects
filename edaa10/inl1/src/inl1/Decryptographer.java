package inl1;
import se.lth.cs.p.inl1.*;
import se.lth.cs.p.inl1.nbr14.*;

public class Decryptographer 
{
	private int start;
	private Key key;
	
	public Decryptographer(Key key) 
	{
		this.key = key;
		start = key.getStart();
	}
	
	public String decrypt(String text) 
	{
		StringBuilder builder = new StringBuilder();

		int index, count;
		count = 0;
		for (int i = 0; i < text.length(); i++) 
		{
			if (text.charAt(i) != ' ') 
			{
				index = text.charAt(i) - (start + count);
				while (index < 'A') 
				{
					index +=26; // "Lägger till en period"
				}
				
				for (int j = 0; j < 26; j++) 
				{
					if (key.getLetter(j) == (char)index) 
					{
						builder.append((char)(j + 'A'));
						break;
					}
				}
				
				count++;
			}
			else 
			{
				builder.append(" ");
			}
		}
		
		return builder.toString();
	}
}