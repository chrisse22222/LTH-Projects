
public class Main 
{
	public static void main (String [] args) 
	{
		Dialog dialog = new Dialog();
		Register reg = new Register();
		reg.readFromFile();
		
		boolean continueProgram = true;
		while (continueProgram) 
		{
			int choice = dialog.readInt
			("Välj ett av alternativen:" + "\n"  + "Tryck 1 för att lägga till en ny bok" + "\n" + 
			"Tryck 2 för att ta bort alla böcker av en viss författare" + "\n" + 
			"Tryck 3 för att söka upp författares böcker" + "\n" + 
			"Tryck 4 för att söka efter titlar" + "\n" + 
			"Tryck 5 för att visa alla böcker sorterade efter författare" + "\n" +
			"Tryck 6 för att visa alla böcker sorterade efter titel" + "\n" + 
			"Tryck 7 för att avsluta programmet");
					
					switch(choice)
					{
						case 1:
							dialog.printString(reg.addBook(dialog.readString("Skriv in författarens namn"), dialog.readString("Skriv in bokens titel")));
							break;
							
						case 2:
							dialog.printString(reg.removeBook(dialog.readString("Skriv in författarens namn")));
							break;
							
						case 3:
							dialog.printString(reg.searchByAuthor(dialog.readString("Skriv in författarens namn")));
							break;
							
						case 4:
							dialog.printString(reg.searchByTitle(dialog.readString("Skriv in titelns namn")));
							break;	
							
						case 5:
							dialog.printString(reg.sortedByAuthor()); 
							break;
							
						case 6:
							dialog.printString(reg.sortedByTitle());
							break;
							
						case 7:
							continueProgram = false;
							break;
							
						default:
							dialog.printString("Error, du valde ett alternativ som inte fanns");
							break;
					}
		}
		
		reg.writeToFile();
	}
}
