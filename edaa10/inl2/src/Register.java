import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class Register 
{
	private ArrayList<Book> reg;
	
	public Register() 
	{
		reg = new ArrayList<Book>();
	}
	
	private Boolean controller(String author, String title) 
	{
		if (author == null || title == null) 
		{
			return false;
		}
		
		if (author.compareTo("") == 0 || title.compareTo("") == 0) 
		{
			return false;
		}
		
		return true;
	}
	
	public String addBook(String author, String title) 
	{
		if (!controller(author, title)) 
		{
			return "Error, du skrev inte in n�gon titel eller f�rfattare, f�rs�k igen!";
		}
		
		Book book = new Book (author, title);
		
		for (int i = 0; i < reg.size(); i++) 
		{
			if (reg.get(i).toString().compareTo(book.toString()) == 0) 
			{
				return "Denna boken finns redan i v�rt register.";
			}
		}

		reg.add(book);
		sortBooks();
		return "En ny bok har lagts till i v�rt register.";
	}
	
	public String removeBook(String author) 
	{	
		if (!controller(author, "N�GOT")) 
		{
			return "Error, du skrev inte in n�gon titel eller f�rfattare, f�rs�k igen!";
		}
		
		boolean hasFoundAuthor = false;
		for (int i = 0; i < reg.size(); i++) 
		{
			if (reg.get(i).getAuthor().toLowerCase().compareTo(author.toLowerCase()) == 0) 
			{
				hasFoundAuthor = true;
				reg.remove(i);
				i = -1;
			}
		}
		
		sortBooks();
			
		if (hasFoundAuthor) 
		{
			return "Alla b�ckerna av f�rfattaren har tagits bort fr�n v�rt register"; 
		}
		else 
		{
			return "Hittade inte den angivna f�rfattaren i v�rt register";
		}
	}
	
	private void sortBooks() 
	{
		for (int i = 0; i < reg.size(); i++) 
		{
			for (int j = i + 1; j < reg.size(); j++) 
			{
				if (reg.get(i).getAuthor().toLowerCase().compareTo(reg.get(j).getAuthor().toLowerCase()) > 0) 
				{
					Book placeHolder = reg.get(j);
					reg.set(j, reg.get(i));
					reg.set(i, placeHolder);
				}
			}
		}
	}
	
	public String searchByAuthor(String author) 
	{
		if (!controller(author, "N�GOT")) 
		{
			return "Error, du skrev inte in n�gon titel eller f�rfattare, f�rs�k igen!";
		}
		
		StringBuilder sB = new StringBuilder();
		for (int i = 0; i < reg.size(); i++) 
		{
			if (reg.get(i).getAuthor().toLowerCase().contains(author.toLowerCase())) 
			{
				sB.append(reg.get(i) + "\n");
			}
		}
		
		if (sB.length() == 0) 
		{
			sB.append("Vi kunde inte hitta n�got i v�rt register som matchar f�rfattarens namn, f�rs�k igen");
		}
		
		return sB.toString();
	}
	
	public String searchByTitle(String title) 
	{
		if (!controller("N�GOT", title)) 
		{
			return "Error, du skrev inte in n�gon titel eller f�rfattare, f�rs�k igen!";
		}
		
		StringBuilder sB = new StringBuilder();
		for (int i = 0; i < reg.size(); i++) 
		{
			if (reg.get(i).title().toLowerCase().contains(title.toLowerCase())) 
			{
				sB.append(reg.get(i));
				sB.append(System.getProperty("line.separator"));
			}
		}
		
		if (sB.length() == 0) 
		{
			sB.append("Vi kunde inte hitta n�got i v�rt register som matchar titelns namn, f�rs�k igen");
		}
		
		return sB.toString();
	}
	
	public String sortedByAuthor() 
	{
		StringBuilder sB = new StringBuilder();
		for (int i = 0; i < reg.size(); i++) 
		{
			sB.append(reg.get(i));
			sB.append(System.getProperty("line.separator"));
		}
		
		return sB.toString();
	}
	
	public String sortedByTitle() 
	{
		StringBuilder sB = new StringBuilder();
		for (int i = 0; i < reg.size(); i++) 
		{
			for (int j = i + 1; j < reg.size(); j++) 
			{
				if (reg.get(i).title().toLowerCase().compareTo(reg.get(j).title().toLowerCase()) > 0) 
				{
					Book placeHolder = reg.get(j);
					reg.set(j, reg.get(i));
					reg.set(i, placeHolder);
				}
			}
		}
		
		for (int i = 0; i < reg.size(); i++) 
		{
			sB.append(reg.get(i));
			sB.append(System.getProperty("line.separator"));
		}
		
		sortBooks();
		return sB.toString();
	}
	
	public void readFromFile() 
	{
		Scanner scan = null;
		try 
		{
			scan = new Scanner(new File("data.txt"));
			
	        while(scan.hasNextLine())
	        {
	        	String author = scan.nextLine();
	        	author.replace(" ", "");
	        	String title = scan.nextLine();
	        	title.replace(" ", "");
	        	
	        	Book book = new Book(author, title);
	        	reg.add(book);
	        }
		} 
		catch (FileNotFoundException e) 
		{
			System.err.println("Filen kunde inte �ppnas");
			System.exit(1);
		}
		
		sortBooks();
	}
	
	public void writeToFile() 
	{
		PrintWriter out = null;
		try 
		{
			out = new PrintWriter(new File("data.txt"));
		} 
		catch (FileNotFoundException e) 
		{
			System.err.println("Filen kunde inte �ppnas");
			System.exit(1);
		}
		
		
		StringBuilder sB = new StringBuilder();
		for (int i = 0; i < reg.size(); i++) 
		{
			sB.append(reg.get(i).getAuthor());
			sB.append(System.getProperty("line.separator"));
			sB.append(reg.get(i).title());
			sB.append(System.getProperty("line.separator"));
		}
		
		out.print(sB.toString());
		out.close();
	}
}
