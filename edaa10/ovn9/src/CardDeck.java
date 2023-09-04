import java.util.Random;

public class CardDeck 
{
	private Card [] theCards;
	private Card topCard;
	private int drawnCards;
	
	public CardDeck () 
	{
		theCards = new Card[52];
		int currentCard = 0;
		
		for (int i = 1; i <= theCards.length / 4; i++) 
		{
			for (int suit = Card.SPADES; suit <= Card.CLUBS ; suit++) 
			{
				theCards[currentCard] = new Card(suit, i);
				currentCard++;
			}
		}
	}
	
	public void shuffle() 
	{
		Random random = new Random();
		for (int i = theCards.length - 1; i > 0; i--) 
		{
			int newPos = random.nextInt(i);
			Card selectedCard = theCards[newPos];
			
			theCards[newPos] = theCards[i];
			theCards[i] = selectedCard;
		}
		
		drawnCards = 0;
	}
	
	public boolean moreCards() 
	{
		return drawnCards < theCards.length;
	}
	
	public Card getCard() 
	{
		topCard = theCards[drawnCards];
		drawnCards++;
		return topCard;
	}
}
