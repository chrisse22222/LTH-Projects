
public class CardCalculator 
{
	private CardDeck deck;
	private Card card;
	private double probability;
	private int step, amount;
	
	public CardCalculator(CardDeck deck, int amount) 
	{
		this.deck = deck;
		this.amount = amount;
	}
	
	public void Calculate() 
	{
		for (int i = 0; i < amount; i++) 
		{
			deck.shuffle();
			step = 0;
			do {
				step++;
				if (step > 3) {
					step = 1;
				}
				
				card = deck.getCard();
				
			} while (deck.moreCards() && step != card.getRank());
			
			if (!deck.moreCards() && card.getRank() != step) {
				probability++;
			}
		}
		
		probability /= amount;
		System.out.println("Patiens Chans: " + probability);
	}
}
