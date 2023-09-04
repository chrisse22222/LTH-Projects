public class TestCardDeck {
 	/** Program för att testa metoderna i klassen CardDeck */
	public static void main(String[] args) {
		CardDeck deck = new CardDeck();
		System.out.println("Oblandad kortlek:");
		while (deck.moreCards()) {
			Card c = deck.getCard();
			System.out.print(c + " ");
		}
		System.out.println();
		System.out.println();
		System.out.println("Blandad kortlek:");
		int[] suitVect = new int[4];   // Antal spader, hjärter etc
		int[] rankVect = new int[13];  // Antal 1:or, 2:or etc
		deck.shuffle();
		while (deck.moreCards()) {
			Card c = deck.getCard();
			suitVect[c.getSuit() - Card.SPADES]++; 
			rankVect[c.getRank() - 1]++;
			System.out.print(c + " ");
		}
		System.out.println();
		System.out.println();
		System.out.print("Antal kort i de olika färgerna: ");
		for (int i = 0; i <= 3; i++) {
			System.out.print(suitVect[i] + " ");
		}
		System.out.println();
		System.out.print("Antal kort i de olika valörerna: ");
		for (int i = 0; i <= 12; i++) {
			System.out.print(rankVect[i] + " ");
		}
		System.out.println();
		
		CardCalculator cD = new CardCalculator(deck, 10000000);
		cD.Calculate();
	}
}