
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
			("V�lj ett av alternativen:" + "\n"  + "Tryck 1 f�r att l�gga till en ny bok" + "\n" + 
			"Tryck 2 f�r att ta bort alla b�cker av en viss f�rfattare" + "\n" + 
			"Tryck 3 f�r att s�ka upp f�rfattares b�cker" + "\n" + 
			"Tryck 4 f�r att s�ka efter titlar" + "\n" + 
			"Tryck 5 f�r att visa alla b�cker sorterade efter f�rfattare" + "\n" +
			"Tryck 6 f�r att visa alla b�cker sorterade efter titel" + "\n" + 
			"Tryck 7 f�r att avsluta programmet");
					
					switch(choice)
					{
						case 1:
							dialog.printString(reg.addBook(dialog.readString("Skriv in f�rfattarens namn"), dialog.readString("Skriv in bokens titel")));
							break;
							
						case 2:
							dialog.printString(reg.removeBook(dialog.readString("Skriv in f�rfattarens namn")));
							break;
							
						case 3:
							dialog.printString(reg.searchByAuthor(dialog.readString("Skriv in f�rfattarens namn")));
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
